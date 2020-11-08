package br.com.t2m.pblapi.config.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.t2m.pblapi.domain.model.Usuario;

public class PblUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	@JsonIgnore
	private String senha;

	private Collection<? extends GrantedAuthority> authorities;

	public PblUserDetails(Long id, String email, String senha, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = authorities;
	}

	public static PblUserDetails build(Usuario usuario) {
		
		
		List<GrantedAuthority> authorities = usuario.getPerfil().stream()
				.map(p -> new SimpleGrantedAuthority(p.getNome().name())).collect(Collectors.toList());
		return new PblUserDetails(usuario.getId(), usuario.getEmail(), usuario.getSenha(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PblUserDetails user = (PblUserDetails) o;
		return Objects.equals(id, user.id);
	}
}
