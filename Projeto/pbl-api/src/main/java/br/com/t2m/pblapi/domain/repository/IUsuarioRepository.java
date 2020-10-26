package br.com.t2m.pblapi.domain.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Usuario;

@Transactional
@Repository
public interface IUsuarioRepository extends IUsuarioBaseRepository<Usuario> {
	public Boolean existsByEmail(String email);
}
