package br.com.estudo.lojajpa.entities;

import java.util.Random;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;

	@EmbeddedId // chave composta
	private CategoriaId id;

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, String.valueOf(new Random().nextInt()));
	}

	public String getNome() {
		return this.id.getNome();
	}
}
