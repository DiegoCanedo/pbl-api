package br.com.uff.pblapi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.t2m.pblapi.controller.PblController;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.PblAluno;
import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.model.TemaPbl;
import br.com.t2m.pblapi.domain.service.PblService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PblController.class)
public class PbiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PblService pblService;

	@Test
	public void testIniciarPbl() throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Pbl mockPbl = new Pbl();
		Professor professor = new Professor();
		TemaPbl tema = new TemaPbl();
		List<PblAluno> alunos = new ArrayList();
		

		mockPbl.setIdPbl(1L);
		mockPbl.setTitulo("Titulo");
		mockPbl.setSituacaoProblema("SituacaoProblema");
		mockPbl.setResumo("Resumo");
		mockPbl.setDataInicio(new Date());
		mockPbl.setDataConclusao(new Date());
		mockPbl.setProfessor(professor);
		mockPbl.setPblAlunos(alunos);
		mockPbl.setTemaPbl(tema);

	}

}
