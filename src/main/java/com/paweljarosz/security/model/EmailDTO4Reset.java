package com.paweljarosz.security.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailDTO4Reset {
	
	@NotNull
	@Size(min = 7, max = 60,message="Please enter a valid email address")
	private String email;

	public EmailDTO4Reset(){}
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmailDTO4Reset(String email){
		super();
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
