package com.paweljarosz.security.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.paweljarosz.security.model.EmailDTO4Reset;
import com.paweljarosz.security.repos.UserRepository;

@Component
public class EmailResetDTOValidator implements Validator {

	@Autowired
	private UserRepository repo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EmailDTO4Reset.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()){
			return;
		}
		
		EmailDTO4Reset dto = (EmailDTO4Reset) target;
		
		if(repo.findByEmail(dto.getEmail())==null){
			errors.rejectValue("email","property.password.recovery.no.such.email","Email was not found!");	
		}
	}
}
