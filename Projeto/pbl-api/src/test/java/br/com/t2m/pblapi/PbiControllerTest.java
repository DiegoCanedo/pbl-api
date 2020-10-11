package br.com.t2m.pblapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.t2m.pblapi.controller.PblController;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.PblAluno;
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
	private List<PblAluno> alunos;
	private Date dtInicio;
	private Date dtFim;

	@BeforeEach
	void iniciarCampos() {
		professor = new Professor();
		temaPbl = new TemaPbl();
		alunos = new ArrayList<PblAluno>();
		dtInicio = new Date();
		dtFim = new Date();
		mockPbl = new Pbl();
	}

	// Tranforma o objeto em um JSON
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void test_PblService() throws Exception {
		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		String inputInJson = mapToJson(mockPbl);
		Mockito.when(pblService.insert(Mockito.any(Pbl.class))).thenReturn(mockPbl);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pbl").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	public void test_PblService_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		String inputInJson = mapToJson(mockPbl);
		Mockito.when(pblService.insert(null)).thenReturn(mockPbl);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pbl").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isNotEqualTo(inputInJson);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void test_IniciarPbl() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isOk());
	}

	@Test
	public void test_IniciarPbl_Campo_IdPbl_Null() throws Exception {

		mockPbl.setIdPbl(null);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_Titulo_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo(null);
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_SituacaoProblema_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema(null);
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_Resumo_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo(null);
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isOk());

	}

	@Test
	public void test_IniciarPbl_Campo_DataInicio_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(null);
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_DataConclusao_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(dtInicio);
		mockPbl.setDataConclusao(null);
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_Professor_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(dtInicio);
		mockPbl.setDataConclusao(dtFim);
		mockPbl.setProfessor(null);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_PblAlunos_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(dtInicio);
		mockPbl.setDataConclusao(dtFim);
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(null);
		mockPbl.setTemaPbl(temaPbl);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void test_IniciarPbl_Campo_TemaPbl_Null() throws Exception {

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(dtInicio);
		mockPbl.setDataConclusao(dtFim);
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(null);

		mockMvc.perform(post("/pbl").contentType("application/json").content(mapToJson(mockPbl)))
				.andExpect(status().isBadRequest());

	}

}
