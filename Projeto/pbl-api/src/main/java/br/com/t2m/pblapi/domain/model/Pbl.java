package br.com.t2m.pblapi.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

//TODO Precisa definir relacionamento com empresa (falta documentação)

@Entity
@Table(name = "pbl")
public class Pbl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pbl")
	private Long idPbl;

	@NotBlank
	private String titulo;
	@NotBlank
	private String situacaoProblema;

	private String resumo;

	// TODO pesquisar sobre datetim
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;
 
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;

	@ManyToMany
	@JoinTable( name = "pbl_aluno", 
		joinColumns = @JoinColumn(name="id_pbl", referencedColumnName = "id_pbl"),
		inverseJoinColumns = @JoinColumn(name="id_aluno", referencedColumnName ="id_aluno"))
	private List<Aluno> aluno;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tema_pbl")
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

	

	public List<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}

	public TemaPbl getTemaPbl() {
		return temaPbl;
	}

	public void setTemaPbl(TemaPbl temaPbl) {
		this.temaPbl = temaPbl;
	}
		
}
