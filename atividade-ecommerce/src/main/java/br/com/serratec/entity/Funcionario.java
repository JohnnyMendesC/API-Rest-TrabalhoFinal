package br.com.serratec.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Funcionario {
	// dados da pessoa:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Preencha o nome")
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	@Schema(description = "Nome de funcionario")
	private String nome;

	@NotBlank(message = "Preenchimento obrigatório do telefone")
	@Size(max = 15, min = 10)
	@Pattern(regexp = "\\d{2}(\\d{5}|\\d{4})\\d{4}")
	@Schema(description = "Número de contato de funcionario")
	private String telefone;

	@NotBlank(message = "Preencha o Cargo")
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	@Schema(description = "Cargo do Funcionário")
	private String cargo;

	// dados da localidade:
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	@Schema(description = "Conjunto de informações que define o endereço de funcionario")
	private Endereco endereco;
	@Schema(description = "Número do lote no logradouro")
	private String numeroResidencia;
	@Schema(description = "Descrição do domicílio no lote")
	private String complemento;

	// gets sets
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
