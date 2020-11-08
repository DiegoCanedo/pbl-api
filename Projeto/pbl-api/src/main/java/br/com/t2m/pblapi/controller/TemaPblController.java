package br.com.t2m.pblapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.service.TemaPblService;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/temaPbl")
@Api(description = "rest api para tema pbl", tags= {"Tema PBL"})
@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
public class TemaPblController {

	@Autowired
	TemaPblService temaPblService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
	public ResponseEntity<List<TemaPbl>> listarTodos() {
		return ResponseEntity.ok().body(temaPblService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TemaPbl> listarPorID(@PathVariable Long id) {
		return ResponseEntity.ok().body(temaPblService.getById(id));
	}
	
	@GetMapping("/disciplina/{id}")
	public ResponseEntity<List<TemaPbl>> listarPorIDDisciplina(@PathVariable Long id) {
		return ResponseEntity.ok().body(temaPblService.getByIdDisciplina(id));
	}

	@PostMapping
	public ResponseEntity<TemaPbl> cadastrar(@Valid @RequestBody TemaPbl temaPbl) {
		return ResponseEntity.ok().body(temaPblService.insert(temaPbl));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TemaPbl> atualizar(@Valid @RequestBody TemaPbl temaPbl, @PathVariable Long id) {
		return ResponseEntity.ok().body(temaPblService.update(temaPbl, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		temaPblService.delete(id);
		return ResponseEntity.ok().body("Tema excluido com sucesso");
	}

}
