package br.com.t2m.pblapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Perfil;

public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
	Optional<Perfil> findByNome(EPerfil nome);
}
