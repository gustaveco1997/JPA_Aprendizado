package br.com.estudo.lojajpa.entities;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RelatorioVendasVo {
	private String nome;
	private BigInteger quantidade;
	private LocalDate dataUltimaVenda;

	public RelatorioVendasVo() {

	}

	public RelatorioVendasVo(String nome, BigInteger quantidade, LocalDate dataUltimaVenda) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.dataUltimaVenda = dataUltimaVenda;
	}

}
