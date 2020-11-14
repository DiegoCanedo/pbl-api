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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "atividade_pbl")
public class AtividadePbl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "id_atividade")
	private Long idAtividade;	

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataEntrega;
	
	private Integer nota;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_pbl", referencedColumnName = "id_pbl", nullable = false)
	private Pbl pbl;
	
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<Tarefa> tarefas;
	
	public AtividadePbl(AtividadePbl atividadepbl) {
		this.id = atividadepbl.getId();
		this.dataEntrega = atividadepbl.getDataEntrega();
		this.nota = atividadepbl.getNota();
		this.pbl = atividadepbl.getPbl();
		this.aluno = atividadepbl.getAluno();
		this.tarefas = atividadepbl.getTarefas();
	}
	
	public AtividadePbl() {
		
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

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Pbl getPbl() {
		return pbl;
	}

	public void setPbl(Pbl pbl) {
		this.pbl = pbl;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
}
