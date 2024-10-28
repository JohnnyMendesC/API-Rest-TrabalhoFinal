package br.com.serratec.entity;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.serratec.enums.EnumPreparo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    private List<Produto> produto;
	
	@Enumerated(EnumType.STRING)
	private EnumPreparo enumpreparo;
	
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
	
	public EnumPreparo getEnumpreparo() {
		return enumpreparo;
	}
	public void setEnumpreparo(EnumPreparo enumpreparo) {
		this.enumpreparo = enumpreparo;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}
