package br.com.t2m.pblapi.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.config.services.PblUserRole;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsExcluidoDTO;
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
	
	@Autowired
	PblUserRole userRole;

	@Transactional(readOnly = true)
	public List<AlunoDTO> getAll() {
		return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AlunoDTO getById(Long id) {
		Optional<AlunoDTO> opt = alunoRepository.findById(id).map(AlunoDTO::new);

		if (opt.isEmpty())
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
		Set<Perfil> perfis = new HashSet<>();
		perfis.add(userRole.getPerfil(EPerfil.ROLE_ALUNO));
		aluno.setPerfil(perfis);
		
		aluno.setSenha(passwordEncoder.encode(senha));
		aluno.setAtivo(false);
		aluno.setExcluido(false);
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
	
	public AlunoDTO updateAtivo(UsuarioIsAtivoDTO alunoIsAtivoDTO, Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(aluno -> {
			aluno.setId(id);
			aluno.setAtivo(alunoIsAtivoDTO.isAtivo());
			return aluno;
		}).map(AlunoDTO::new).get();
	}
	
	public AlunoDTO updateExcluido(UsuarioIsExcluidoDTO alunoIsExcluidoDTO, Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(aluno -> {
			aluno.setId(id);
			aluno.setExcluido(alunoIsExcluidoDTO.isExcluido());
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
