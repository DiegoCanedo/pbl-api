package br.com.t2m.pblapi.domain.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.AtividadePbl;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.repository.IAtividadeRepository;
import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;
import br.com.t2m.pblapi.domain.repository.IPblRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class AtividadeService {

	@Autowired
	private IAtividadeRepository atividadeRepository;

	@Autowired
	private IPblRepository pblRepository;

	@Autowired
	private IDisciplinaRepository disciplinaRepository;

	@Transactional(readOnly = true)
	public Set<AtividadePbl> getByPbl(Long idPbl) {
		Optional<Pbl> optPbl = pblRepository.findById(idPbl);

		if (optPbl.isEmpty()) {
			throw new ResourceNotFoundException(Constants.PBL_NAO_ENCONTRADO, idPbl.toString());
		}

		return atividadeRepository.findByAtividadePbls_Pbl(optPbl.get());
	}
	
	@Transactional(readOnly = true)
	public Set<Atividade> getByDisciplina(Long idDisciplina) {
		Optional<Disciplina> optDisciplina = disciplinaRepository.findById(idDisciplina);

		if (optDisciplina.isEmpty()) {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, idDisciplina.toString());
		}

		return atividadeRepository.findByDisciplina(optDisciplina.get());
	}

	public Atividade insert(Atividade atividade) {

		Optional<Disciplina> optDisciplina = disciplinaRepository.findById(atividade.getDisciplina().getId());

		if (optDisciplina.isEmpty()) {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA,
					atividade.getDisciplina().getId().toString());
		}

		Set<Pbl> pbls = pblRepository.findAllByTemaPbl_Disciplinas_Id(atividade.getDisciplina().getId());

		if (pbls.isEmpty()) {
			return atividadeRepository.save(atividade);
		}

		Set<AtividadePbl> atividadePbls = new HashSet<AtividadePbl>();
		pbls.stream().forEach(p -> {
			AtividadePbl atividadePbl = new AtividadePbl();
			atividadePbl.setPbl(p);
			atividadePbls.add(atividadePbl);
		});

		atividade.setAtividadePbls(atividadePbls);

		return atividadeRepository.save(atividade);
	}

	public void createAtividadePbl(Atividade atividade) {

	}

}
