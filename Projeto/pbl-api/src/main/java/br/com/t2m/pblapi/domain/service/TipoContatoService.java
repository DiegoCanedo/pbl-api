package br.com.t2m.pblapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.domain.model.TipoContato;
import br.com.t2m.pblapi.domain.repository.ITipoContatoRepository;

@Service
public class TipoContatoService {
	
	@Autowired
	ITipoContatoRepository tipoContatoRepository;
	
	@Transactional(readOnly = true)
	public List<TipoContato> getAll() {
		return tipoContatoRepository.findAll();
	}

}
