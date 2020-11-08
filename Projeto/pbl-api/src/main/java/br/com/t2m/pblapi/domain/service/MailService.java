package br.com.t2m.pblapi.domain.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.utils.EmailMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private DisciplinaService disciplinaService;

	public static int noOfQuickServiceThreads = 20;

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	public void sendEmailPbl(Pbl pbl) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		Disciplina disciplina = disciplinaService.getById(pbl.getPblTemaDisciplina().getDisciplina().getId());

		helper.setSubject("Novo PBL da disciplina: " + disciplina.getNome());

		quickService.submit(new Runnable() {

			@Override
			public void run() {
				for (Aluno aluno : pbl.getAluno()) {
					AlunoDTO alunoDTO = alunoService.getById(aluno.getId());
					
					try {
						helper.setText(EmailMessage.pblAlunos(pbl, alunoDTO.getNome() ,disciplina.getNome()), true);
						helper.setTo(alunoDTO.getEmail());
						
						javaMailSender.send(mimeMessage);

					} catch (MessagingException e) {
						e.printStackTrace();
					}

				}

			}

		});

	}

}
