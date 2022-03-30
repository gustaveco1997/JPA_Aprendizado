package br.com.estudo.lojajpa.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "livros")
public class Livro extends Produto {
	private String autor;
	private String numeroPaginas;

	public Livro(String autor, String numeroPaginas) {
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}
}
