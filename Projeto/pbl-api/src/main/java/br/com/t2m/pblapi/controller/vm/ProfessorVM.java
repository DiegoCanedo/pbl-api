package br.com.t2m.pblapi.controller.vm;

import javax.validation.constraints.Size;

import br.com.t2m.pblapi.domain.service.dto.ProfessorDTO;

public class ProfessorVM extends ProfessorDTO {
	
	@Size(min = 4, max = 100)
	private String senha;

	public ProfessorVM() {

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
