package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.service.AlunoService;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsExcluidoDTO;
import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
@Api(description = "rest api para usuario", tags = { "Usuario" })
@PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
public class UsuarioController {

	@Autowired
	private AlunoService alunoService;	

	@PutMapping("/altera-status-ativo/{id}")
	public ResponseEntity<AlunoDTO> alterarStatusAtivo(@Valid @RequestBody UsuarioIsAtivoDTO usuario,
			@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoService.updateAtivo(usuario, id));
	}

	@PutMapping("/altera-status-excluido/{id}")
	public ResponseEntity<AlunoDTO> alterarStatusExcluido(@Valid @RequestBody UsuarioIsExcluidoDTO usuario,
			@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoService.updateExcluido(usuario, id));
	}

}
