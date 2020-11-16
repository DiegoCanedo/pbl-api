package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Tarefa;

public class PostTarefaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 290029691832673676L;

	private Long idAtividade;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;	
	
	private List<Aluno> alunos;	
	
	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getTitulo() {
		return titulo;
	}	

	public String getDescricao() {
		return descricao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}		
	
	

}
