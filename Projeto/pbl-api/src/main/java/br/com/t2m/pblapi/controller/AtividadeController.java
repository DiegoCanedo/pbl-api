package br.com.t2m.pblapi.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.AtividadePbl;
import br.com.t2m.pblapi.domain.service.AtividadeService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/atividades")
@Api(description = "rest api para atividades", tags = { "Atividade" })
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Atividade> litarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.getById(id));
	}

	@GetMapping("/disciplina/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Set<Atividade>> listarPorIdDisciplina(@PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.getByDisciplina(id));
	}

	@GetMapping("/pbl/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Set<Atividade>> listarPorIdPbl(@PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.getByPbl(id));
	}

	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR,ROLE_ALUNO')")
	@GetMapping("/atividade-aluno/{id}")
	public ResponseEntity<List<Atividade>> listarPorIdAluno(@PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.getByIdAluno(id));
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Atividade> cadastrar(@Valid @RequestBody Atividade atividade) {
		return ResponseEntity.ok().body(atividadeService.insert(atividade));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<Atividade> alterar(@Valid @RequestBody Atividade atividade, @PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.update(atividade, id));
	}

	@PutMapping("/atividade-pbl/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<AtividadePbl> alterarAtividadePbl(@Valid @RequestBody Atividade atividade,
			@PathVariable Long id) {
		return ResponseEntity.ok().body(atividadeService.updateAtividadePbl(atividade, id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		atividadeService.delete(id);
		return ResponseEntity.ok().body("Atividade " + id.toString() + " excluido com sucesso.");
	}
}
