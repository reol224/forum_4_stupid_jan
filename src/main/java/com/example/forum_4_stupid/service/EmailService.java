package com.example.forum_4_stupid.service;

import com.example.forum_4_stupid.LoggerClass;
import com.example.forum_4_stupid.dto.EmailRequest;
import com.example.forum_4_stupid.exceptions.EmailAlreadyExistsException;
import com.example.forum_4_stupid.exceptions.EmailNotFoundByUsernameException;
import com.example.forum_4_stupid.model.Email;
import com.example.forum_4_stupid.repository.EmailRepository;
import com.example.forum_4_stupid.repository.UsersRepository;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@EnableTransactionManagement
public class EmailService {

	private final UsersRepository usersRepository;
	private final EmailRepository emailRepository;
	
	@Autowired
	public EmailService (UsersRepository usersRepository, EmailRepository emailRepository) {
		this.usersRepository = usersRepository;
		this.emailRepository = emailRepository;
	}

	@Transactional
	public void addEmail (EmailRequest emailRequest) {
		try {
			var email = new Email();
			email.setEmail(emailRequest.getEmail());
			email.setUser(usersRepository.findByUsername(emailRequest.getUsername()).get());
			emailRepository.save(email);			
		} catch (DataIntegrityViolationException e) {
			throw new EmailAlreadyExistsException("Email Already Exists");
		}
	}
	
	public Email getEmailByOwnerId(Integer user_ids) {
		try {
			Email email = emailRepository.findByUser_Id(user_ids).get(0);
			return email;			
		} catch (NoSuchElementException e) {
			throw new EmailNotFoundByUsernameException("No Email associated with user");
		}
	}
	
	public List<Email> getAllEmailFromUserByUserId (String owner_id) {
		try {	
			return emailRepository.findByUser_Id(Integer.parseInt(owner_id));
		} catch (NoSuchElementException e) {
			LoggerClass.getLogger(EmailService.class).log(Level.INFO, "Someone Searched for an Email that does not Exist.");
			throw new EmailNotFoundByUsernameException("Email Does Not Exist");
		}
	}
	
	public List<Email> getAllEmaiLFromUserByUsername(String username) {
		try {	
			return emailRepository.findByUser_Username(username);
		} catch (NoSuchElementException e) {
			LoggerClass.getLogger(EmailService.class).log(Level.INFO, "Someone Searched for an Email that does not Exist.");
			throw new EmailNotFoundByUsernameException("Email Does Not Exist");
		}
	}
	
	public void deleteEmail (Email entity) {
		emailRepository.delete(entity);;
	}
}
