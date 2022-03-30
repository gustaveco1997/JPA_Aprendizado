package br.com.estudo.lojajpa.entrada;

import javax.persistence.EntityManager;

import br.com.estudo.lojajpa.dao.ProdutoDao;
import br.com.estudo.lojajpa.util.JPAUtil;

public class TesteCriteria {
	public static void main(String[] args) {
		new InsereDadosTesteDB().inserir();

		EntityManager manager = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(manager);
		System.out.println("\n\n\n\n -----------------");
		dao.findByParameters("Vassoura", null, null);
		manager.close();
	}
}
