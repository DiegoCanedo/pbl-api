package br.com.t2m.pblapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.TipoContato;
import br.com.t2m.pblapi.domain.service.TipoContatoService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/tipoContato")
@Api(description = "rest api para tipoContato", tags= {"TipoContato"})
public class TipoContatoController {

	@Autowired
	TipoContatoService tipoContatoService;
	
	@GetMapping
	public ResponseEntity<Iterable<TipoContato>> listarTodos() {
		return ResponseEntity.ok().body(tipoContatoService.getAll());
	}
}
