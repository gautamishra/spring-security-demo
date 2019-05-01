package com.spring.saphire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.saphire.modal.VerificationToken;

public interface TokenRepositiory extends JpaRepository<VerificationToken, Long> {
	public VerificationToken findByToken(String token);
}

