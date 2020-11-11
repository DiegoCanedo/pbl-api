package br.com.t2m.pblapi.utils;

import br.com.t2m.pblapi.domain.model.Pbl;

public class EmailMessage {

	public static String pblAlunos(Pbl pbl,String alunoNome, String disciplinaNome) {
		
	return	"<table style=\"background-color: #fff; width: 800px; margin-right: auto; margin-left: auto;\">\r\n"
				+ "        <tr>\r\n"
				+ "            <td>\r\n"
				+ "                <img src=\"https://cdn.discordapp.com/attachments/774795865403031563/774832865182744586/logo.png\" width=\"300px\" height=\"300px\">\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "\r\n"
				+ "            <td align=\"center\">\r\n"
				+ "                <p style=\"color: #404040; text-align: center; padding-top: 20px; font-size: 32px; font-weight: bold; font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif;\">Parabéns, "+alunoNome+"!</p>\r\n"
				+ "                <p><img src=\"https://cdn.discordapp.com/attachments/774795865403031563/774832661189361694/scrumboard.png\" width=\"320px\"></p>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "            <td><p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; text-align: center; font-weight: 700; font-size: 20px; margin-bottom: 30px;\">A partir de agora você faz parte de um novo time PBL!</p></td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "            <td style=\"border: 1px solid #404040;\">\r\n"
				+ "                <p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; text-align: center; font-weight: 700; font-size: 18px;\">Confira seus novos desafios:</p>\r\n"
				+ "                <p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; padding-left: 20px; font-weight: 700;\">Disciplina: "+disciplinaNome+"</p>\r\n"
				+ "                <p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; padding-left: 20px; font-weight: 700;\">Problema: "+pbl.getProblema()+"</p>\r\n"
				+ "                <p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; padding-left: 20px; font-weight: 700;\">Data de início: "+Utils.dateFormat(pbl.getDataInicio())+"</p>\r\n"
				+ "                <p style=\"font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; color: #404040; padding-left: 20px; font-weight: 700;\">Data de conclusão: "+Utils.dateFormat(pbl.getDataConclusao())+"</p>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "        <td style=\"background-color: #002147; width: 800px; margin-right: auto; margin-left: auto; margin-top:12px\">\r\n"
				+ "            <p style=\"text-align: center;\"><img\r\n"
				+ "                    src=\"http://www.pep.uff.br/wp-content/uploads/2019/08/polo-engenharia-de-petropolis.png\"\r\n"
				+ "                    width=\"500px\" style=\"margin-top: 30px; margin-bottom: 30px; margin-right: 30px;\"></p>\r\n"
				+ "        </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>";
		}

}
