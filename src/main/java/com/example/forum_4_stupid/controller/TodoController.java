package com.example.forum_4_stupid.controller;

import com.example.forum_4_stupid.dto.TodoRequest;
import com.example.forum_4_stupid.dtoMapper.TodoDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/todo")
public class TodoController {

	private final TodoDtoMapper todoDtoMapper;
	
	@Autowired
	public TodoController (TodoDtoMapper todoDtoMapper) {
		this.todoDtoMapper = todoDtoMapper;
	}
	
	//add appropriate redirects
	@PostMapping("/add-todo")
	public void addTodo (@ModelAttribute TodoRequest todoRequest) {
		
		todoDtoMapper.save(todoRequest);
	}
//	
//	@GetMapping("todo")
//	public ResponseEntity<TodoDTO> getTodo (@RequestParam String id) {
//		var todo = todoService.findTodosByOwnerId(Integer.parseInt(id));
//		var response = todoDtoMapper.returnEntity(todo);
//		
//		return new ResponseEntity<TodoDTO>(response, new HttpHeaders(), HttpStatus.OK);	
//	}
	
}
