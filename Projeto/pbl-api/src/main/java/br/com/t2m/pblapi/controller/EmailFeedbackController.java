package br.com.t2m.pblapi.controller;

import javax.validation.ValidationException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.EmailFeedback;
import br.com.t2m.pblapi.domain.service.EmailService;

@RestController
@RequestMapping("/feedback")
public class EmailFeedbackController {

	private EmailService emailService;

	public EmailFeedbackController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping
	public void sendFeedback(@RequestBody EmailFeedback emailfeedback, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new ValidationException("Feedback is not valid");
		}
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailService.getHost());
		mailSender.setPort(this.emailService.getPort());
		mailSender.setUsername(this.emailService.getUsername());
		mailSender.setPassword(this.emailService.getPassword());
		
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(emailfeedback.getEmail());
		mailMessage.setTo("projetopblt2m@gmail.com");
		mailMessage.setSubject("New feedback from " + emailfeedback.getName());
		mailMessage.setText(emailfeedback.getFeedback());
		
		
		mailSender.send(mailMessage);
		
	}
}
