package br.com.t2m.pblapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;

@Transactional
@Service
public class DisciplinaService {

	@Autowired
	IDisciplinaRepository disciplinaRepository;
	
}
