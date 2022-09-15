package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
	
}
