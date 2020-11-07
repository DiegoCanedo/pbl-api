package br.com.t2m.pblapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping(value = "/atividades/{id}/tarefas")
public class TarefaController {	
	
	@Autowired
	private TarefaService tarefaService;
	
	@PostMapping
	public ResponseEntity<TarefaDTO> postTarefa(
			@PathVariable("atividadeId") Long atividadeId, @RequestBody PostTarefaDTO novaTarefa){		
		TarefaDTO tarefa = tarefaService.postTarefa(atividadeId, novaTarefa);
		return ResponseEntity.ok().body(tarefa);
	}
	
	@PutMapping
	public ResponseEntity<TarefaDTO> alterarTarefa(@RequestBody PutTarefaDTO novosDados){
		TarefaDTO tarefa = tarefaService.putTarefa(novosDados);
		return ResponseEntity.ok().body(tarefa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long id){
		tarefaService.deleteTarefa(id);
		return ResponseEntity.ok().body("Tarefa " + id.toString() + " excluido com sucesso.");
	}

}
