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
import br.com.t2m.pblapi.domain.service.ProfessorService;
import br.com.t2m.pblapi.domain.service.dto.ProfessorDTO;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/professor")
@Api(description = "rest api para professor", tags= {"Professor"})
public class ProfessorController {
	@Autowired
	ProfessorService professorService;

	@GetMapping
	public ResponseEntity<Iterable<ProfessorDTO>> listarTodos() {
		return ResponseEntity.ok().body(professorService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(professorService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDTO> alterar(@Valid @RequestBody ProfessorDTO professor, @PathVariable Long id){
		return ResponseEntity.ok().body(professorService.update(professor));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		professorService.delete(id);
		return ResponseEntity.ok().body("Usuario " + id.toString() + " excluido com sucesso.");
	}
}
