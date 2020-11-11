package br.com.t2m.pblapi.config;

public final class Constants {
	public static final String EMAIL_REGEX = ".+@.+\\..+";
	public static final String USUARIO_EMAIL_JA_EXISTE = "Esse email pertence a outro usuario: ";
	public static final String USUARIO_CNPJ_JA_EXISTE = "Esse cnpj pertence a outra empresa: ";
	public static final String USUARIO_MATRICULA_JA_EXISTE = "Essa matricula pertence a outro usuario: ";
	public static final String USUARIO_NAO_ENCONTRADO = "Não existe usuario com o id: ";
	public static final String DISCIPLINA_NAO_ENCONTRADA = "Não existe disciplina com o id: ";
	public static final String DISCIPLINA_JA_EXISTE = "Já existe uma disciplina cadastrada com o nome: ";
	public static final String DISCIPLINA_EXCLUIDA = "Disciplina excluida com sucesso!";
	public static final String DISCIPLINA_VINCULADA = "Disciplina não pode ser excluida!";
	public static final String TEMA_NAO_ENCONTRADO = "Não existe tema com o id: ";
	public static final String TEMA_JA_EXISTE = "Já existe um tema com esse nome.";
	public static final String PBL_NAO_ENCONTRADO = "Não existe PBL com o id: ";
	public static final String MENSAGEM_USUARIO_REGISTRADO_SUCESSO = "Usuario cadastrado com sucesso, aguarde confirmação do administrador para acessar o sistema.";
	public static final String ATIVIDADE_NAO_ENCONTRADA = "Não existe atividade com o id: ";
	public static final String ATIVIDADE_ALUNO_NAO_ENCONTRADA = "Não existem atividades vinculadas no momento";
	public static final String PERFIL_NAO_ENCONTRADO = "Não existe perfil: ";
	public static final String TAREFA_NAO_ENCONTRADA = "Não existe tarefa com id: ";
	public static final String EMPRESA_NÃO_ENCONTRADA = "Não existe empresa com o id";
	public static final String PROBLEMA_NAO_ENCONTRADO = "Não existe problema com o id";
	public static final String CONTATO_JA_EXISTE = "Já existe um contato cadastrado.";
	public static final String CONTATO_NAO_ENCONTRADO = "Não existe contato com o id: ";
	public static final String PROBLEMA_JA_EXISTE = "Já existe um problema cadastrado com essa descrição.";
	public static final String PROBLEMA_NAO_PODE_SER_EXCLUIDO = "O problema selecionado não pode ser excluído.";
	public static final String PROBLEMA_VINCULADO = "Este problema não pode ser excluído pois já está vinculado a um PBL.";
	public static final String ALUNO_NAO_ENCONTRADO = "Não existe aluno com id: ;";
	public static final String CONTATO_VINCULADO = "Contato não pode ser excluído";
	public static final String ALUNO_JA_ATRIBUIDO = "O aluno já foi atribuído a essa tarefa";
	public static final String TAREFA_NAO_POSSUI_ALUNOS = "Não é possível remover aluno se a tarefa não possui alunos associados";
	private Constants() {
	}
}
