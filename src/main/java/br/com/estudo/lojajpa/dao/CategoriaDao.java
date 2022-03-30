package br.com.estudo.lojajpa.dao;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.entities.Categoria;

public class CategoriaDao {
	private final EntityManager manager;

	public CategoriaDao(EntityManager manager) {
		this.manager = manager;
	}

	public void cadastrar(Categoria categoria) {
		manager.persist(categoria);
	}

}
