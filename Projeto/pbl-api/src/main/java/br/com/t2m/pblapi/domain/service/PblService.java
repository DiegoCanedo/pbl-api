package br.com.t2m.pblapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.repository.IPblRepository;

@Service
@Transactional
public class PblService {
	
	@Autowired
	IPblRepository pblRepository;
	
	public Pbl insert(Pbl pbl) {
		return pblRepository.save(pbl);
	}
}
