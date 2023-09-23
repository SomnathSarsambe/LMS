package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.service.trainerservice;

@Controller
public class trainerController {
	trainerservice ts;
	
	@Autowired
	public trainerController(trainerservice ts) {
		super();
		// TODO Auto-generated constructor stub
		this.ts=ts;
	}
	


	@PostMapping("/addCourse")
	public String addcourse(@RequestParam("courseId")int courseId,
			@RequestParam("courseName") String courseName,
			@RequestParam("coursePrice")int coursePrice)
	{
		Course course=new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
	    course.setCoursePrice(coursePrice);	
	    
	     Course c = ts.addCourse(course);  
	    		if(c!= null)
	    {
	    return "/trainerhome";
	    }
	    else
	    {
	    	return "/createcoursefail";
	    }
	    }
     
	
	@PostMapping("/lesson")
	public String lesson(@RequestParam("courseId")int courseId,
	@RequestParam("lessonId")int lessonId,
	@RequestParam("lessonName")String lessonName,
	@RequestParam("topics")String topics,
	@RequestParam("link")String link) {
	Course course=ts.getCourse(courseId);
	Lesson lesson=new Lesson(lessonId,lessonName,topics,link,course);
	ts.addLesson(lesson);
	course.getLessons().add(lesson);
	ts.saveCourse(course);
	return "trainerhome";
	}	
	
	
	@GetMapping("/showcourses")
	public String showcourses(Model model)
	{ 
		List<Course> courseList=ts.courseList();
		model.addAttribute("courseList",courseList);
        return "courses";	
	}
	
}
