package com.paweljarosz.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paweljarosz.security.model.User;
import com.paweljarosz.security.model.UserDetailsImpl;
import com.paweljarosz.security.repos.UserDao;
import com.paweljarosz.security.repos.UserRepository;



@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = findByUsername(username);
		//User user = findByEmail(username);
				
		if(user==null){
			throw new UsernameNotFoundException(username+" not found!");
		}
		return new UserDetailsImpl(user);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}
	
	
	
	
}
