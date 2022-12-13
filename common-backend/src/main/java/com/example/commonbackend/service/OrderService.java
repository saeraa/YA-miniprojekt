package com.example.commonbackend.service;

import com.example.commonbackend.model.Order;
import com.example.commonbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void deleteOrder (int orderId) {
		var result = jdbcTemplate.update("delete from `order details` where OrderID='" + orderId + "';");
		System.out.println(result);

		orderRepository.deleteById(orderId);
	}
}
