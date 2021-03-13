package com.example.forum_4_stupid.repository;

import com.example.forum_4_stupid.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {

	
	List<Email> findByUser_Id(Integer user_ids);
	
	List<Email> findByUser_Username(String username);
	
}
