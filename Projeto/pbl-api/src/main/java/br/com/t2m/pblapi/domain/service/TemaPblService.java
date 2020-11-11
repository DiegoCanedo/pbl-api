package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;
import br.com.t2m.pblapi.domain.repository.ITemaPblRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Transactional
@Service
public class TemaPblService {

	@Autowired
	ITemaPblRepository temaPblRepository;

	@Autowired
	IDisciplinaRepository disciplinaRepository;

	@Transactional(readOnly = true)
	public List<TemaPbl> getAll() {
		return temaPblRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TemaPbl getById(Long id) {
		Optional<TemaPbl> opt = temaPblRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.TEMA_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	@Transactional(readOnly = true)
	public List<TemaPbl> getByIdDisciplina(Long id) {
		Optional<Disciplina> opt = disciplinaRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, id.toString());

		return temaPblRepository.findByDisciplinas_Id(opt.get().getId());
	}

	public TemaPbl insert(TemaPbl temaPbl) {
		temaPbl.getDisciplinas().stream().forEach(item -> {

			Optional<Disciplina> optDisciplina = disciplinaRepository.findById(item.getId());

			if (optDisciplina.isEmpty())
				throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, item.getId().toString());

		});

		validaTemaExistente(temaPbl);

		return temaPblRepository.save(temaPbl);
	}

	public TemaPbl update(TemaPbl temaPbl, Long id) {
		Optional<TemaPbl> opt = temaPblRepository.findById(id);

		opt.ifPresentOrElse(pbl -> validaTemaExistente(temaPbl), () -> {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, temaPbl.getId().toString());
		});

		TemaPbl tema = opt.get();
		tema.setId(id);
		tema.setNome(temaPbl.getNome());
		tema.setDisciplinas(temaPbl.getDisciplinas());
		return temaPblRepository.save(tema);
	}

	public void delete(Long id) {
		Optional<TemaPbl> opt = temaPblRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.TEMA_NAO_ENCONTRADO, id.toString());

		temaPblRepository.delete(opt.get());
	}

	private void validaTemaExistente(TemaPbl temaPbl) {
		List<TemaPbl> temas = temaPblRepository.findByNomeContainingIgnoreCase(temaPbl.getNome());

		temas.stream().forEach(tema -> {
			if (tema.getNome().equalsIgnoreCase(temaPbl.getNome())) {
				throw new ResourceAlreadyExistsException(Constants.TEMA_JA_EXISTE, temaPbl.getNome());
			}
		});
	}
}
