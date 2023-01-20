package com.example.commonbackend.service;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.model.OrderRow;
import com.example.commonbackend.repository.CustomerRepository;
import com.example.commonbackend.repository.OrderRepository;
import com.example.commonbackend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final JdbcTemplate jdbcTemplate;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;

	public OrderService (OrderRepository orderRepository, JdbcTemplate jdbcTemplate,
						 CustomerRepository customerRepository,
						 ProductRepository productRepository) {
		Assert.notNull(orderRepository, "OrderRepository must not be null!");
		Assert.notNull(jdbcTemplate, "JDBCTemplate must not be null!");
		this.orderRepository = orderRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	public ResponseEntity<?> getOrders () {
		var results = orderRepository.findAll();
		return results.size() == 0 ?
				new ResponseEntity<>("Sorry, no orders found.", HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> addOrder (Order order) {
		Boolean doesCustomerExist = customerRepository.existsById(order.getCustomerID());
		if (Boolean.FALSE.equals(doesCustomerExist)) {
			return new ResponseEntity<>("You need to enter an existing customerID to create an order.",
					HttpStatus.BAD_REQUEST);
		} else {
			var results = orderRepository.save(order);
			return new ResponseEntity<>(results, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<?> getOrderRows (int orderID) {
		String query = "SELECT `order details`.OrderID, `order details`.ProductID, `order details`" +
				".Quantity, `order details`.UnitPrice, `order details`.Discount, " +
				"`products`" +
				".ProductName FROM `order" +
				" details` " +
				"INNER JOIN " +
				"`products`	ON `order details`.ProductID=`products`.ProductID WHERE " +
				"OrderID=" + orderID + ";";
		var results =
				jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(OrderRow.class));
		return results.size() == 0 ?
				new ResponseEntity<>(String.format("Order rows not found for ID: %s.", orderID),
						HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(results, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteOrder (int orderID) {
		var order = orderRepository.findById(orderID);
		if (order.isPresent()) {
			var result = orderRepository.deleteOrderRow(orderID);
			orderRepository.deleteById(orderID);
			return new ResponseEntity<>(String.format("Order with the ID: %s successfully deleted. %s " +
					"order row(s) deleted.", orderID, result),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(String.format("Order not found for ID: %s.", orderID),
					HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> addOrderWithRows (String customerId, int productId) {
		Boolean doesCustomerExist = customerRepository.existsById(customerId);
		Boolean doesProductExist = productRepository.existsById(productId);
		if (Boolean.FALSE.equals(doesCustomerExist)) {
			return new ResponseEntity<>(String.format("Customer with the ID: %s not found.", customerId),
					HttpStatus.NOT_FOUND);
		} else if (Boolean.FALSE.equals(doesProductExist)) {
			return new ResponseEntity<>(String.format("Product with the ID: %s not found.", productId),
					HttpStatus.NOT_FOUND);
		}
		Order order = orderRepository.save(new Order(customerId));
		int orderId = order.getId();

		String orderRowQuery = String.format("INSERT INTO northwind.`order details` (OrderID, " +
				"ProductID) VALUES (%s, %s)", orderId, productId);
		jdbcTemplate.update(orderRowQuery);

		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
}
