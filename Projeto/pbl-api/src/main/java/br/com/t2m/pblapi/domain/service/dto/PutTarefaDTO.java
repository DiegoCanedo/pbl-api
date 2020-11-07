package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PutTarefaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;	
	private boolean concluido;
	
	
	public PutTarefaDTO(TarefaDTO entidade) {
		super();
		this.id = entidade.getId();
		this.descricao = entidade.getDescricao();
		this.dataConclusao = entidade.getDataConclusao();
		this.concluido = entidade.isConcluido();
	}


	public Long getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}


	public Date getDataConclusao() {
		return dataConclusao;
	}


	public boolean isConcluido() {
		return concluido;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}


	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}	

}
