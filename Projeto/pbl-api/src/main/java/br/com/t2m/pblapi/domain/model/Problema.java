package br.com.t2m.pblapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "problema")
public class Problema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5052318289651503998L;
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_problema")
	private Long idProblema;
	
	private String descricao;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataRegistro;
	
	@NotBlank
	private String prioridade;	
	
	public Problema(Problema problema) {
		this.idProblema = problema.getIdProblema();
		this.descricao = problema.getDescricao();
		this.dataRegistro = problema.getDataRegistro();
		this.prioridade = problema.getPrioridade();
	}
	
	public Problema() {
		
	}

	public Long getIdProblema() {
		return idProblema;
	}

	public void setIdProblema(Long idProblema) {
		this.idProblema = idProblema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProblema == null) ? 0 : idProblema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problema other = (Problema) obj;
		if (idProblema == null) {
			if (other.idProblema != null)
				return false;
		} else if (!idProblema.equals(other.idProblema))
			return false;
		return true;
	}

	public void setAtivo(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setExcluido(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
