package br.com.estudo.lojajpa.entrada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.dao.CategoriaDao;
import br.com.estudo.lojajpa.dao.ClienteDao;
import br.com.estudo.lojajpa.dao.PedidoDao;
import br.com.estudo.lojajpa.dao.ProdutoDao;
import br.com.estudo.lojajpa.entities.Categoria;
import br.com.estudo.lojajpa.entities.Cliente;
import br.com.estudo.lojajpa.entities.ItemPedido;
import br.com.estudo.lojajpa.entities.Pedido;
import br.com.estudo.lojajpa.entities.Produto;
import br.com.estudo.lojajpa.util.JPAUtil;

public class InsereDadosTesteDB {
	
	/**
	 * 
	 */
	public void inserir() {
		Map<String, Categoria> categorias = new HashMap<>();
		categorias.put("celulares", new Categoria("Celulares"));
		categorias.put("computadores", new Categoria("Computadores"));
		categorias.put("eletrodomesticos", new Categoria("Eletrodomésticos"));
		categorias.put("lazer", new Categoria("Lazer"));
		categorias.put("utilitario", new Categoria("Utilitário"));

		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto("Poco X3 Pro", "Modelo 2021", new BigDecimal("800"), categorias.get("celulares")));
		produtos.add(new Produto("Acer ASPIRE 5", "A515 52G", new BigDecimal("3200"), categorias.get("computadores")));
		produtos.add(new Produto("MTB Impact Evo", "Aro 29 - 1x12v", new BigDecimal("7600"), categorias.get("lazer")));
		produtos.add(new Produto("Vassoura", "203218 cerdas", new BigDecimal("45.32"), categorias.get("utilitario")));
		produtos.add(new Produto("Bebedouro", "520 litros", new BigDecimal("23000"), categorias.get("lazer")));
		produtos.add(new Produto("Caneca", "duas alças", new BigDecimal("17"), categorias.get("utilitario")));

		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("Gustavo", "12345678909"));
		clientes.add(new Cliente("Paula", "123456789"));
		clientes.add(new Cliente("Ricardo", "123456"));
		clientes.add(new Cliente("Joseph", "123"));

		List<Pedido> pedidos = new ArrayList<>();
		for (int i = 0; i < produtos.size(); i++) {
			Pedido pedido = new Pedido(clientes.get(0));
			pedido.addItem(new ItemPedido(new Random().nextInt(42) + 2, pedido, produtos.get(i)));
			if(i < produtos.size() -1)
				pedido.addItem(new ItemPedido(new Random().nextInt(42) + 2, pedido, produtos.get(i + 1)));
			pedidos.add(pedido);
		}

		EntityManager manager = JPAUtil.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(manager);
		ProdutoDao produtoDao = new ProdutoDao(manager);
		ClienteDao clienteDao = new ClienteDao(manager);
		PedidoDao pedidoDao = new PedidoDao(manager);

		manager.getTransaction().begin();

		clientes.forEach(cliente -> {
			clienteDao.save(cliente);
		});

		categorias.forEach((key, categoria) -> {
			categoriaDao.cadastrar(categoria);
		});

		produtos.forEach(produto -> {
			produtoDao.cadastrar(produto);
		});

		pedidos.forEach(pedido -> {
			pedidoDao.save(pedido);
		});

		manager.getTransaction().commit();
		manager.close();
	}

}
