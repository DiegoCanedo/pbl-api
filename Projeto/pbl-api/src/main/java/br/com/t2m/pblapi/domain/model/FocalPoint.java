package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


//@Entity
//@Table(name = "focalPoint")
public class FocalPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435614680358719514L;
	
	
	private long idFocalPoint;
	
	private String nome;
	
	private String email;
	
	private String contato;
	
	

	public long getIdFocalPoint() {
		return idFocalPoint;
	}

	public void setIdFocalPoint(long idFocalPoint) {
		this.idFocalPoint = idFocalPoint;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
}
