package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;




public interface userrepository extends JpaRepository<Users, Integer> {

	   boolean existsByEmail(String email);

	Users getByEmail(String email);
}
