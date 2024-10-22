package br.com.serratec.entity;

import br.com.serratec.enums.EnumCategoria;
import br.com.serratec.enums.EnumPreparo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private EnumCategoria enumcategoria;
	@Enumerated(EnumType.STRING)
	private EnumPreparo enumpreparo;
	

//	GETTERS SETTERS	
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
	

	
}
