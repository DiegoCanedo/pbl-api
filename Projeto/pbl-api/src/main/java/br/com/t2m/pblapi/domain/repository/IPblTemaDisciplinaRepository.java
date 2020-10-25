package br.com.t2m.pblapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.t2m.pblapi.domain.model.PblTemaDisciplina;

public interface IPblTemaDisciplinaRepository extends JpaRepository<PblTemaDisciplina, Long> {
	
}
