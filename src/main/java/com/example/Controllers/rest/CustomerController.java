package com.example.Controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Customer;
import com.example.Service.CustomerService;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customers")
	public Customer save(@RequestBody Customer theCustomer) {
		customerService.save(theCustomer);
		return theCustomer;
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers()
	{
		return customerService.findAll();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return customerService.findById(customerId);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer foundCustomer = customerService.findById(customerId);
		
		if (foundCustomer == null) {
			throw new RuntimeException("Customer with id: "+customerId+" not found.");
		}
		customerService.deleteById(customerId);
		return "Deleted Customer with id: "+customerId;
	}
}
