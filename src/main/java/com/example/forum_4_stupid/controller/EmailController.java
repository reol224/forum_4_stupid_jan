package com.example.forum_4_stupid.controller;

import com.example.forum_4_stupid.dto.EmailDTO;
import com.example.forum_4_stupid.dto.EmailRequest;
import com.example.forum_4_stupid.dtoMapper.EmailDtoMapper;
import com.example.forum_4_stupid.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/email")
public class EmailController {

	private final EmailDtoMapper emailDtoMapper; 
	
	public EmailController(EmailService emailService, EmailDtoMapper emailDtoMapper) {
		this.emailDtoMapper = emailDtoMapper;
	}

	@PostMapping("/add-email")
	public void addEmail (@ModelAttribute EmailRequest emailRequest) {
		emailDtoMapper.save(emailRequest);
	}
	
	@GetMapping("/{owner_id}")
	public ResponseEntity<List<EmailDTO>> getEmail(@PathVariable String owner_id) {
		List<EmailDTO> response = emailDtoMapper.getAllEmailByUsersId(Integer.parseInt(owner_id));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
