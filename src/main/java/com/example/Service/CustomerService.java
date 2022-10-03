package com.example.Service;

import java.util.List;

import com.example.Entities.Customer;
import com.example.Entities.Product;


public interface CustomerService {
	public List<Customer> findAll();
	public Customer findById(int theId);
	public void save(Customer newCustomer);
	public void deleteById(int newId);
	public boolean processPayment(Customer customerToEdit, Product productToOperate);
	public List<Product> listOwnerProducts(Customer owner);
	public void deleteProduct(Customer foundCustomer, Product productToDelete);
}
