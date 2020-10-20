package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Transactional
@Service
public class DisciplinaService {

	@Autowired
	IDisciplinaRepository disciplinaRepository;

	@Transactional(readOnly = true)
	public List<Disciplina> getAll() {
		return disciplinaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Disciplina getById(Long id) {
		Optional<Disciplina> opt = disciplinaRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, id.toString());

		return opt.get();
	}

	public Disciplina insert(Disciplina disciplina) {
		Optional<Disciplina> opt = disciplinaRepository.findByNomeContainingIgnoreCase(disciplina.getNome());

		if (opt.isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.DISCIPLINA_JA_EXISTE, disciplina.getNome());
		}

		return disciplinaRepository.save(disciplina);
	}

	public Disciplina update(Disciplina disciplina, Long id) {
		Optional<Disciplina> opt = disciplinaRepository.findById(id);

		opt.ifPresentOrElse(pbl -> validaDisciplinaExistente(pbl, disciplina), () -> {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, id.toString());
		});

		Disciplina discip = opt.get();
		discip.setId(id);
		return disciplinaRepository.save(discip);
	}

	public void delete(Long id) {
		Optional<Disciplina> opt = disciplinaRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, id.toString());

		disciplinaRepository.delete(opt.get());
	}

	private void validaDisciplinaExistente(Disciplina antiga, Disciplina nova) {

		if (!antiga.getNome().equalsIgnoreCase(nova.getNome()) && disciplinaRepository.existsByNome(nova.getNome())) {
			throw new ResourceAlreadyExistsException(Constants.DISCIPLINA_JA_EXISTE, nova.getNome());
		}
	}

}
