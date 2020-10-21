package br.com.t2m.pblapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.t2m.pblapi.controller.PblController;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.service.PblService;

@WebMvcTest(controllers = PblController.class)
public class PbiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PblService pblService;

	private Pbl mockPbl;
	private Professor professor;
	private TemaPbl temaPbl;
	private Set<Aluno> alunos;
	private Date dtInicio;
	private Date dtFim;

	@BeforeEach
	void iniciarCampos() {
		professor = new Professor();
		temaPbl = new TemaPbl();
		alunos = new HashSet<Aluno>();
		dtInicio = new Date();
		dtFim = new Date();
		mockPbl = new Pbl();
	}

	// Tranforma o objeto em um JSON
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	

}
