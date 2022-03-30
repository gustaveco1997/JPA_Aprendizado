package br.com.estudo.lojajpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CategoriaId implements Serializable {
	private String nome;
	private String tipo;

	public CategoriaId() {

	}

	public CategoriaId(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

}
