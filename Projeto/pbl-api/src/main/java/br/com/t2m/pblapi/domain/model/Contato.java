package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contato")
public class Contato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435614680358719514L;
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_contato")
	private long idContato;
	
	@NotBlank
	private String nomeContato;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String contato;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_contato")
	private TipoContato tipoContato  ;
	
	public Contato(Contato contato) {
		this.idContato = contato.getIdContato();
		this.nomeContato = contato.getNomeContato();
		this.email = contato.getContato();
		this.contato = contato.getContato();
		this.tipoContato = contato.getTipoContato();
	}
	
	public Contato() {
		
	}

	
	public long getIdContato() {
		return idContato;
	}

	public void setIdContato(long idContato) {
		this.idContato = idContato;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idContato ^ (idContato >>> 32));
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
		Contato other = (Contato) obj;
		if (idContato != other.idContato)
			return false;
		return true;
	}
	
	

		
}
