package br.com.t2m.pblapi.utils;

import br.com.t2m.pblapi.domain.model.Pbl;

public class EmailMessage {

	public static String pblAlunos(Pbl pbl,String alunoNome, String disciplinaNome) {
		return " <div style=\"background-color: #002147; width: 800px; margin-right: auto; margin-left: auto;\">\r\n"
				+ "        \r\n"
				+ "        <img src=\"https://logodownload.org/wp-content/uploads/2015/03/uff-logo-6.png\" width=\"100px\" height=\"100px\" style=\"margin-left: 20px; margin-top: 20px\">"
				+ "        <h1 style=\"color: #f5f5f5; text-align: center; padding-top: 20px\">Olá, " +alunoNome+ " !</h1>\r\n"
				+ "        <h2 style=\"color: #f5f5f5; text-align: center; padding-bottom: 50px\">Você foi adicionado a um novo PBL.</h2>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Disciplina: "
				+ disciplinaNome + "</h3>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Problema: " + pbl.getProblema()
				+ "</h3>\r\n" + "        <h3 style=\"color: #f5f5f5; padding-left: 20px;\">Data de início: "
				+ Utils.dateFormat(pbl.getDataInicio()) + " </h3>\r\n"
				+ "        <h3 style=\"color: #f5f5f5; padding-left: 20px; padding-bottom: 50px\">Data de conclusão: "
				+ Utils.dateFormat(pbl.getDataConclusao()) + " </h3>\r\n" + "\r\n" + "\r\n" + "\r\n" + "    </div>";
	}

}
