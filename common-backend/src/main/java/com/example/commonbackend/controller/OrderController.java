package com.example.commonbackend.controller;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.repository.OrderRepository;
import com.example.commonbackend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class OrderController {

	private final OrderService orderService;
	private final OrderRepository orderRepository;

	public OrderController (OrderService orderService,
							OrderRepository orderRepository) {
		this.orderService = orderService;
		this.orderRepository = orderRepository;
	}

	@GetMapping("/orders")
	public ResponseEntity<?> getOrders () {
		return orderService.getOrders();
	}

	@GetMapping("/order/{orderID}")
	public ResponseEntity<?> getOrderRows (@PathVariable int orderID) {
		return orderService.getOrderRows(orderID);
	}

	@PostMapping("/orders")
	public ResponseEntity<?> addOrder (@RequestBody(required = false) Order order) {
		return order == null ?
				new ResponseEntity<>("That didn't work. Did you forget to include order details?",
						HttpStatus.BAD_REQUEST) : orderService.addOrder(order);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<?> deleteOrder (@PathVariable int orderId) {
		return orderService.deleteOrder(orderId);
	}

	@PostMapping("/addOrder/{customerId}/{productId}")
	public ResponseEntity<?> addOrderWithRows (@PathVariable String customerId,
			@PathVariable int productId) {
		return orderService.addOrderWithRows(customerId, productId);
	}

	@PostMapping("/addorders")
	public Integer addOrders (@RequestBody(required = false) Order order) {
		return orderRepository.save(order).getId();
	}
}