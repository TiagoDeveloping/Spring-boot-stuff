package com.alien.SessionAuth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.SessionAuth.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}