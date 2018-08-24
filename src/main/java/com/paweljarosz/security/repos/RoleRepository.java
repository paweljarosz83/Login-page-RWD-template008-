package com.paweljarosz.security.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paweljarosz.security.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	
	//Optional<Role> findById(Long id);

}
