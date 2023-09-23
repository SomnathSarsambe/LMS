package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Lesson;

public interface lessonrepository extends JpaRepository<Lesson, Integer> {

}
