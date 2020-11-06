package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "empresa")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Empresa extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3942248280086506347L;

	@NotBlank
	private String nome;

	@NotBlank
	private String cnpj;

	private String endereco;

	private String urlLog;

	// private String focalPoint;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUrlLog() {
		return urlLog;
	}

	public void setUrlLog(String urlLog) {
		this.urlLog = urlLog;
	}

}
