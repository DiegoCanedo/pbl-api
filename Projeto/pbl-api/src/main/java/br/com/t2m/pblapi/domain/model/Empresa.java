package br.com.t2m.pblapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "empresa")
public class Empresa {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nomeEmpresa;
	
	@NotBlank
	private String cnpj;
	
	@NotBlank
	private String email;
	
	
	
}
