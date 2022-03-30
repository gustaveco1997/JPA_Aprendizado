package br.com.estudo.lojajpa.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	private BigInteger quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	public ItemPedido() {
		
	}
	public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
		this.quantidade = BigInteger.valueOf(quantidade);
		this.precoUnitario = produto.getPreco();
		this.pedido = pedido;
		this.produto = produto;
	}
	
	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}
	
}
