package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.EmpresaService;
import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/empresa")
@Api(description = "rest api para empresa", tags= {"Empresa"})
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<Iterable<EmpresaDTO>> listarTodos() {
		return ResponseEntity.ok().body(empresaService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDTO> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(empresaService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmpresaDTO> alterar(@Valid @RequestBody EmpresaDTO empresa, @PathVariable Long id){
		return ResponseEntity.ok().body(empresaService.update(empresa));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		empresaService.delete(id);
		return ResponseEntity.ok().body("Empresa " + id.toString() + " excluido com sucesso.");
	}
}
