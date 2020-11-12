package br.com.t2m.pblapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.TarefaService;
import br.com.t2m.pblapi.domain.service.dto.PostTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.PutTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.TarefaDTO;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/atividades/{idAtividade}/tarefas")
@CrossOrigin
@Api(description = "rest api para tarefas dos alunos", tags = { "Tarefas" })
@PreAuthorize("hasAnyRole('ROLE_PROFESSOR,ROLE_ALUNO')")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@PostMapping
	public ResponseEntity<TarefaDTO> postTarefa(@PathVariable Long idAtividade, @RequestBody PostTarefaDTO novaTarefa) {
		TarefaDTO tarefa = tarefaService.postTarefa(idAtividade, novaTarefa);
		return ResponseEntity.ok().body(tarefa);
	}

	@PutMapping("/{idTarefa}")
	public ResponseEntity<TarefaDTO> alterarTarefa(@PathVariable Long idAtividade, @PathVariable Long idTarefa,
			@RequestBody PutTarefaDTO novosDados) {
		TarefaDTO tarefa = tarefaService.putTarefa(idAtividade, idTarefa, novosDados);
		return ResponseEntity.ok().body(tarefa);
	}

	@DeleteMapping("/{idTarefa}")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long idAtividade, @PathVariable Long idTarefa) {
		tarefaService.deleteTarefa(idAtividade, idTarefa);
		return ResponseEntity.ok().body("Tarefa " + idTarefa.toString() + " excluido com sucesso.");
	}

	@PatchMapping("/{idTarefa}/add/{idAluno}")
	public ResponseEntity<TarefaDTO> addAlunoIntoTarefa(@PathVariable Long idAtividade, @PathVariable Long idTarefa,
			@PathVariable Long idAluno) {

		TarefaDTO tarefa = tarefaService.addAlunoIntoTarefa(idAtividade, idTarefa, idAluno);
		return ResponseEntity.ok().body(tarefa);
	}

	@PatchMapping("/{idTarefa}/remove/{idAluno}")
	public ResponseEntity<TarefaDTO> removeAlunoFromTarefa(@PathVariable Long idAtividade, @PathVariable Long idTarefa,
			@PathVariable Long idAluno) {

		TarefaDTO tarefa = tarefaService.removeAlunoFromTarefa(idAtividade, idTarefa, idAluno);
		return ResponseEntity.ok().body(tarefa);
	}
}
