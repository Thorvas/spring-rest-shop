package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
