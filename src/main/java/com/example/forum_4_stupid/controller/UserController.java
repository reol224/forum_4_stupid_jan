package com.example.forum_4_stupid.controller;

import com.example.forum_4_stupid.dto.UserDTO;
import com.example.forum_4_stupid.dtoMapper.UserDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserDtoMapper userDtoMapper;
	
	public UserController (UserDtoMapper userDtoMapper) {
		this.userDtoMapper = userDtoMapper;
	}
	
	@GetMapping("/userById/{id}")
	public ResponseEntity<UserDTO> getUserInformationById (@PathVariable String id) {
		var response = userDtoMapper.getById(Integer.parseInt(id));
		
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}
	
	@GetMapping("/userByUsername")
	public ResponseEntity<UserDTO> getUserInformationByUsername (@RequestParam String username) {
		var response = userDtoMapper.getByUsername(username);
		
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}
	
}
