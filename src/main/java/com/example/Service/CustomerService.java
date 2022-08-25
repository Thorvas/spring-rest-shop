package com.example.Service;

import java.util.List;

import com.example.Entities.Customer;


public interface CustomerService {
	public List<Customer> findAll();
	public Customer findById(int theId);
	public void save(Customer newCustomer);
	public void deleteById(int newId);
}
