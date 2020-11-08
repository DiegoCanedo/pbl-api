package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.service.ContatoService;
import br.com.t2m.pblapi.domain.service.dto.ContatoDTO;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/empresa/contato")
@Api(description = "rest api para contatos das empresas", tags= {"Contato"})
public class ContatoController {

	@Autowired
	ContatoService contatoService;
	
	@PostMapping
	public ResponseEntity<ContatoDTO> cadastrar(@Valid @RequestBody ContatoDTO contatoDTO) {
		return ResponseEntity.ok().body(contatoService.insert(contatoDTO));
	}
}
