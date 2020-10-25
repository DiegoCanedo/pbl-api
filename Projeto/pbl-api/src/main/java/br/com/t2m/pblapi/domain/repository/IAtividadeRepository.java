package br.com.t2m.pblapi.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;

public interface IAtividadeRepository extends JpaRepository<Atividade, Long> {

	public Set<Atividade> findByAtividadePbls_Pbl(Pbl pbl);

	public Set<Atividade> findByDisciplina(Disciplina disciplina);

	public boolean existsByAtividadePbls_AlunoIsNotNull();
}
