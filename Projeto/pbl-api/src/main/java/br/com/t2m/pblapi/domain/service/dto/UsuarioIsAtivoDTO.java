package br.com.t2m.pblapi.domain.service.dto;

import javax.validation.constraints.NotNull;

import br.com.t2m.pblapi.domain.model.Aluno;

public class UsuarioIsAtivoDTO {

	@NotNull
	private boolean ativo;

	public UsuarioIsAtivoDTO() {

	}

	public UsuarioIsAtivoDTO(Aluno aluno) {
		this.ativo = aluno.isAtivo();
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
