package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Course;

public interface courserepository extends JpaRepository<Course, Integer> {

}
