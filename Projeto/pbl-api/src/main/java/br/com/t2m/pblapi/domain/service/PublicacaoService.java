package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.model.Publicacao;
import br.com.t2m.pblapi.domain.repository.IProfessorRepository;
import br.com.t2m.pblapi.domain.repository.IPublicacaoRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class PublicacaoService {
	
	@Autowired
	IPublicacaoRepository publicacaoRepository;
	
	@Autowired
	IProfessorRepository professorRepository;
	
	@Transactional(readOnly = true)
	public List<Publicacao> getAll() {
		return publicacaoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Publicacao getById(Long id) {
		Optional<Publicacao> opt = publicacaoRepository.findById(id);

		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PUBLICACAO_NAO_ENCONTRADA, id.toString());

		return opt.get();
	}
	
	public Publicacao insert(Publicacao publicacao) {
		Optional<Professor> optProfessor = professorRepository.findById(publicacao.getIdUsuario());

		if (optProfessor.isEmpty())
			throw new ResourceNotFoundException(Constants.PROFESSOR_NÃO_ENCONTRADO, publicacao.getIdUsuario().toString());

		publicacao.setIdUsuario(optProfessor.get().getId());
		return publicacaoRepository.save(publicacao);
	}
	
	@Transactional
	public Publicacao update(Publicacao publicacao) {
		Optional<Publicacao> opt = publicacaoRepository.findById(publicacao.getIdPublicacao());
		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PUBLICACAO_NAO_ENCONTRADA);
		
		Publicacao pub = opt.get(); 
		pub.setTitulo(publicacao.getTitulo());
		pub.setResumo(publicacao.getResumo());
		pub.setTexto(publicacao.getTexto());
		return publicacaoRepository.save(pub);
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<Publicacao> opt = publicacaoRepository.findById(id);		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.PUBLICACAO_NAO_ENCONTRADA, id.toString());

		publicacaoRepository.delete(opt.get());
	}

}
