package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.service.AtividadeService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/atividades")
@Api(description = "rest api para atividades", tags = { "Atividade" })
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;

	@PostMapping
	public ResponseEntity<Atividade> cadastrar(@Valid @RequestBody Atividade atividade) {
		return ResponseEntity.ok().body(atividadeService.insert(atividade));
	}
}
