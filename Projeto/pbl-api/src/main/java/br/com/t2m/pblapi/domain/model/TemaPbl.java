package br.com.t2m.pblapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tema_pbl")
public class TemaPbl {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTemaPbl;

	private String descricao;

	public Long getIdTemaPbl() {
		return idTemaPbl;
	}

	public void setIdTemaPbl(Long idTemaPbl) {
		this.idTemaPbl = idTemaPbl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTemaPbl == null) ? 0 : idTemaPbl.hashCode());
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
		TemaPbl other = (TemaPbl) obj;
		if (idTemaPbl == null) {
			if (other.idTemaPbl != null)
				return false;
		} else if (!idTemaPbl.equals(other.idTemaPbl))
			return false;
		return true;
	}
	
	
}
