package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "perfil")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Perfil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String role;

	@NotEmpty
	private String descricao;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Perfil)) {
			return false;
		}
		return Objects.equals(role, ((Perfil) o).role);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(role);
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Perfil{" + "role='" + role + '\'' + "}";
	}

}
