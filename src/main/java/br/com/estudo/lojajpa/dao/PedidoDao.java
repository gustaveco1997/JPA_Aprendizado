package br.com.estudo.lojajpa.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.estudo.lojajpa.entities.Pedido;
import br.com.estudo.lojajpa.entities.RelatorioVendasVo;

public class PedidoDao {
	private final EntityManager manager;

	public PedidoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Pedido save(Pedido pedido) {
		manager.persist(pedido);
		return pedido;
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		Query query = manager.createQuery(jpql, BigDecimal.class);
		return (BigDecimal) query.getSingleResult();
	}

	public Pedido findWithCliente(Long id) {
		//JOIN FETCH (carrega o objeto que é lazy)
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente  WHERE p.id = :id";
		return manager.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
	}

	public List<RelatorioVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.lojajpa.entities.RelatorioVendasVo( produto.nome, SUM(item.quantidade) as soma, MAX(pedido.data)  )"
				+ " FROM Pedido pedido " + " JOIN pedido.itens item " + " JOIN item.produto produto "
				+ " GROUP by produto.nome " + " ORDER BY soma DESC ";
		Query query = manager.createQuery(jpql, RelatorioVendasVo.class);
		return query.getResultList();
	}
}
