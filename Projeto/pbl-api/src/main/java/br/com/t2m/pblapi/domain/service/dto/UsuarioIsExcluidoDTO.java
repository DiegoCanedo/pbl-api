package br.com.t2m.pblapi.domain.service.dto;

import br.com.t2m.pblapi.domain.model.Usuario;

public class UsuarioIsExcluidoDTO {

	private boolean excluido;

	public UsuarioIsExcluidoDTO() {

	}

	public UsuarioIsExcluidoDTO(Usuario usuario) {
		this.excluido = usuario.isExcluido();
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
