package br.com.t2m.pblapi.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.Problema;

@Repository
public interface IPblRepository extends JpaRepository<Pbl, Long> {
	
	public Set<Pbl> findAllByPblTemaDisciplina_Disciplina_Id(@Param("id_disciplina") Long id_disciplina);
	
	public boolean existsByProblemaEmpresa(Problema problema);
}
