package br.com.estudo.lojajpa.dao;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.entities.Cliente;

public class ClienteDao {
	private final EntityManager manager;

	public ClienteDao(EntityManager manager) {
		this.manager = manager;
	}

	public Cliente findById(Long id) {
		return manager.find(Cliente.class, id);
	}

	public Cliente save(Cliente cliente) {
		manager.persist(cliente);
		return cliente;
	}

}
