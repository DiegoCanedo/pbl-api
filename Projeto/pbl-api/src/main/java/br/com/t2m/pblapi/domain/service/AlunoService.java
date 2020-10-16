package br.com.t2m.pblapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;

@Service
public class AlunoService {

	@Autowired
	IAlunoRepository alunoRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Iterable<Aluno> getAll() {
		return alunoRepository.findAll();
	}

	public Aluno insert(Aluno aluno) {
		aluno.setSenha(passwordEncoder.encode(aluno.getSenha()));
		return alunoRepository.save(aluno);
	}

}
