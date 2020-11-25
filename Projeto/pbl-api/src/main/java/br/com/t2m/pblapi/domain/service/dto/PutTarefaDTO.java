package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Tarefa;

public class PutTarefaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;	
	private boolean concluido;
	private List<Aluno> alunos;	
	
	public PutTarefaDTO() {
		super();
	}
	
	public PutTarefaDTO(Tarefa entidade) {
		super();
		this.titulo = entidade.getTitulo();
		this.descricao = entidade.getDescricao();
		this.dataConclusao = entidade.getDataConclusao();
		this.concluido = entidade.isConcluido();
	}
	
	


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}


	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}	
	
	

}
