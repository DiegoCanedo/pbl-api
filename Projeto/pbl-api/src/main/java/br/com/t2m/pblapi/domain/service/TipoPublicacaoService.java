package br.com.t2m.pblapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.domain.model.TipoPublicacao;
import br.com.t2m.pblapi.domain.repository.ITipoPublicacaoRepository;

@Service
@Transactional
public class TipoPublicacaoService {
	
	@Autowired
	ITipoPublicacaoRepository tipoPublicacaoRepository;
	
	@Transactional(readOnly = true)
	public List<TipoPublicacao> getAll() {
		return tipoPublicacaoRepository.findAll();
	}


}
