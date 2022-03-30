package br.com.estudo.lojajpa.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.estudo.lojajpa.entities.Produto;

public class ProdutoDao {
	private EntityManager manager;

	public ProdutoDao(EntityManager manager) {
		this.manager = manager;
	}

	public void cadastrar(Produto produto) {
		manager.persist(produto);
	}

	public void atualizar(Produto produto) {
		merge(produto);
	}

	public void remove(Produto produto) {
		produto = merge(produto);
		manager.remove(produto);
	}

	public Produto findById(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> findAll() {
		String jpql = "SELECT p FROM Produto p";
		Query query = manager.createQuery(jpql, Produto.class);
		return query.getResultList();
	}

	public List<Produto> findByNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		Query query = manager.createQuery(jpql, Produto.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	public List<Produto> findByParameters(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();
		if (nome != null && !nome.trim().isEmpty())
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));

		if (dataCadastro != null)
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));

		if (preco != null)
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));

		query.where(filtros);
		return manager.createQuery(query).getResultList();

	}

	// péssima maneira //pesquisar outras maneiras
	// solução melhorada no método acima
	public List<Produto> findByParametersOld(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p  " + " WHERE 1 ";
		if (nome != null && !nome.trim().isEmpty())
			jpql += " AND p.nome = :nome";

		if (dataCadastro != null)
			jpql += " AND p.dataCadastro = :dataCadastro";

		if (preco != null)
			jpql += " AND p.preco= :preco";

		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);

		if (nome != null && !nome.trim().isEmpty())
			query.setParameter("nome", nome);

		if (dataCadastro != null)
			query.setParameter("dataCadastro", dataCadastro);

		if (preco != null)
			query.setParameter("preco", preco);

		return query.getResultList();
	}

	private Produto merge(Produto produto) {
		return manager.merge(produto);
	}

}
