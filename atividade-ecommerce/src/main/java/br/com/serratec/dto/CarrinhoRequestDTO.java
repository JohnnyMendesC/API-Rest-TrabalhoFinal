package br.com.serratec.dto;

import java.math.BigDecimal;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;

public class CarrinhoRequestDTO {
	// o construtor do swagger se baseia nos getters e setters daqui

	// 7 ATRIBUTOS
	private Produto produto;

	private Integer quantidade;

	private Pedido pedido;

	private BigDecimal desconto; // BigDecimal é o mais preciso
	private Integer valorTotal;

	// (anotação model mapper também funciona)
	// construtor cheio
	public CarrinhoRequestDTO(Carrinho carrinho) {
		this.produto = carrinho.getProduto();
		this.quantidade = carrinho.getQuantidade();
		this.pedido = carrinho.getPedido();
		this.desconto = carrinho.getDesconto();
		this.valorTotal = carrinho.getValorTotal();
	}

	// construtor vazio
	public CarrinhoRequestDTO() {
	}

	// gets sets
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Integer getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Integer valorTotal) {
		this.valorTotal = valorTotal;
	}

}
