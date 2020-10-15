package br.com.t2m.pblapi.domain.service.dto;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.model.Usuario;

public class UsuarioDTO {

	private Long id;

	@NotBlank
	@Email(regexp = Constants.EMAIL_REGEX, message = "e-mail deve estar em um formato válido.")
	private String email;

	private boolean ativo = false;

	private Set<String> perfil;

	public UsuarioDTO() {
		// Construtor vazio, necessário para o Jackson.
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.ativo = usuario.isAtivo();
		this.perfil = usuario.getPerfil().stream().map(Perfil::getRole).collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<String> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<String> perfil) {
		this.perfil = perfil;
	}

}
