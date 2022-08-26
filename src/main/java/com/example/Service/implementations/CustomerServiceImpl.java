package com.example.Service.implementations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entities.Customer;
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

}
