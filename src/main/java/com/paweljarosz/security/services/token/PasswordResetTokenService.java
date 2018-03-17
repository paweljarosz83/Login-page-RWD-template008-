package com.paweljarosz.security.services.token;

import com.paweljarosz.security.model.token.PasswordResetToken;

public interface PasswordResetTokenService {

	String validatePasswordResetToken(Long id, String token);

	void save(PasswordResetToken token);

	PasswordResetToken findByToken(String token);
	
	void deleteToken(PasswordResetToken token);

}
