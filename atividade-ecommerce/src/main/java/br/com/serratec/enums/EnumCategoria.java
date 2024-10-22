package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum EnumCategoria {
	CERVEJA (1, "Cerveja"),
	DESTILADOS (2, "Destilados"),
	VINHOS (3, "Vinhos"),
	ESPUMANTES (4, "Espumantes"),
	LICORES (5, "Licores"),
	DRINKS (6, "Drinks"),
	REFRIGERANTES (7, "Refrigerantes"),
	SUCOS (8, "Sucos"),
	CAFES (9, "Cafés");
	
	private Integer id;
	private String tipo;
	
	
	private EnumCategoria(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	@JsonCreator
	public static EnumCategoria verificar(Integer valor) {
		for (EnumCategoria c : EnumCategoria.values()) {
			if (c.name().equals(valor)) {
				return c;
			}
		}
		throw new EnumException("Categoria inválida");
	}
}
