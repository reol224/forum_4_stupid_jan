package com.example.forum_4_stupid.dtoMapper;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;;
>>>>>>> 2373714898f99d4dfd5177b4b027317ec2c350e3
import com.example.forum_4_stupid.dto.TodoDTO;
import com.example.forum_4_stupid.dto.TodoRequest;
import com.example.forum_4_stupid.dtoMapper.interfaces.TodoDTOMapper;
import com.example.forum_4_stupid.model.Todos;
import com.example.forum_4_stupid.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoDtoMapper implements TodoDTOMapper<TodoDTO,TodoRequest,Todos>{

	@Autowired
	private TodoService todoService;

	@Override
	public TodoDTO getById(Integer id) {
		Todos todos = todoService.findTodosById(id);
		var todoDTO = new TodoDTO();
		todoDTO.setId(todos.getId());
		todoDTO.setContent(todos.getContent());
		todoDTO.setCreatedAt(todos.getCreated());
		todoDTO.setDeadline(todos.getDeadline());
		todoDTO.setTitle(todos.getTitle());
		
		return todoDTO;
	}

	@Override
	public void save(TodoRequest request) {
		todoService.addTodos(request);
	}

	@Override
	public void delete(Todos entity) {
<<<<<<< HEAD
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
		
>>>>>>> ce06a0e53cbd4853f97e18f49e47c19e72d8d24f
=======
>>>>>>> 2373714898f99d4dfd5177b4b027317ec2c350e3
	}

	@Override
	public List<TodoDTO> getAllByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoDTO> getAllByUserUsername(String username) {
		List<Todos> todos = todoService.findAllTodosByOwnerUsername(username);
		List<TodoDTO> todoDTOResponse = new ArrayList<>();
		
		for(Todos todo : todos) {
			var todoDTO = new TodoDTO();
			todoDTO.setId(todo.getId());
			todoDTO.setContent(todo.getContent());
			todoDTO.setCreatedAt(todo.getCreated());
			
			todoDTOResponse.add(todoDTO);
		}
		
		return todoDTOResponse;
		
	}
	
}
