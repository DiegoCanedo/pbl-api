package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;
import br.com.t2m.pblapi.domain.repository.IPblRepository;
import br.com.t2m.pblapi.domain.repository.IPblTemaDisciplinaRepository;
import br.com.t2m.pblapi.domain.repository.ITemaPblRepository;
import br.com.t2m.pblapi.exception.InvalidDateException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class PblService {

	@Autowired
	IPblRepository pblRepository;

	@Autowired
	ITemaPblRepository temaPblRepository;

	@Autowired
	IDisciplinaRepository disciplinaRepositoy;

	@Autowired
	IPblTemaDisciplinaRepository pblTemaDisciplinaRepository;

	@Autowired
	AtividadeService atividadeService;
	
	@Autowired
	private MailService notificationService;

	@Transactional(readOnly = true)
	public List<Pbl> getAll() {

		return pblRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Set<Pbl> getByDisciplina(Long idDisciplinas) {

		return pblRepository.findAllByPblTemaDisciplina_Disciplina_Id(idDisciplinas);
	}

	@Transactional
	public Pbl insert(Pbl pbl) {

		if (pbl.getDataInicio().after(pbl.getDataConclusao())) {
			throw new InvalidDateException("Data Inicio não pode ser maior que a Data Conclusão");
		}

		if (!temaPblRepository.existsById(pbl.getPblTemaDisciplina().getTema().getId())) {
			throw new ResourceNotFoundException(Constants.TEMA_NAO_ENCONTRADO,
					pbl.getPblTemaDisciplina().getTema().getId().toString());
		}

		if (!disciplinaRepositoy.existsById(pbl.getPblTemaDisciplina().getDisciplina().getId())) {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA,
					pbl.getPblTemaDisciplina().getDisciplina().getId().toString());
		}
		
		Pbl pblRetorno = pblRepository.save(pbl);
		
		try {
			System.out.println(pblRepository.findById(pblRetorno.getIdPbl()).get());
			notificationService.sendEmailPbl(pblRepository.findById(pblRetorno.getIdPbl()).get());
		} catch (MailException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
			
		}
		atividadeService.bindPblToAtividadePBl(pblRetorno);

		return pblRetorno;

	}
	
}
