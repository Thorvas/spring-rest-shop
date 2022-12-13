package com.example.Service.implementations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entities.Customer;
import com.example.Entities.Product;
import com.example.Repositories.CustomerRepository;
import com.example.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerDAO;
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	@Transactional
	public Customer findById(int theId) {
		Optional<Customer> returnedCustomer = customerDAO.findById(theId);
		Customer newCustomer = null;
		if (returnedCustomer.isPresent()) {
			newCustomer = returnedCustomer.get();
		}
		else {
			throw new RuntimeException("Customer not found.");
		}
		return newCustomer;
	}

	@Override
	@Transactional
	public void save(Customer newCustomer) {
		customerDAO.save(newCustomer);

	}

	@Override
	@Transactional
	public void deleteById(int newId) {
		customerDAO.deleteById(newId);

	}
	
	
	
	@Override
	@Transactional
	public List<Product> listOwnerProducts(Customer owner) {
		int id = owner.getId();
		Optional<Customer> foundCustomer = customerDAO.findById(id);
		Customer toUpdate = foundCustomer.get();
		return toUpdate.getOwnedProducts();
	}
	
	@Override
	@Transactional
	public void deleteProduct(Customer foundCustomer, Product productToDelete) {
		foundCustomer.getOwnedProducts().remove(productToDelete);
		customerDAO.save(foundCustomer);
	}
	
	@Override
	@Transactional
	public boolean processPayment(Customer customerToEdit, Product productToOperate) {
		if (customerToEdit.getBalance() >= productToOperate.getProductCost())
		{
			Customer retrievedProductOwner = productToOperate.getProductOwner();
			customerToEdit.setBalance(customerToEdit.getBalance()-productToOperate.getProductCost());
			this.deleteProduct(retrievedProductOwner, productToOperate);
			customerDAO.save(customerToEdit);
		}
		else {
			System.out.println("Insufficient funds!");
			return false;
		}
		return true;
	}
	
	public void processPost(Customer foundCustomer, Product productToProcess) {
		foundCustomer.getOwnedProducts().add(productToProcess);
		customerDAO.save(foundCustomer);
		
		
	}
	

}
