package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_contato")
public class TipoContato implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -967647814911941252L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_contato")
	private long idTipoContato;
	
	private String descricao;

	public long getIdTipoContato() {
		return idTipoContato;
	}

	public void setIdTipoContato(long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTipoContato ^ (idTipoContato >>> 32));
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
		TipoContato other = (TipoContato) obj;
		if (idTipoContato != other.idTipoContato)
			return false;
		return true;
	}
	
}
