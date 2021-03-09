package com.example.forum_4_stupid.service;

import com.example.forum_4_stupid.dto.TodoRequest;
import com.example.forum_4_stupid.exceptions.TodoAlreadyExistException;
import com.example.forum_4_stupid.exceptions.TodoNotFoundException;
import com.example.forum_4_stupid.model.Todos;
import com.example.forum_4_stupid.repository.TodosRepository;
import com.example.forum_4_stupid.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@EnableTransactionManagement
public class TodoService {

	private final TodosRepository todosRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public TodoService(TodosRepository todosRepository, UsersRepository usersRepository) {
		this.todosRepository = todosRepository;
		this.usersRepository = usersRepository;
	}
	
	public void addTodos (TodoRequest todoRequest) {
		try {
			var todos = new Todos();
			todos.setContent(todoRequest.getContent());
			todos.setTitle(todoRequest.getTitle());
			todos.setDeadline(todoRequest.getDeadline());
			todos.setCreated(new Date());
			todos.setUser(usersRepository.findByUsername(todoRequest.getUsername()).get());
			
			todosRepository.save(todos);			
		} catch (DataIntegrityViolationException e) {
			throw new TodoAlreadyExistException("Todo Already Exist");	
		}
	}
	
	@Transactional(readOnly = true)
	public Todos findTodosByOwnerId (Integer id) {
		try {
			Todos todo = todosRepository.findByUser_Id(id).get();
			return todo;			
		} catch (NoSuchElementException e) {
			throw new TodoNotFoundException("Todo Does Not Exist");
		}
	}

	@Transactional
	public ResponseEntity<HttpStatus> deleteTodo(Todos entity){
		try {
			todosRepository.delete(entity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
