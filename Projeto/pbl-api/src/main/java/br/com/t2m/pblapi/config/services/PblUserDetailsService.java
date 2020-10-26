package br.com.t2m.pblapi.config.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Usuario;
import br.com.t2m.pblapi.domain.repository.IUsuarioRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class PblUserDetailsService implements UserDetailsService {
	@Autowired
	IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByAtivoIsTrueAndExcluidoIsFalseAndEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com email: " + email));
		
		return PblUserDetails.build(usuario);
	}
	
	
}
