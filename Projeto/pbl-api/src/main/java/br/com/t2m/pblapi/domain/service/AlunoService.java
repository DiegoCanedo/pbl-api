package br.com.t2m.pblapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class AlunoService {

	@Autowired
	IAlunoRepository alunoRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Iterable<Aluno> getAll() {
		return alunoRepository.findAll();
	}

	public Aluno getById(Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	public Aluno insert(Aluno aluno) {
		aluno.setSenha(passwordEncoder.encode(aluno.getSenha()));
		return alunoRepository.save(aluno);
	}

	public Aluno update(Aluno aluno, Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		opt.ifPresentOrElse(o -> this.validaUsuario(o, aluno), () -> {
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());
		});

		aluno.setId(id);
//		aluno.setSenha(opt.get().getSenha());
		return alunoRepository.save(aluno);
	}

	private void validaUsuario(Aluno antigo, Aluno novo) {
		if (!novo.getEmail().equalsIgnoreCase(antigo.getEmail()) && alunoRepository.existsByEmail(novo.getEmail())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_EMAIL_JA_EXISTE, novo.getEmail());
		}

		if (!novo.getMatricula().equalsIgnoreCase(antigo.getMatricula())
				&& alunoRepository.existsByEmail(novo.getMatricula())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_MATRICULA_JA_EXISTE, novo.getMatricula());
		}
	}

}
