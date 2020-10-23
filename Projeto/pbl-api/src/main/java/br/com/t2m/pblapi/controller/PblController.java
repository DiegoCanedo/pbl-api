package br.com.t2m.pblapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.service.PblService;
import io.swagger.annotations.Api;

@RequestMapping("/pbl")
@CrossOrigin
@RestController
@Api(description = "rest api para pbl", tags= {"PBL"})
public class PblController {

	@Autowired
	PblService service;

	@GetMapping
	public ResponseEntity<List<Pbl>> listarTodos() {

		try {
			return ResponseEntity.ok().body(this.service.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PostMapping
	public ResponseEntity<Pbl> incluir(@Valid @RequestBody Pbl pbl) {
		return ResponseEntity.ok().body(this.service.insert(pbl));
	}

}
