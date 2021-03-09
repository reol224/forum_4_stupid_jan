package com.example.forum_4_stupid.repository;

import com.example.forum_4_stupid.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodosRepository extends JpaRepository<Todos, Integer>{

	 Optional<Todos> findByUser_Id(Integer user_ids);
	 
	 Optional<Todos> findByUser_Username(String username);
}
