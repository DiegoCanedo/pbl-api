package br.com.t2m.pblapi.domain.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.mapper.AlunoMapper;

@Service
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
	private AlunoService service;
	
	public static int noOfQuickServiceThreads = 20;
	
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	public void sendEmailPbl(Pbl pbl) throws MailException, MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = " <div style=\"background-color: #002147; width: 800px; margin-right: auto; margin-left: auto;\">\r\n"
				+ "        \r\n"
				+ "        <img src=\"https://logodownload.org/wp-content/uploads/2015/03/uff-logo-6.png\" width=\"100px\" height=\"100px\" style=\"margin-left: 20px; margin-top: 20px\">"
				+ "        <h1 style=\"color: #f5f5f5; text-align: center; padding-top: 20px\">Olá, " + " !</h1>\r\n"
				+ "        <h2 style=\"color: #f5f5f5; text-align: center; padding-bottom: 50px\">Você foi adicionado a um novo PBL.</h2>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Disciplina: " + pbl.getPblTemaDisciplina().getDisciplina().getNome() + "</h3>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Problema: " + pbl.getProblema() + "</h3>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Data de início: " + pbl.getDataInicio() + " </h3>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px; padding-bottom: 50px\">Data de conclusão: " + pbl.getDataConclusao() + " </h3>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "    </div>";
		helper.setSubject("Novo PBL da disciplina: " + pbl.getPblTemaDisciplina().getDisciplina().getNome());
		helper.setText(htmlMsg, true);

		quickService.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (Aluno aluno : pbl.getAluno()) {
					AlunoDTO alunoDTO = service.getById(aluno.getId());
					
					try {
						helper.setTo(alunoDTO.getEmail());
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					javaMailSender.send(mimeMessage);
					
				}
				
				
			}
			
		});
		
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
