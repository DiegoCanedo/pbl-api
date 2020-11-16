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
import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.repository.IPerfilRepository;
import br.com.t2m.pblapi.domain.repository.IProfessorRepository;
import br.com.t2m.pblapi.domain.service.dto.ProfessorDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsExcluidoDTO;
import br.com.t2m.pblapi.domain.service.mapper.ProfessorMapper;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class ProfessorService {

	@Autowired
	IProfessorRepository professorRepository;

	@Autowired
	ProfessorMapper professorMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	PblUserRole userRole;

	@Transactional(readOnly = true)
	public List<ProfessorDTO> getAll() {
		return professorRepository.findAll().stream().map(ProfessorDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProfessorDTO getById(Long id) {
		Optional<ProfessorDTO> opt = professorRepository.findById(id).map(ProfessorDTO::new);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());

		return opt.get();
	}

	public Professor insert(ProfessorDTO professorDTO, String senha) {

		if (professorDTO.getId() != null)

			if (professorRepository.existsByEmail(professorDTO.getEmail()))
				throw new ResourceAlreadyExistsException(Constants.USUARIO_EMAIL_JA_EXISTE, professorDTO.getEmail());

		Professor professor = professorMapper.ProfessorDTOTOProfessor(professorDTO);

		professor.setSenha(passwordEncoder.encode(senha));
		
		Set<Perfil> perfis = new HashSet<>();
		perfis.add(userRole.getPerfil(EPerfil.ROLE_PROFESSOR));
		professor.setPerfil(perfis);

		professor.setAtivo(false);
		professor.setExcluido(false);
		return professorRepository.save(professor);

	}

	public ProfessorDTO update(ProfessorDTO professorDTO) {
		Optional<Professor> opt = professorRepository.findById(professorDTO.getId());

		opt.ifPresentOrElse(o -> this.validaUsuario(o, professorDTO), () -> {
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, professorDTO.getId().toString());
		});

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(professor -> {
			professor.setNome(professorDTO.getNome());
			professor.setEmail(professorDTO.getEmail());
			professor.setDisciplinas(professorDTO.getDisciplinas());
			return professor;
		}).map(ProfessorDTO::new).get();
	}

	public ProfessorDTO updateAtivo(UsuarioIsAtivoDTO professorIsAtivoDTO, Long id) {
		Optional<Professor> opt = professorRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(professor -> {
			professor.setId(id);
			professor.setAtivo(professorIsAtivoDTO.isAtivo());
			return professor;
		}).map(ProfessorDTO::new).get();
	}

	public ProfessorDTO updateExcluido(UsuarioIsExcluidoDTO professorIsExcluidoDTO, Long id) {
		Optional<Professor> opt = professorRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(professor -> {
			professor.setId(id);
			professor.setExcluido(professorIsExcluidoDTO.isExcluido());
			return professor;
		}).map(ProfessorDTO::new).get();
	}

	public void delete(Long id) {
		Optional<Professor> opt = professorRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.USUARIO_NAO_ENCONTRADO, id.toString());

		Professor professor = opt.get();
		professor.setExcluido(true);
		professor.setAtivo(false);
		professorRepository.save(professor);
	}

	private void validaUsuario(Professor antigo, ProfessorDTO novo) {
		if (!novo.getEmail().equalsIgnoreCase(antigo.getEmail())
				&& professorRepository.existsByEmail(novo.getEmail())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_EMAIL_JA_EXISTE, novo.getEmail());
		}
	}

}
