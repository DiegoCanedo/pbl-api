package br.com.t2m.pblapi.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.t2m.pblapi.domain.model.Usuario;

@NoRepositoryBean
public interface IUsuarioBaseRepository<T extends Usuario> extends CrudRepository<T, Long> {
	public T findByEmail(String email);
	
	@Override
	List<T> findAll();
}
