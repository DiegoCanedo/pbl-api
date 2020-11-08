package br.com.t2m.pblapi.domain.service.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.TipoContato;

public class ContatoDTO {
	
	
	private Long id;
	
	@NotNull
	private Long idEmpresa;
	
	@NotBlank
	private String nomeContato;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String contato;
	
	@NotNull
	private TipoContato tipoContato;
	
	public ContatoDTO(Contato contato) {
		this.id = contato.getId();
		this.nomeContato = contato.getNomeContato();
		this.email = contato.getEmail();
		this.contato = contato.getContato();
		this.tipoContato = contato.getTipoContato();
	}
	
	public ContatoDTO() {
		
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	
	

}
