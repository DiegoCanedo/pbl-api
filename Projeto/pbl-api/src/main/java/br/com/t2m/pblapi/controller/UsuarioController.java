package br.com.t2m.pblapi.controller;

import javax.persistence.PostRemove;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.domain.model.Usuario;
import br.com.t2m.pblapi.domain.service.UsuarioService;

@RestController
@RequestMapping("/registar")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario){
		return ResponseEntity.ok().body(usuarioService.register(usuario));
	}
	
}
