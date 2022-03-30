package br.com.estudo.lojajpa.entrada;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.dao.CategoriaDao;
import br.com.estudo.lojajpa.dao.ProdutoDao;
import br.com.estudo.lojajpa.entities.Categoria;
import br.com.estudo.lojajpa.entities.CategoriaId;
import br.com.estudo.lojajpa.entities.Produto;
import br.com.estudo.lojajpa.util.JPAUtil;

public class CadastroProduto {
	public static void main(String[] args) {
		new InsereDadosTesteDB().inserir();
		findAll();
		findCategoria();
	}

	public static void findCategoria() {
		EntityManager manager = JPAUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(manager);
		Categoria categoria = manager.find(Categoria.class, new CategoriaId("Celulares", "Celulares"));
		manager.close();
	}

	public static void findAll() {
		EntityManager manager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(manager);
		List<Produto> all = produtoDao.findAll();
		all.forEach(p1 -> {
			System.out.println(String.format("%s %s %s", p1.getNome(), p1.getDescricao(), p1.getPreco()));
		});
		manager.close();
	}
}
