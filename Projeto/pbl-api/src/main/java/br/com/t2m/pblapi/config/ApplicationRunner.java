package br.com.t2m.pblapi.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.repository.IPerfilRepository;
import br.com.t2m.pblapi.domain.repository.IProblemaRepository;
import br.com.t2m.pblapi.domain.repository.IUsuarioRepository;

@Configuration
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private IPerfilRepository perfilRepository;

	@Autowired
	private IUsuarioRepository usuarioRepositor;

	@Autowired
	private IProblemaRepository professorRepository;

	@Override
	public void run(String... args) throws Exception {

		// INICIANDO ROLES NA TABELA PERFIL
		List<Perfil> perfis = perfilRepository.findAll();
		if (perfis.isEmpty()) {
			Perfil admin = new Perfil(EPerfil.ROLE_ADMIN);
			Perfil professor = new Perfil(EPerfil.ROLE_PROFESSOR);
			Perfil empresa = new Perfil(EPerfil.ROLE_EMPRESA);
			Perfil aluno = new Perfil(EPerfil.ROLE_ALUNO);

			perfilRepository.saveAll(Arrays.asList(admin, professor, empresa, aluno));
		}

		// INICIANDO USUARIO ADMIN
		if (usuarioRepositor.existsByEmail("pbl@admin.com")) {
			// TODO iniciar o usuario de coordenador - precisa fazer todo o crud;
		}
	}

}
