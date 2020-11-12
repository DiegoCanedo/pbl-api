package br.com.t2m.pblapi.domain.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;

public interface IAtividadeRepository extends JpaRepository<Atividade, Long> {

	@Query(nativeQuery = true, value = 
			" SELECT a.* " +
		    " FROM pbl.atividade as a" + 
			" INNER JOIN pbl.atividade_pbl as ap ON ap.id_atividade = a.id_atividade AND ap.id_pbl = :idPbl" + 
			" INNER JOIN pbl.pbl as pbl ON pbl.id_pbl = ap.id_pbl" + 
			" INNER JOIN pbl.pbl_aluno as pbla ON pbla.id_usuario = :idUsuario AND pbla.id_pbl  = :idPbl" + 
			" WHERE pbl.data_conclusao > current_date")
	public List<Atividade> findByIdUsuarioAndIdPbl(@Param("idUsuario") Long idUsuario,@Param("idPbl") Long idPbl);

	public Set<Atividade> findByAtividadePbls_Pbl(Pbl pbl);

	public Set<Atividade> findByDisciplina(Disciplina disciplina);

	public boolean existsByAtividadePbls_AlunoIsNotNullAndId(Long Id);
}
