package br.com.t2m.pblapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.controller.vm.AlunoVM;
import br.com.t2m.pblapi.domain.service.AlunoService;

@RestController
public class UsuarioController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping("/aluno/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarAluno(@Valid @RequestBody AlunoVM aluno) {
		alunoService.insert(aluno, aluno.getSenha());
	}

}
