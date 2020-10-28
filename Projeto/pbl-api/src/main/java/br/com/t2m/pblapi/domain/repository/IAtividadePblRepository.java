package br.com.t2m.pblapi.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.AtividadePbl;

@Repository
public interface IAtividadePblRepository extends JpaRepository<AtividadePbl, Long> {
	
	@Modifying
    @Query(value = "insert into pbl.atividade_pbl (id_pbl,id_atividade) VALUES (:id_pbl,:id_atividade)", nativeQuery = true)
    @Transactional
    void bindPblToAtividade(@Param("id_pbl") Long idPbl, @Param("id_atividade") Long idAtividade);

}
