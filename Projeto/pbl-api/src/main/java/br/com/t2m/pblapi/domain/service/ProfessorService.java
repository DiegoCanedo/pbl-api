package br.com.t2m.pblapi.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.repository.IProfessorRepository;

@Service
@Transactional
public class ProfessorService {

	private final String msgProfessorNaoExiste = "Não existe professor com id: ";

	@Autowired
	IProfessorRepository professorRepository;

}
