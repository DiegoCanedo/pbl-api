package br.com.t2m.pblapi.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

//TODO Precisa definir relacionamento com empresa (falta documentação)

@Entity
@Table(name = "pbl")
public class Pbl {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPbl;

	@NotBlank
	private String titulo;
	@NotBlank
	private String situacaoProblema;

	private String resumo;

	// TODO pesquisar sobre datetime
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dataConclusao;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProfessor")
	private Professor professor;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPbl")
	private List<PblAluno> pblAlunos;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTemaPbl")
	private TemaPbl temaPbl;

	public Long getIdPbl() {
		return idPbl;
	}

	public void setIdPbl(Long idPbl) {
		this.idPbl = idPbl;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSituacaoProblema() {
		return situacaoProblema;
	}

	public void setSituacaoProblema(String situacaoProblema) {
		this.situacaoProblema = situacaoProblema;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public List<PblAluno> getPblAlunos() {
		return pblAlunos;
	}

	public void setPblAlunos(List<PblAluno> pblAlunos) {
		this.pblAlunos = pblAlunos;
	}

	public TemaPbl getTemaPbl() {
		return temaPbl;
	}

	public void setTemaPbl(TemaPbl temaPbl) {
		this.temaPbl = temaPbl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPbl == null) ? 0 : idPbl.hashCode());
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
		Pbl other = (Pbl) obj;
		if (idPbl == null) {
			if (other.idPbl != null)
				return false;
		} else if (!idPbl.equals(other.idPbl))
			return false;
		return true;
	}

}
