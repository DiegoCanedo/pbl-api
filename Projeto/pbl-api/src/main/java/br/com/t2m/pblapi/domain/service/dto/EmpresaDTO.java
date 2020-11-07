package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.model.Perfil;


public class EmpresaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -838231074945922732L;

	private Long id;

	@NotBlank
	private String nome;
	
	private String endereco;
	
	private String urlLog;
	
	@NotBlank
	private String cnpj;
	
	@NotBlank
	@Email(regexp = Constants.EMAIL_REGEX, message = "e-mail deve estar em um formato válido.")
	@Size(min = 5, max = 254)
	private String email;

	private boolean ativo;
	
	private boolean excluido;
	
	private Set<Perfil> perfis;

	public EmpresaDTO(Empresa empresa) {
		this.id = empresa.getId();
		this.email = empresa.getEmail();
		this.nome = empresa.getNome();
		this.cnpj = empresa.getCnpj();
		this.endereco = empresa.getEndereco();
		this.urlLog = empresa.getUrlLog();
		this.perfis = empresa.getPerfil();
		this.ativo = empresa.isAtivo();
		this.excluido = empresa.isExcluido();
	}
	
	public EmpresaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}


	
	
}
