package br.com.t2m.pblapi.domain.service;

/*import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.Problema;
import br.com.t2m.pblapi.domain.repository.IContatoRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyBounded;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;*/

//@Service
public class ContatoService {
	
	/*@Autowired
	IContatoRepository contatoRepository;
	
	@Transactional(readOnly = true)
	public List<Contato> getAll() {
		return contatoRepository.findAll().stream().map(Contato::new).collect(Collectors.toList());
	}

	
	public Contato insert(Contato contato) {
		if(contatoRepository.existsByNomeContato(contato.getNomeContato()))
			throw new ResourceAlreadyExistsException(Constants.CONTATO_JA_EXISTE, contato.getNomeContato());
		return contatoRepository.save(contato);
	}
	
	
	public void delete() {
				
		if (contatoRepository.existsByNomeContato(contato.getNomeContato()))
			throw new ResourceNotFoundException(Constants.CONTATO_NAO_ENCONTRADO, contato.getNomeContato());
	}else if (contatoRepository.existsByNomeContato(contato.getNomeContato())) {
			throw new ResourceAlreadyBounded(Constants.CONTATO_VINCULADO);

		contatoRepository.delete(contatos.get());
	}*/


}
