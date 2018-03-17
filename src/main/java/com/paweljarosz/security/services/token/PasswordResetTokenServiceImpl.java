package com.paweljarosz.security.services.token;

import java.util.Arrays;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.paweljarosz.security.model.User;
import com.paweljarosz.security.model.token.PasswordResetToken;
import com.paweljarosz.security.repos.token.PasswordResetTokenRepository;



@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public String validatePasswordResetToken(Long id, String token) {
		
		final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		final Calendar cal = Calendar.getInstance();
		if ((passToken.getExpiryDate()
				.getTime() - cal.getTime()
				.getTime()) <= 0) {
			return "expired";
		}

		final User user = passToken.getUser();
		final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext()
		.setAuthentication(auth);
		return null;
	}

	@Override
	public void save(PasswordResetToken token) {
		passwordResetTokenRepository.save(token);		
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void deleteToken(PasswordResetToken token) {
		passwordResetTokenRepository.delete(token);
		
	}
	
}
