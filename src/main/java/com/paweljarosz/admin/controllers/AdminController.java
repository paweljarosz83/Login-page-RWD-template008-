package com.paweljarosz.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paweljarosz.security.repos.UserDao;


@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserDao dao;
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
	public void removeUser(Long id){
		
	}
}
