package com.example.Service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Order;
import com.example.Repositories.OrderRepository;
import com.example.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderDAO;

	public List<Order> findAll() {
		return orderDAO.findAll();
	}
	
	public void deleteOrder(int idToDelete) {
		orderDAO.deleteById(idToDelete);
	}
	
	public Order findById(int idToFind) {
		Optional<Order> foundOrder = orderDAO.findById(idToFind);
		Order orderToReturn = null;
		if (foundOrder.isPresent()) {
			orderToReturn = foundOrder.get();
		}
		else {
			throw new RuntimeException("Order not found.");
		}
		return orderToReturn;
	}
	
	public void saveOrder(Order orderToSave) {
		orderDAO.save(orderToSave);
	}
}
