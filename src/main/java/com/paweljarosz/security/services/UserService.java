package com.paweljarosz.security.services;

import com.paweljarosz.security.model.User;

public interface UserService {

	public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	public User findById(Long id);
	
	public void update(User user);
	
}
