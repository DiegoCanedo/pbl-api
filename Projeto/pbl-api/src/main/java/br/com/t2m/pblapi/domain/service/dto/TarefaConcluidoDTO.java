package br.com.t2m.pblapi.domain.service.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarefaConcluidoDTO {

	private Long id;
	
	private Long idAtividade;
	
	private boolean concluido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}
	
	

	
	
}
