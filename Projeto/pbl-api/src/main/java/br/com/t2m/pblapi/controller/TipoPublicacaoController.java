package br.com.t2m.pblapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.TipoPublicacao;
import br.com.t2m.pblapi.domain.service.TipoPublicacaoService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/tipoPublicacao")
@Api(description = "rest api para tipoPublicacao", tags= {"TipoPublicacao"})
public class TipoPublicacaoController {

	@Autowired
	TipoPublicacaoService tipoPublicacaoService;
	
	@GetMapping
	public ResponseEntity<Iterable<TipoPublicacao>> listarTodos() {
		return ResponseEntity.ok().body(tipoPublicacaoService.getAll());
	}
}
