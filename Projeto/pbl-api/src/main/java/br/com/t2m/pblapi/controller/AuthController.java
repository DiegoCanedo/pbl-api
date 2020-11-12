package br.com.t2m.pblapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.config.jwt.JwtUtils;
import br.com.t2m.pblapi.config.services.PblUserDetails;
import br.com.t2m.pblapi.controller.vm.AlunoVM;
import br.com.t2m.pblapi.controller.vm.EmpresaVM;
import br.com.t2m.pblapi.controller.vm.ProfessorVM;
import br.com.t2m.pblapi.domain.service.AlunoService;
import br.com.t2m.pblapi.domain.service.EmpresaService;
import br.com.t2m.pblapi.domain.service.ProfessorService;
import br.com.t2m.pblapi.domain.service.dto.JwtResponseDTO;
import br.com.t2m.pblapi.domain.service.dto.LoginDTO;
import br.com.t2m.pblapi.domain.service.dto.MensagemDTO;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pbl-api/auth")
@Api(description = "rest api para autenticação", tags = { "Auth" })
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	EmpresaService empresaService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = new JwtUtils().generateJwtToken(authentication);

		PblUserDetails userDetails = (PblUserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		

		return ResponseEntity.ok(
				new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/aluno/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MensagemDTO> registrarAluno(@Valid @RequestBody AlunoVM aluno) {
		alunoService.insert(aluno, aluno.getSenha());

		return ResponseEntity.ok(new MensagemDTO(Constants.MENSAGEM_USUARIO_REGISTRADO_SUCESSO));
	}
	
	@PostMapping("/professor/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MensagemDTO> registrarProfessor(@Valid @RequestBody ProfessorVM professor) {
		professorService.insert(professor, professor.getSenha());

		return ResponseEntity.ok(new MensagemDTO(Constants.MENSAGEM_USUARIO_REGISTRADO_SUCESSO));
	}
	
	@PostMapping("/empresa/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MensagemDTO> registrarEmpresa(@Valid @RequestBody EmpresaVM empresa) {
		empresaService.insert(empresa, empresa.getSenha());

		return ResponseEntity.ok(new MensagemDTO(Constants.MENSAGEM_USUARIO_REGISTRADO_SUCESSO));
	}
}
