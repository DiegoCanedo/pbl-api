package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Problema;
import br.com.t2m.pblapi.domain.repository.IPblRepository;
import br.com.t2m.pblapi.domain.repository.IProblemaRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyBounded;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProblemaService {
	
	@Autowired
	IProblemaRepository problemaRepository;
	
	@Autowired
	IPblRepository pblRepository;
	
	@Transactional(readOnly = true)
	public List<Problema> getAll() {
		return problemaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Problema getById(Long id) {
		Optional<Problema> opt = problemaRepository.findByIdProblema(id);

		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PROBLEMA_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	@Transactional
	public Problema insert(Problema problema) {

		return problemaRepository.save(problema);

	}
	
	@Transactional
	public Problema update(Problema problema) {
		Optional<Problema> opt = problemaRepository.findById(problema.getIdProblema());
		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PROBLEMA_NAO_ENCONTRADO);
		Problema problemaBanco = opt.get(); 
		problemaBanco.setDescricao(problema.getDescricao());
		problemaBanco.setPrioridade(problema.getPrioridade());
		problemaBanco.setAtivo(problema.isAtivo());
		return problemaRepository.save(problemaBanco);
	}		
	
	@Transactional
	public void delete(Long id) {
		Optional<Problema> opt = problemaRepository.findByIdProblema(id);		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PROBLEMA_NAO_ENCONTRADO, id.toString());
		
		if (pblRepository.existsByProblema(opt.get()))
			throw new ResourceAlreadyBounded(Constants.PROBLEMA_VINCULADO);

		problemaRepository.delete(opt.get());
	}

}
