package br.com.t2m.pblapi.domain.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.Usuario;

@Service
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmailPbl(Pbl pbl) throws MailException, MessagingException {

		for (Aluno aluno : pbl.getAluno()) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			String htmlMsg = "<h3>Data de ínicio: " + pbl.getDataInicio() + "</h3>\r\n"
					+ "   	  <h3>Data de conclusão: " + pbl.getDataConclusao() + " 18/11/2020</h3>\r\n"
					+ "    	  <h3>Problema: " + pbl.getProblema() + " </h3>\r\n" + "    	  <h3>Disciplina: "
					+ pbl.getPblTemaDisciplina().getDisciplina() + "</h3> ";
			helper.setSubject("Novo PBL da disciplina: " + pbl.getPblTemaDisciplina().getDisciplina());
			helper.setText(htmlMsg, true);

			System.out.println(aluno.getEmail());
			helper.setTo(aluno.getEmail());
			javaMailSender.send(mimeMessage);
			
		}

	}

	public void sendEmailWithAttachment(Usuario usuario) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(usuario.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}

}
