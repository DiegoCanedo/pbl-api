package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.service.ContatoService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/empresa/contato")
@Api(description = "rest api para contatos das empresas", tags= {"Contato"})
public class ContatoController {

	@Autowired
	ContatoService contatoService;
	
	@PostMapping
	public ResponseEntity<Contato> cadastrar(@Valid @RequestBody Contato contato) {
		return ResponseEntity.ok().body(contatoService.insert(contato));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> alterar(@Valid @RequestBody Contato contato, @PathVariable Long id) {
		return ResponseEntity.ok().body(contatoService.update(contato));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		contatoService.delete(id);
		return ResponseEntity.ok().body("Contato " + id.toString() + " excluído com sucesso.");
	}
}
