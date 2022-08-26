package com.example.Service;

import java.util.List;

import com.example.Entities.Order;

public interface OrderService {
	public List<Order> findAll();
	public Order findById(int idToFind);
	public void deleteOrder(int idToDelete);
	public void saveOrder(Order orderToSave);

}
