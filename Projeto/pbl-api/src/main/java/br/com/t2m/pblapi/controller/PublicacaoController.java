package br.com.t2m.pblapi.controller;

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

import br.com.t2m.pblapi.domain.model.Problema;
import br.com.t2m.pblapi.domain.model.Publicacao;
import br.com.t2m.pblapi.domain.service.PublicacaoService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/publicacao")
@Api(description = "rest api para publicacao", tags= {"Publicacao"})
public class PublicacaoController {
	
	@Autowired
	PublicacaoService publicacaoService;
	
	@GetMapping
	public ResponseEntity<Iterable<Publicacao>> listarTodos() {
		return ResponseEntity.ok().body(publicacaoService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publicacao> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(publicacaoService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Publicacao> cadastrar(@Valid @RequestBody Publicacao publicacao) {
		return ResponseEntity.ok().body(publicacaoService.insert(publicacao));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Publicacao> alterar(@Valid @RequestBody Publicacao publicacao, @PathVariable Long id){
		return ResponseEntity.ok().body(publicacaoService.update(publicacao));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		publicacaoService.delete(id);
		return ResponseEntity.ok().body("Problema " + id.toString() + " excluído com sucesso.");
	}

}
