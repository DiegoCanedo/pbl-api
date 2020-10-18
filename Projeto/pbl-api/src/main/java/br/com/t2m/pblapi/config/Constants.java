package br.com.t2m.pblapi.config;

public final class Constants {
	public static final String EMAIL_REGEX = ".+@.+\\..+";
	public static final String USUARIO_EMAIL_JA_EXISTE = "Esse email pertence a outro usuario: ";
	public static final String USUARIO_MATRICULA_JA_EXISTE = "Essa matricula pertence a outro usuario: ";
	public static final String USUARIO_NAO_ENCONTRADO = "Não existe usuario com o id: ";
	public static final String DISCIPLINA_NAO_ENCONTRADA = "Não existe disciplina com o id: ";
	public static final String TEMA_NAO_ENCONTRADO = "Não existe tema com o id: ";
	public static final String TEMA_JA_EXISTE = "Ja existe um tema com esse nome.";

	private Constants() {
	}
}
