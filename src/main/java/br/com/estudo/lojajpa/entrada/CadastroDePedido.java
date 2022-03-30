package br.com.estudo.lojajpa.entrada;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.dao.PedidoDao;
import br.com.estudo.lojajpa.entities.RelatorioVendasVo;
import br.com.estudo.lojajpa.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		new InsereDadosTesteDB().inserir();

		EntityManager manager = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(manager);
		List<RelatorioVendasVo> results = pedidoDao.relatorioDeVendas();
		results.forEach(result -> {
			System.out.println(String.format("(%s) Qnt: %s - %s", result.getDataUltimaVenda(), result.getQuantidade(),
					result.getNome()));
		});

		manager.close();

	}

}
