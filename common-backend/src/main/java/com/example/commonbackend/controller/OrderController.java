package com.example.commonbackend.controller;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/orders")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		orderRepository.deleteById(orderId);
		return "Order deleted.";
	}

	@GetMapping("/addOrder/{customerId}/{productId}")
	public Order addOrder(@PathVariable int customerId, @PathVariable int productId) {
		return null;
	}

	//• Get all orders
	//GET /orders
	//• Delete an order based on orderId
	//GET /deleteOrder/{orderId}
	//• Add a product for a customer (create order) (Optional)
	//GET /addOrder/{customerId}/{productId}
}
