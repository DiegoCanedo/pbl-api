package br.com.t2m.pblapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.service.DisciplinaService;

@Controller
@CrossOrigin
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	DisciplinaService disciplinaService;

	@GetMapping
	public ResponseEntity<List<Disciplina>> listarTodos() {
		return ResponseEntity.ok().body(disciplinaService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> listarPorID(@PathVariable Long id) {
		return ResponseEntity.ok().body(disciplinaService.getById(id));
	}

	@PostMapping
	public ResponseEntity<Disciplina> cadastrar(@Valid @RequestBody Disciplina Disciplina) {
		return ResponseEntity.ok().body(disciplinaService.insert(Disciplina));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Disciplina> atualizar(@Valid @RequestBody Disciplina Disciplina, @PathVariable Long id) {
		return ResponseEntity.ok().body(disciplinaService.update(Disciplina, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		disciplinaService.delete(id);
		return ResponseEntity.ok().body(Constants.DISCIPLINA_EXCLUIDA);
	}
}
