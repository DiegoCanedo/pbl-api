package br.com.t2m.pblapi.domain.service.dto;

import java.util.List;

import br.com.t2m.pblapi.domain.model.Aluno;

public class TarefaPblDTO {

	private Long idPbl;
	private List<Aluno> alunosPbl;
	private List<AtividadeTarefaDTO> atividadeTarefaDTOs;

	public Long getIdPbl() {
		return idPbl;
	}

	public void setIdPbl(Long idPbl) {
		this.idPbl = idPbl;
	}

	public List<Aluno> getAlunosPbl() {
		return alunosPbl;
	}

	public void setAlunosPbl(List<Aluno> alunosPbl) {
		this.alunosPbl = alunosPbl;
	}

	public List<AtividadeTarefaDTO> getAtividadeTarefaDTOs() {
		return atividadeTarefaDTOs;
	}

	public void setAtividadeTarefaDTOs(List<AtividadeTarefaDTO> atividadeTarefaDTOs) {
		this.atividadeTarefaDTOs = atividadeTarefaDTOs;
	}

}
