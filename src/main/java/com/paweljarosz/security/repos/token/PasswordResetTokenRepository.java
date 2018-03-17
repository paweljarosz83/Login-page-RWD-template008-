package com.paweljarosz.security.repos.token;

import org.springframework.data.repository.CrudRepository;
import com.paweljarosz.security.model.token.PasswordResetToken;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long>{

	PasswordResetToken findByToken(String token);
}
