package br.com.t2m.pblapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.PblTemaDisciplina;
import br.com.t2m.pblapi.domain.model.TemaPbl;

public interface IPblTemaDisciplinaRepository extends JpaRepository<PblTemaDisciplina, Long> {
	public Optional<List<PblTemaDisciplina>> findByDisciplina(Disciplina disciplina); 
	public Optional<PblTemaDisciplina> findByTema(TemaPbl tema); 
}
