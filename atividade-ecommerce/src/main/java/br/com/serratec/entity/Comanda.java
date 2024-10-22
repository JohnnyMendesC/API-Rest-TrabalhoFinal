package br.com.serratec.entity;

import java.util.List;

import br.com.serratec.enums.EnumCategoria;
import br.com.serratec.enums.EnumPreparo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Comanda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private EnumCategoria enumcategoria;
	private EnumPreparo enumpreparo;
	
	@ManyToOne
	private Cliente cliente;
	private List<ItemComanda> itens;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EnumCategoria getEnumcategoria() {
		return enumcategoria;
	}
	public void setEnumcategoria(EnumCategoria enumcategoria) {
		this.enumcategoria = enumcategoria;
	}
	public EnumPreparo getEnumpreparo() {
		return enumpreparo;
	}
	public void setEnumpreparo(EnumPreparo enumpreparo) {
		this.enumpreparo = enumpreparo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemComanda> getItens() {
		return itens;
	}
	public void setItens(List<ItemComanda> itens) {
		this.itens = itens;
	}
	
	
}
