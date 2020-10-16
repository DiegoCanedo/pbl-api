package br.com.t2m.pblapi.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.repository.ITemaPblRepository;

@Transactional
@Service
public class TemaPblService {

	@Autowired
	ITemaPblRepository temaPblRepository;
	
	public List<TemaPbl> getAll() {
		return temaPblRepository.findAll();
	}
	
	public TemaPbl insert(TemaPbl temaPbl) {
		return temaPblRepository.save(temaPbl);
	}
}
