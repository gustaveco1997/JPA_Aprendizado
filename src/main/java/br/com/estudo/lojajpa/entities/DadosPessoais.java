package br.com.estudo.lojajpa.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable // é "imbutível" em outras classes
public class DadosPessoais {

	@Column(name = "nome_alterado")
	private String nome;
	private String cpf;

	public DadosPessoais() {

	}

	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

}
