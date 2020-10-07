package br.com.t2m.pblapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuario {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPerfilUsuario;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idPerfil")
	private Perfil perfil;

	public Long getIdPerfilUsuario() {
		return idPerfilUsuario;
	}

	public void setIdPerfilUsuario(Long idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPerfilUsuario == null) ? 0 : idPerfilUsuario.hashCode());
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
		PerfilUsuario other = (PerfilUsuario) obj;
		if (idPerfilUsuario == null) {
			if (other.idPerfilUsuario != null)
				return false;
		} else if (!idPerfilUsuario.equals(other.idPerfilUsuario))
			return false;
		return true;
	}
	
	 

}
