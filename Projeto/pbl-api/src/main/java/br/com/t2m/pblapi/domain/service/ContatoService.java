package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.repository.IContatoRepository;
import br.com.t2m.pblapi.domain.repository.IEmpresaRepository;
import br.com.t2m.pblapi.domain.service.dto.ContatoDTO;
import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;
import br.com.t2m.pblapi.domain.service.mapper.ContatoMapper;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class ContatoService {

	@Autowired
	IContatoRepository contatoRepository;
	
	@Autowired
	IEmpresaRepository empresaRepository;
	
	@Autowired
	ContatoMapper contatoMapper;

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
	
	public ContatoDTO insert(ContatoDTO contatoDTO) {
		Optional<Empresa> optEmpresa = empresaRepository.findById(contatoDTO.getIdEmpresa());

		if (optEmpresa.isEmpty())
			throw new ResourceNotFoundException(Constants.EMPRESA_NÃO_ENCONTRADA, contatoDTO.getIdEmpresa().toString());
		
		Empresa empresaBanco = optEmpresa.get();
		
		
//		empresaBanco.setContato(empresaBanco.getContato()
//				.stream()
//				.map(c -> new ContatoMapper().ContatoDTOTOContato(contatoDTO)));

		
		return new ContatoDTO();
	}
}
