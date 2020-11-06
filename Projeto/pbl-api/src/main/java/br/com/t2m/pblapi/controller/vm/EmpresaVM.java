package br.com.t2m.pblapi.controller.vm;

import javax.validation.constraints.Size;

import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;

public class EmpresaVM extends EmpresaDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7282497245847966074L;
	@Size(min = 6, max = 50)
	private String senha;

	public EmpresaVM() {

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
