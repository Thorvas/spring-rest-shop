package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
