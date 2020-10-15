package br.com.t2m.pblapi.domain.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.model.Usuario;
import br.com.t2m.pblapi.domain.service.dto.UsuarioDTO;

@Service
public class UsuarioMapper {

	public List<UsuarioDTO> usuariosToUsuarioDTOs(List<Usuario> usuarios) {
		return usuarios.stream().filter(Objects::nonNull).map(this::usuarioToUsuarioDTO).collect(Collectors.toList());
	}

	public List<Usuario> usuarioDTOsToUsuarios(List<UsuarioDTO> usuariosDTOs) {
		return usuariosDTOs.stream().filter(Objects::nonNull).map(this::usuarioDTOTOUsuario)
				.collect(Collectors.toList());
	}

	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
		return new UsuarioDTO(usuario);
	}

	public Usuario usuarioDTOTOUsuario(UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null)
			return null;

		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(usuarioDTO.isAtivo());
		Set<Perfil> perfis = this.perfilsFromString(usuarioDTO.getPerfil());
		usuario.setPerfil(perfis);
		return usuario;
	}

	private Set<Perfil> perfilsFromString(Set<String> perfisAsString) {
		Set<Perfil> perfis = new HashSet<>();

		if (perfisAsString != null) {
			perfis = perfisAsString.stream().map(string -> {
				Perfil perfil = new Perfil();
				perfil.setRole(string);
				return perfil;
			}).collect(Collectors.toSet());
		}
		return perfis;
	}

	public Usuario usuarioFromId(Long id) {
		if (id == null) {
			return null;
		}

		Usuario usuario = new Usuario();
		usuario.setId(id);
		return usuario;
	}

}
