package br.com.t2m.pblapi.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

//TODO Precisa definir relacionamento com empresa

@Entity
@Table(name = "pbl")
@SequenceGenerator(name = "pbl_id_seq", sequenceName = "pbl_id_seq", allocationSize = 1)
public class Pbl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pbl_id_seq")
	@Column(name = "id_pbl")
	private Long idPbl;

	@NotNull
	@NotBlank
	private String problema;

	private String situacaoProblema;

	private String resumo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "pbl_aluno", joinColumns = @JoinColumn(name = "id_pbl", referencedColumnName = "id_pbl"), inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"))
	private List<Aluno> aluno;

	@NotNull
	@Valid
	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "id_pbl")
	@MapsId
	private PblTemaDisciplina pblTemaDisciplina;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_problema")
	private Problema problemaEmpresa;
	
	public Long getIdPbl() {
		return idPbl;
	}

	public void setIdPbl(Long idPbl) {
		this.idPbl = idPbl;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
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

	public List<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}

	public PblTemaDisciplina getPblTemaDisciplina() {
		return pblTemaDisciplina;
	}

	public void setPblTemaDisciplina(PblTemaDisciplina pblTemaDisciplina) {
		this.pblTemaDisciplina = pblTemaDisciplina;
	}

	public Problema getProblemaEmpresa() {
		return problemaEmpresa;
	}

	public void setProblemaEmpresa(Problema problemaEmpresa) {
		this.problemaEmpresa = problemaEmpresa;
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
