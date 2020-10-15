package br.com.t2m.pblapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.service.AlunoService;

@RestController
@CrossOrigin
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	@GetMapping
	public ResponseEntity<List<Aluno>> listarTodos() {
		return ResponseEntity.ok().body(alunoService.getAll());
	}
	
//	@PostMapping
//	public ResponseEntity<Aluno> listarTodos(Aluno aluno) {
//		return ResponseEntity.ok().body(alunoService.getAll());
//	}
}
