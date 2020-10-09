package br.com.t2m.pblapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pbl_aluno")
public class PblAluno {

		@Id
		@NotNull
		@GeneratedValue(strategy = GenerationType.AUTO)
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idPblAluno == null) ? 0 : idPblAluno.hashCode());
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
			PblAluno other = (PblAluno) obj;
			if (idPblAluno == null) {
				if (other.idPblAluno != null)
					return false;
			} else if (!idPblAluno.equals(other.idPblAluno))
				return false;
			return true;
		}
		
		
}
