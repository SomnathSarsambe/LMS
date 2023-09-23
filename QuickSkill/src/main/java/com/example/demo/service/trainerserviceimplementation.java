package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.courserepository;
import com.example.demo.dao.lessonrepository;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;

@Service
public class trainerserviceimplementation implements trainerservice {

	@Autowired
	courserepository cr;
	
	@Autowired
	lessonrepository lr;
	
	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		 
		 return cr.save(course);
		}

	@Override
	public Course getCourse(int courseId) {
		// TODO Auto-generated method stub
		return cr.findById(courseId).get();
	}

	@Override
	public String addLesson(Lesson lsn) {
		// TODO Auto-generated method stub
		lr.save(lsn);
		return "lesson added Succesfully";
	}

	@Override
	public String saveCourse(Course crs) {
		// TODO Auto-generated method stub
		cr.save(crs);
		return "Course saved succefully";
	}

	@Override
	public List<Course> courseList() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

}
