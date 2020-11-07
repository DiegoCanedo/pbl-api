package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.t2m.pblapi.config.Constants;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7768096444504586762L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", updatable = false)
	private Long id;

	@NotBlank
	@Column(length = 50)
	@Size(max = 50)
	@Email(regexp = Constants.EMAIL_REGEX, message = "e-mail deve estar em um formato válido.")
	private String email;

	@NotNull
	@JsonIgnore
	@Size(min = 60, max = 60)
	private String senha;

	@NotNull
	@Column(nullable = false)
	private boolean ativo;

	@NotNull
	@Column(nullable = false)
	private boolean excluido;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfil_usuario", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario") }, inverseJoinColumns = {
					@JoinColumn(name = "id_perfil", referencedColumnName = "id") })
	private Set<Perfil> perfil = new HashSet<>();

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String email, String senha) {

		this.email = email;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<Perfil> perfil) {
		this.perfil = perfil;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
