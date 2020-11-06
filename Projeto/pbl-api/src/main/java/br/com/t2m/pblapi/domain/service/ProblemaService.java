package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Problema;
import br.com.t2m.pblapi.domain.repository.IProblemaRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProblemaService {
	
	@Autowired
	IProblemaRepository problemaRepository;
	
	@Transactional(readOnly = true)
	public List<Problema> getAll() {
		return problemaRepository.findAll().stream().map(Problema::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Problema getById(Long id) {
		Optional<Problema> opt = problemaRepository.findByIdProblema(id).map(Problema::new);

		if (opt.isPresent())
			throw new ResourceNotFoundException(Constants.PROBLEMA_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	public Problema insert(Problema problema) {

		return problemaRepository.save(problema);

	}

	public void delete(Long id) {
		Optional<Problema> opt = problemaRepository.findByIdProblema(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.PROBLEMA_NAO_ENCONTRADO, id.toString());

		
		
		Problema problema = opt.get();
		//problema.setExcluido(true);
		//problema.setAtivo(false);
		problemaRepository.save(problema);
	}

}
