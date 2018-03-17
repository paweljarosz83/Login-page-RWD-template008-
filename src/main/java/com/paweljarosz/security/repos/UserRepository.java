package com.paweljarosz.security.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paweljarosz.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	 User findByEmail(String email);
	
	 User findByUsername(String username);

}
