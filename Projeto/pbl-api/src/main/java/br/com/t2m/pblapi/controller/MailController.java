package br.com.t2m.pblapi.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	private MailService notificationService;


	/**
	 * 
	 * @return
	 * @throws MessagingException 
	 */
	@RequestMapping("/send-mail")
	public String send() throws MessagingException {

	
		try {
			//notificationService.sendEmailPbl();
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}


}
