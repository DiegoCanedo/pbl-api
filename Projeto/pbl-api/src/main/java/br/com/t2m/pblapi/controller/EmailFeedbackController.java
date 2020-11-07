package br.com.t2m.pblapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.EmailService;


@RestController
@RequestMapping("/feedback")
public class EmailFeedbackController {
	
	private EmailService emailService;
	
	public EmailFeedbackController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping
	public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) {
		
	}
	

}
