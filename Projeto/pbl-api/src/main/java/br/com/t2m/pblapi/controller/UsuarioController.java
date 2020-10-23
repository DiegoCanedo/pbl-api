package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.controller.vm.AlunoVM;
import br.com.t2m.pblapi.domain.service.AlunoService;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsExcluidoDTO;
import io.swagger.annotations.Api;

@RequestMapping("/usuario")
@CrossOrigin
@RestController
@Api(description = "rest api para usuario", tags = { "Usuario" })
public class UsuarioController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping("/aluno/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarAluno(@Valid @RequestBody AlunoVM aluno) {
		alunoService.insert(aluno, aluno.getSenha());
	}

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
