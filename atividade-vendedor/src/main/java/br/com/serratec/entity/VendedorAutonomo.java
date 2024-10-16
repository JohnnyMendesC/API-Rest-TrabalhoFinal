package br.com.serratec.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class VendedorAutonomo extends Vendedor{
	//pode dar problema se for 0,00?
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double comissao;
}
