package br.com.estudo.lojajpa.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "informatica")
public class Informatica extends Produto {

	private final String marca;
	private final String modelo;

	public Informatica(String marca, String modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

}
