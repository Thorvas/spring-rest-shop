package com.example.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Entities.Customer;

@Repository
public class JPACustomerDAOImpl implements CustomerDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public JPACustomerDAOImpl(EntityManager newManager) {
		this.entityManager = newManager;
	}

	@Override
	public List<Customer> findAll() {
		Query theQuery = entityManager.createQuery("from Customer");
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

	@Override
	public void deleteById(int newId) {
		Query theQuery = entityManager.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", newId);
		theQuery.executeUpdate();

	}

	@Override
	public void save(Customer newCustomer) {
		Customer theCustomer = entityManager.merge(newCustomer);
		newCustomer.setId(theCustomer.getId());

	}

	@Override
	public Customer findCustomer(int newId) {
		Customer foundCustomer = entityManager.find(Customer.class, newId);
		return foundCustomer;
	}

}
