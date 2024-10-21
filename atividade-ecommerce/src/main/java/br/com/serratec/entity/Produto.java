package br.com.serratec.entity;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Schema(description = "Nome do produto")
	private String nome;
	
	@OneToMany(mappedBy = "id.produto")
	private Set<CategoriaProduto> categoriaProdutos = new HashSet<>();

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
	

}
