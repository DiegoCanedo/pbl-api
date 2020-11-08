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
import br.com.t2m.pblapi.domain.service.ProblemaService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/problema")
@Api(description = "rest api para problema", tags= {"Problema"})
public class ProblemaController {

	@Autowired
	ProblemaService problemaService;
	
	@GetMapping
	public ResponseEntity<Iterable<Problema>> listarTodos() {
		return ResponseEntity.ok().body(problemaService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Problema> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(problemaService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Problema> cadastrar(@Valid @RequestBody Problema problema) {
		return ResponseEntity.ok().body(problemaService.insert(problema));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Problema> alterar(@Valid @RequestBody Problema problema, @PathVariable Long id){
		return ResponseEntity.ok().body(problemaService.update(problema));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		problemaService.delete(id);
		return ResponseEntity.ok().body("Problema " + id.toString() + " excluído com sucesso.");
	}
	
}
