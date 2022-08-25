package com.example.DAO;

import java.util.List;

import com.example.Entities.Customer;

public interface CustomerDAO {
	public List<Customer> findAll();
	public void deleteById(int newId);
	public void save(Customer newCustomer);
	public Customer findCustomer(int newId);
}
