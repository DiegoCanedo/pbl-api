package br.com.t2m.pblapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.service.AlunoService;
import br.com.t2m.pblapi.domain.service.dto.AlunoAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;

@RestController
@CrossOrigin
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	@GetMapping
	public ResponseEntity<Iterable<AlunoDTO>> listarTodos() {
		return ResponseEntity.ok().body(alunoService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> alterar(@Valid @RequestBody AlunoDTO aluno){
		return ResponseEntity.ok().body(alunoService.update(aluno));
	}
	
	@PutMapping("/altera-status-ativo/{id}")
	public ResponseEntity<AlunoDTO> alterarStatusAtivo(@Valid @RequestBody AlunoAtivoDTO aluno, @PathVariable Long id){
		return ResponseEntity.ok().body(alunoService.updateAtivo(aluno, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		alunoService.delete(id);
		return ResponseEntity.ok().body("Usuario " + id.toString() + " excluido com sucesso.");
	}
}
