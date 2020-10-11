package br.com.t2m.pblapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;

@Service
public class AlunoService {

	@Autowired
	IAlunoRepository alunoRepository;

	public List<Aluno> getAll() {
		return alunoRepository.findAll();
	}

}
