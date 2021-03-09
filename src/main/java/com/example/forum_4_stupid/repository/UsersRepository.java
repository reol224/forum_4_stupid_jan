package com.example.forum_4_stupid.repository;

import com.example.forum_4_stupid.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Optional<Users> findByUsername (String username);
	
	
}

