package br.com.t2m.pblapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Usuario;
import br.com.t2m.pblapi.domain.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	public Usuario register(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
