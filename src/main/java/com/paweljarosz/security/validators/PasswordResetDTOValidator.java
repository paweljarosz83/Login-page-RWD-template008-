package com.paweljarosz.security.validators;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.paweljarosz.security.model.PasswordResetDTO;

@Controller
public class PasswordResetDTOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordResetDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordResetDTO dto = (PasswordResetDTO) target; 
		if(!dto.getPassword().equals(dto.getPasswordRepeated())){
			errors.rejectValue("password", "passwords.dont.match","Passwords don't match!");
		}
	}
}
