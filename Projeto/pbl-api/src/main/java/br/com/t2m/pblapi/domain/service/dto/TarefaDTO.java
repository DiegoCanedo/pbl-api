package br.com.t2m.pblapi.domain.service.dto;

import java.util.Date;

import br.com.t2m.pblapi.domain.model.Tarefa;

public class TarefaDTO {
	
	private Long id;
	private String titulo;
	private Date dataCriacao;
	private String descricao;
	private Date dataConclusao;
	private boolean concluido;
	
	
	public TarefaDTO(Tarefa entidade) {
		super();
		this.id = entidade.getId();
		this.titulo = entidade.getTitulo();
		this.dataCriacao = entidade.getDataCriacao();
		this.descricao = entidade.getDescricao();
		this.dataConclusao = entidade.getDataConclusao();
		this.concluido = entidade.isConcluido();
	}	

	public String getTitulo() {
		return titulo;
	}
	
	public Long getId() {
		return id;
	}


	public Date getDataCriacao() {
		return dataCriacao;
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
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
