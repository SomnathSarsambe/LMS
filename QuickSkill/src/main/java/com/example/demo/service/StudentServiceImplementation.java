package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.lessonrepository;
import com.example.demo.dao.userrepository;
import com.example.demo.entity.Lesson;

@Service
public class StudentServiceImplementation implements studentService {

	@Autowired
	userrepository ur;
	
	@Autowired
	lessonrepository lessonRepo;
	
	@Override
	public Lesson getLesson(int lessonId) {
		
		return lessonRepo.findById(lessonId).get();
		
	}

}
