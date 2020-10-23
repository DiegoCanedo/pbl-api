package br.com.t2m.pblapi.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;

@Repository
public interface IPblRepository extends JpaRepository<Pbl, Long> {

//	@Query(value = " select p.* from Pbl p "
//			+ " join tema_pbl t "
//			+ " join tema_disciplina td "
//			+ " join Disciplina d "
//			+ " where d.id = :id_disciplina",
//		   nativeQuery = true)
//	@Transactional(readOnly = true)
//	public Set<Pbl> pblPorDisciplina(@Param("id_disciplina") Long id_disciplina);
	public Set<Pbl> findAllByTemaPbl_Disciplinas_Id(@Param("id_disciplina") Long id_disciplina);
}
