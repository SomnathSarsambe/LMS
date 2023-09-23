package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.demo.dao.userrepository;
import com.example.demo.entity.Users;

@Service
public class serviceImplementation implements userservice {
	
	userrepository ur;
	@Autowired
	public serviceImplementation(userrepository ur) {
		this.ur=ur;
	
}

	@Override
	public String addUser(Users user) {
		// TODO Auto-generated method stub
	         ur.save(user);
		return "Added succesfully";
	}



	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return ur.existsByEmail(email);
	}
	
	@Override
	public boolean validate(String email, String password) {
	if(ur.existsByEmail(email)) {
	Users u=ur.getByEmail(email);
	String dbPassword=u.getPassword();
	if(password.equals(dbPassword)) 
	{
	return true;
	}
	else {
	return false;
	}
	}

	else {
	return false;
	}
	}


	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return ur.getByEmail(email);
	}

	@Override
	public  String getUserRole(String email) {
		Users u = ur.getByEmail(email);
		return u.getRole();
	
	}

	@Override
	public String updateUser(Users user) {
		ur.save(user);
		return "update succcesfull";
	}

}
