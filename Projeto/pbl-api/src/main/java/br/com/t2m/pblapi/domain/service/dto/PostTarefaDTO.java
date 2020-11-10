package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.t2m.pblapi.domain.model.Tarefa;

public class PostTarefaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;	
	
	public PostTarefaDTO() {
		
	}	
	
	public PostTarefaDTO(Tarefa entidade) {
		super();		
		this.descricao = entidade.getDescricao();
		this.dataConclusao = entidade.getDataConclusao();		
	}	

	public String getDescricao() {
		return descricao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}	

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}		

}
