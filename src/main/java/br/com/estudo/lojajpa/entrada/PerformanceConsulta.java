package br.com.estudo.lojajpa.entrada;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.dao.PedidoDao;
import br.com.estudo.lojajpa.entities.Pedido;
import br.com.estudo.lojajpa.util.JPAUtil;

public class PerformanceConsulta {

	public static void main(String[] args) {
		new InsereDadosTesteDB().inserir();

		EntityManager manager = JPAUtil.getEntityManager();
		System.out.println("\n\n\n\n\n-----------------------------");
		PedidoDao pedioDao = new PedidoDao(manager);
		Pedido pedido = pedioDao.findWithCliente(1l);
		manager.close();
		
		System.out.println(pedido.getCliente().getNome());
		manager.close();
	}
}
