package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.entity.users;
import com.example.demo.service.userservice;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nav")
public class userController {

    userservice si;
    
    @Autowired
	public userController(userservice si) {
		this.si=si;
	}

    @GetMapping("/register")
    public String register()
    {
    	return "register";
    }
    
    @PostMapping("/adduser")
    public String registeruser(@RequestParam("name") String name,
    		@RequestParam("email") String email,
    		@RequestParam("password") String password,
    		@RequestParam("role") String role)
    {
    	boolean emailexist=si.checkEmail(email);
    	
    	if(emailexist==false)
    	{
    		Users user=new Users();
    		
    		user.setName(name);
    		user.setEmail(email);
    		user.setPassword(password);
    		user.setRole(role);
    		
    		si.addUser(user);
    		System.out.println("User Registered Succefully");
    		return "login";
    	}
    	else {
    		
    		System.out.println("user Already Exist");
    		return "registerfail";
    	}
    }
    
    @GetMapping("/login")
	public String login1()
	{
    	return "login";
	}
    
    
    @PostMapping("/validate")
    public String validate(@RequestParam("email")
    		String email,@RequestParam("password")String password,
    		HttpSession session)
    {
    	{
    		if(si.checkEmail(email)) {
    		boolean val=si.validate(email, password);
    		//if user is valid
    		if(val==true) {
    		Users user = si.getUser(email); // Assuming you have a method to get the User object
    		session.setAttribute("loggedInUser", user); // Saving the User object in session
    		if(si.getUserRole(email).equals("trainer")) 
    		{
    		return "trainerhome";
    		}
    		else {
    		return "studenthome";
    		}
    		}
    		else {
    		System.out.println("incorrect credentials, try again!");
    		return "loginFail";
    		}
    		}
    		else {
    		return "loginFail";
    		}
    		}
    }

    
    @GetMapping("/createcourse")
    public String createcourse()
    {
    	return "createcourse";
    }
    
    @GetMapping("/addlesson")
    public String addlesson()
    {
    	return "addlesson";
    }
    
}

