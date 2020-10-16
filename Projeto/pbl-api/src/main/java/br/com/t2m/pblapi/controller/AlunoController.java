package br.com.t2m.pblapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Iterable<Aluno>> listarTodos() {
		return ResponseEntity.ok().body(alunoService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoService.getById(id));
	}

	@PostMapping
	public ResponseEntity<Aluno> incluir(@Valid @RequestBody Aluno aluno) {
		return ResponseEntity.ok().body(alunoService.insert(aluno));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alterar(@Valid @RequestBody Aluno aluno, @PathVariable Long id ){
		return ResponseEntity.ok().body(alunoService.update(aluno, id));
	}
}
