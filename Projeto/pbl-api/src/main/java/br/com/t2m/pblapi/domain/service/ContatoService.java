package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.model.Problema;
import br.com.t2m.pblapi.domain.repository.IContatoRepository;
import br.com.t2m.pblapi.domain.repository.IEmpresaRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyBounded;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class ContatoService {

	@Autowired
	IContatoRepository contatoRepository;

	@Autowired
	IEmpresaRepository empresaRepository;

	@Transactional(readOnly = true)
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Contato getById(Long id) {
		Optional<Contato> opt = contatoRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.CONTATO_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	public Contato insert(Contato contato) {
		Optional<Empresa> optEmpresa = empresaRepository.findById(contato.getIdUsuario());

		if (optEmpresa.isEmpty())
			throw new ResourceNotFoundException(Constants.EMPRESA_NÃO_ENCONTRADA, contato.getIdUsuario().toString());

		contato.setIdUsuario(optEmpresa.get().getId());
		return contatoRepository.save(contato);
	}
	
	@Transactional
	public Contato update(Contato contato) {
		Optional<Contato> opt = contatoRepository.findById(contato.getId());
		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.CONTATO_NAO_ENCONTRADO);
		
		Contato contatoBanco = opt.get(); 
		contatoBanco.setNomeContato(contato.getNomeContato());
		contatoBanco.setEmail(contato.getEmail());
		contatoBanco.setContato(contato.getContato());
		contatoBanco.setTipoContato(contato.getTipoContato());
		return contatoRepository.save(contatoBanco);
	}		
	
	@Transactional
	public void delete(Long id) {
		Optional<Contato> opt = contatoRepository.findById(id);		
		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.CONTATO_NAO_ENCONTRADO, id.toString());
		

		contatoRepository.delete(opt.get());
	}
}
