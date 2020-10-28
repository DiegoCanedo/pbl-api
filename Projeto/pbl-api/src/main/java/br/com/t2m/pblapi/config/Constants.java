package br.com.t2m.pblapi.config;

public final class Constants {
	public static final String EMAIL_REGEX = ".+@.+\\..+";
	public static final String USUARIO_EMAIL_JA_EXISTE = "Esse email pertence a outro usuario: ";
	public static final String USUARIO_MATRICULA_JA_EXISTE = "Essa matricula pertence a outro usuario: ";
	public static final String USUARIO_NAO_ENCONTRADO = "Não existe usuario com o id: ";
	public static final String DISCIPLINA_NAO_ENCONTRADA = "Não existe disciplina com o id: ";
	public static final String DISCIPLINA_JA_EXISTE = "Já existe uma disciplina cadastrada com o nome: ";
	public static final String DISCIPLINA_EXCLUIDA = "Disciplina excluida com sucesso!";
	public static final String TEMA_NAO_ENCONTRADO = "Não existe tema com o id: ";
	public static final String TEMA_JA_EXISTE = "Já existe um tema com esse nome.";
	public static final String PBL_NAO_ENCONTRADO = "Não existe PBL com o id: ";
	public static final String MENSAGEM_USUARIO_REGISTRADO_SUCESSO = "Usuario cadastrado com sucesso, aguarde confirmação do administrador para acessar o sistema.";
	public static final String ATIVIDADE_NAO_ENCONTRADA = "Não existe atividade com o id: ";
	public static final String PERFIL_NAO_ENCONTRADO = "Não existe perfil: ";

	private Constants() {
	}
}
