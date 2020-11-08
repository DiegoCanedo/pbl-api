package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.AlunoService;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/aluno")
@Api(description = "rest api para aluno", tags= {"Aluno"})
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	@GetMapping

	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Iterable<AlunoDTO>> listarTodos() {
		return ResponseEntity.ok().body(alunoService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoService.getById(id));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR,ROLE_ALUNO')")
	public ResponseEntity<AlunoDTO> alterar(@Valid @RequestBody AlunoDTO aluno, @PathVariable Long id){
		return ResponseEntity.ok().body(alunoService.update(aluno));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		alunoService.delete(id);
		return ResponseEntity.ok().body("Usuario " + id.toString() + " excluido com sucesso.");
	}
}
