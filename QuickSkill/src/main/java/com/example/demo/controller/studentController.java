package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Users;
import com.example.demo.service.CommentService;
import com.example.demo.service.studentService;
import com.example.demo.service.trainerservice;
import com.example.demo.service.userservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
public class studentController {
    @Autowired
	trainerservice tr;
    
    @Autowired
    userservice us; 
    
    @Autowired
	CommentService cService;
    
    @Autowired
	studentService service;
    
	@GetMapping("/purchase")
	public String showCourses(Model model, HttpSession session)
	{
		Users User=(Users) session.getAttribute("loggedInUser");
		List<Course> courseList =tr.courseList();
		model.addAttribute("courseList", courseList);
		model.addAttribute("loggedInUser", User);
		return "purchase";
	}
	
	@GetMapping("/fetchCourses")
	public String fetchCourses(Model model, HttpSession session)
	{
		Users User=(Users) session.getAttribute("loggedInUser");
		List<Course> courseList =tr.courseList();
		String email = User.getEmail();
		Users user = us.getUser(email);
		model.addAttribute("courseList",courseList);
		return "mycourses";
	}
	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId")int lessonId,
							Model model,HttpSession session) {
	//	Users user = (Users) session.getAttribute("loggedInUser");

		
		
		Lesson lesson = service.getLesson(lessonId);
		// Extract the YouTube video id from the URL
	    String youtubeUrl = lesson.getLink();
	    
	    String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
	    lesson.setLink(videoId);
		
		
		model.addAttribute("lesson",lesson);
		List<Comments> commentsList=cService.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}
	
	@PostMapping("/addComment")
	public String comments(@RequestParam("comment")String comment
						,Model model) {
		Comments c=new Comments();
		c.setComment(comment);
		cService.addComment(c);
		
		List<Comments> commentsList=cService.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session, logging the user out
        }
        return "login"; // Redirect to the login page after logout
    }
	/*when the user clicks the "Logout" button, 
	//the request will be sent to the /logout endpoint. The controller 
	//will then check if there is an active session using request.
	getSession(false). If a session exists, it will be invalidated,
	 effectively logging the user out. Finally, the user will be redirected to 
	 the login page, and the session will be cleared.
	//Using HttpSession for logout provides a simple and effective way to manage user sessions in your Spring Boot application.
	*/
	
	
}
