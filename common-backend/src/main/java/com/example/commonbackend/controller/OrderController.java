package com.example.commonbackend.controller;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public List<Order> getOrders() {
		return orderService.getOrders();
	}

	@PostMapping("/orders")
	public String addOrder(@RequestBody Order order) {
		orderService.addOrder(order);
		return "Order successfully created.";
	}
	@DeleteMapping("/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		orderService.deleteOrder(orderId);
		return "Order deleted.";
	}


	// TODO: First add order detail row
	@PostMapping ("/addOrder/{customerId}/{productId}")
	public Order addOrder(@PathVariable String customerId, @PathVariable int productId) {
		return orderService.addOrder(new Order(customerId, productId));
	}

	//• Get all orders
	//GET /orders
	//• Delete an order based on orderId
	//GET /deleteOrder/{orderId}
	//• Add a product for a customer (create order) (Optional)
	//GET /addOrder/{customerId}/{productId}
}
