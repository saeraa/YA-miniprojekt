package com.example.commonbackend.service;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.model.OrderRow;
import com.example.commonbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<Order> getOrders () {
		return orderRepository.findAll();
	}

	public Order addOrder (Order order) {
		return orderRepository.save(order);
	}

//	public List<OrderRow> getOrderRows(int orderID) {
//		var result =
//				jdbcTemplate.query("select * from `order details` where OrderID=" + orderID + ";",
//						BeanPropertyRowMapper.newInstance(OrderRow.class));
//
//		return result.size() == 0 ? null : result;
//	}

	public List<OrderRow> getOrderRows(int orderID) {
		String query = "SELECT `order details`.OrderID, `order details`.ProductID, `order details`" +
											 ".Quantity, `order details`.UnitPrice, `order details`.Discount, " +
											 "`products`" +
											 ".ProductName FROM `order" +
											 " details` " +
											 "INNER JOIN " +
											 "`products`	ON `order details`.ProductID=`products`.ProductID WHERE " +
											 "OrderID=" + orderID + ";";
		var result =
				jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(OrderRow.class));
		return result;
	}

	public void deleteOrder (int orderId) {
		var result = jdbcTemplate.update("delete from `order details` where OrderID='" + orderId + "';");
		System.out.println(result);

		orderRepository.deleteById(orderId);
	}
}
