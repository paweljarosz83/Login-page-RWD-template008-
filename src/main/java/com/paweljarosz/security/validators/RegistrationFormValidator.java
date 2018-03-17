package com.paweljarosz.security.validators;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.paweljarosz.security.model.UserDTO4Registration;
import com.paweljarosz.security.services.UserService;

@Component
public class RegistrationFormValidator implements Validator{

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO4Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if(errors.hasErrors()){
			return;
		}
		UserDTO4Registration dto = (UserDTO4Registration) target;

		if(isUsernameAlreadyUsed(dto)){
			errors.rejectValue("username","property.username.registration.email.already.registered","This username is already used");
		}

		if(isEmailIncorrect(dto)){
			errors.rejectValue("email","property.user.registration.email.incorrect","This email is incorrect");
		}
		
		if(isEmailAlreadyUsed(dto)){
			errors.rejectValue("email","property.user.registration.email.already.registered","This email is already registered");
		}	
		
		
	}

	private boolean isUsernameAlreadyUsed(UserDTO4Registration dto){
		if(userService.findByUsername(dto.getUsername())!=null){
			return true;
		}
		return false;
	}
	
	private boolean isEmailIncorrect(UserDTO4Registration dto) {
		 try {
		      InternetAddress emailAddr = new InternetAddress(dto.getEmail());
		      emailAddr.validate();
		      return false;
		   } catch (AddressException ex) {
		      return true;
		   }
	}

	private boolean isEmailAlreadyUsed(UserDTO4Registration dto){
		if(userService.findByEmail(dto.getEmail())!=null){
			return true;
		}
		return false;
	}
}

