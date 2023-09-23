package com.example.demo.service;



import java.util.List;


import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;


public interface trainerservice {

	
	public Course addCourse(Course course);

	public Course getCourse(int courseId);

	public String addLesson(Lesson lsn);

	public String saveCourse(Course crs);
	
	public List<Course> courseList();

	
}

