package br.com.serratec.entity;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class LancamentoVendas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description="Identificador único da venda")
	private Long id;
	
	@NotBlank(message = "Preencha a data da venda AAAA-MM-DD")
	@Column(nullable = false, length = 50)
	@Schema(description="Nome do usuário")
	private LocalDate data; //2024-10-12 21:57
	
	@Email
	@Schema(description="Email único do usuário")
	private Double valor;
	
	@NotBlank(message = "Preencha o valor do salário")
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double salario;
}
