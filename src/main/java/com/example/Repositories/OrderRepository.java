package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
