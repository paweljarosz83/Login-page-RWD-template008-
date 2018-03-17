package com.paweljarosz.security.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO4Registration {

	@NotNull
	@Size(min = 3, max = 60, message="Invalid username (min 3, max 60)")
	private String username;
	
	@NotNull
	@Size(min = 3, max = 60, message="Invalid email (min 3, max 60)")
	private String email;
	
	@NotNull
	@Size(min = 3, max = 60, message="Invalid password (min 3, max 60)")
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
