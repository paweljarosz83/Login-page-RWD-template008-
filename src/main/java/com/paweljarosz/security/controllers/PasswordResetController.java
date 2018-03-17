package com.paweljarosz.security.controllers;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.paweljarosz.security.model.EmailDTO4Reset;
import com.paweljarosz.security.model.PasswordResetDTO;
import com.paweljarosz.security.model.User;
import com.paweljarosz.security.model.token.PasswordResetToken;
import com.paweljarosz.security.services.UserService;
import com.paweljarosz.security.services.mail.MailService;
import com.paweljarosz.security.services.token.PasswordResetTokenService;
import com.paweljarosz.security.validators.EmailResetDTOValidator;
import com.paweljarosz.security.validators.PasswordResetDTOValidator;

@Controller
public class PasswordResetController {

	
	@Autowired
	private EmailResetDTOValidator validator;

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Autowired
	private MessageSource messages;
	
	@Autowired
	private PasswordResetDTOValidator passwordResetDTOValidator;


	private User user;
	private String token;


	///////////////////////send email with link/////////////////////////////////
	
	
	/**
	 * Display forgot password page. Provide empty dto for email
	 * 
	 * @param emailResetDTO dto to grab an email
	 * @return
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(EmailDTO4Reset emailDTO4Reset){
		return "auth/forgot";
	}

	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String resetPassword(@Valid EmailDTO4Reset emailDTO4Reset, BindingResult bindingResult,final RedirectAttributes redirectAttributes,final HttpServletRequest request) {

		validator.validate(emailDTO4Reset, bindingResult);

		if (bindingResult.hasErrors()) {
			return "auth/forgot";
		}

		redirectAttributes.addFlashAttribute("message", "Recovery was link sent. Check your email.");		

		User user = userService.findByEmail(emailDTO4Reset.getEmail());
		String tokenString = UUID.randomUUID().toString();
		PasswordResetToken token = new PasswordResetToken(tokenString, user);
		passwordResetTokenService.save(token);

		String url = createResetPasswordUrl(request,tokenString,user);
		sendPasswordRecoveryEmail(url,user.getEmail());

		return "redirect:/forgot";
	}
	
	private String createResetPasswordUrl(HttpServletRequest request, String tokenString, User user) {
		String contextPath =  "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + tokenString;
		return url;
	}

	private void sendPasswordRecoveryEmail(String url,String email) {
		mailService.sendPasswordRecoveryEmail(url,email);
	}
	
	
	
	
	/////////////////////////////////////recive link from email and validate/////////////////////////////

	/**
	 * Accepts a link from an email. Validates a token and an id. 
	 * Showes and error on failure or pass change from on success 
	 * 
	 * @param locale			
	 * @param model 			model to add a message to
	 * @param id				user id from recovery link
	 * @param token 			token from recovery link
	 * @param passwordResetDTO
	 * @return
	 */
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage(Locale locale, Model model,  @RequestParam("id") long id, @RequestParam("token") String token,PasswordResetDTO passwordResetDTO) {
		
		String result = passwordResetTokenService.validatePasswordResetToken(id, token);

		if (result != null) {
			model.addAttribute("message", messages.getMessage("password.reset.message." + result, null, locale));
			return "auth/resetPasswordFailed";
		}else{
			user = userService.findById(id);
			this.token = token;
			return "auth/resetPassword";
		}
	}

	/**
	 * Password reset from submit method.
	 * @param passwordResetDTO
	 * @param bindingResult
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/resetPassword", method = RequestMethod.POST)
	public String resetPasswordSave(@Valid PasswordResetDTO passwordResetDTO, BindingResult bindingResult,Model model){
		
		passwordResetDTOValidator.validate(passwordResetDTO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "auth/resetPassword";
		}else{
			setNewPassword(passwordResetDTO);
			model.addAttribute("message","Password was changed. You can now login");
			return "auth/login";
			
		}
		
	}

	private void setNewPassword(PasswordResetDTO dto) {
		
		if(user!=null){
			user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
			userService.update(user);
			passwordResetTokenService.deleteToken(passwordResetTokenService.findByToken(token));
		}
	}


	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	








}
