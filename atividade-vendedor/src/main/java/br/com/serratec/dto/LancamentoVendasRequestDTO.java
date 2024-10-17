package br.com.serratec.dto;

import java.time.LocalDate;

import br.com.serratec.entity.LancamentoVendas;

public class LancamentoVendasRequestDTO {
	
	//atributos
	private Long id;
	private LocalDate data;
	private Double valor;
	private String nomeVendedor;
	


	public LancamentoVendasRequestDTO(LancamentoVendas lancamentoVendas) {
		super();
		this.id = lancamentoVendas.getId();
		this.data = lancamentoVendas.getData();
		this.valor = lancamentoVendas.getValor();
		this.nomeVendedor = lancamentoVendas.getVendedorAutonomo().getNome();
	}

	//gets sets
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	
}
