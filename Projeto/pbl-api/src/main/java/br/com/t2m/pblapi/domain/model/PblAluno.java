package br.com.t2m.pblapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

public class PblAluno {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id_pbl_aluno")
		private Long idPblAluno;
		
		@NotNull
		@ManyToOne
		@JoinColumn(name= "id_aluno")
		private Aluno aluno;
		
		public Long getIdPblAluno() {
			return idPblAluno;
		}

		public void setIdPblAluno(Long idPblAluno) {
			this.idPblAluno = idPblAluno;
		}

		public Aluno getAluno() {
			return aluno;
		}

		public void setAluno(Aluno aluno) {
			this.aluno = aluno;
		}

}
