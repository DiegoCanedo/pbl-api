package br.com.t2m.pblapi.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;
import br.com.t2m.pblapi.domain.service.dto.AlunoAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.mapper.AlunoMapper;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class AlunoService {

	@Autowired
	IAlunoRepository alunoRepository;

	@Autowired
	AlunoMapper alunoMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<AlunoDTO> getAll() {
		return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AlunoDTO getById(Long id) {
		Optional<AlunoDTO> opt = alunoRepository.findById(id).map(AlunoDTO::new);

		if (!opt.isPresent())
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	public Aluno insert(AlunoDTO alunoDTO, String senha) {

		if (alunoDTO.getId() != null)

			if (alunoRepository.existsByEmail(alunoDTO.getEmail()))
				throw new ResourceAlreadyExistsException(Constants.USUARIO_EMAIL_JA_EXISTE, alunoDTO.getEmail());

		if (alunoRepository.existsByMatricula(alunoDTO.getMatricula()))
			throw new ResourceAlreadyExistsException(Constants.USUARIO_MATRICULA_JA_EXISTE, alunoDTO.getEmail());

		Aluno aluno = alunoMapper.alunoDTOTOAluno(alunoDTO);
		aluno.setSenha(passwordEncoder.encode(senha));
		return alunoRepository.save(aluno);

	}

	public AlunoDTO update(AlunoDTO alunoDTO) {
		Optional<Aluno> opt = alunoRepository.findById(alunoDTO.getId());

		opt.ifPresentOrElse(o -> this.validaUsuario(o, alunoDTO), () -> {
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, alunoDTO.getId().toString());
		});

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(aluno -> {
			aluno.setNome(alunoDTO.getNome());
			aluno.setEmail(alunoDTO.getEmail());
			aluno.setMatricula(alunoDTO.getMatricula());
			aluno.setAtivo(alunoDTO.isAtivo());
			return aluno;
		}).map(AlunoDTO::new).get();
	}
	
	public AlunoDTO updateAtivo(AlunoAtivoDTO alunoAtiboDTO, Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(aluno -> {
			aluno.setId(id);
			aluno.setAtivo(alunoAtiboDTO.isAtivo());
			return aluno;
		}).map(AlunoDTO::new).get();
	}

	public void delete(Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());

		Aluno aluno = opt.get();
		aluno.setExcluido(true);
		aluno.setAtivo(false);
		alunoRepository.save(aluno);
	}

	private void validaUsuario(Aluno antigo, AlunoDTO novo) {
		if (!novo.getEmail().equalsIgnoreCase(antigo.getEmail()) && alunoRepository.existsByEmail(novo.getEmail())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_EMAIL_JA_EXISTE, novo.getEmail());
		}

		if (!novo.getMatricula().equalsIgnoreCase(antigo.getMatricula())
				&& alunoRepository.existsByEmail(novo.getMatricula())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_MATRICULA_JA_EXISTE, novo.getMatricula());
		}
	}

}
