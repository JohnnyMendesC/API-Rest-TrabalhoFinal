package br.com.serratec.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cliente {
//8 ATRIBUTOS
//ID, Nome, Telefone, Email, Cpf, Endereco, NumeroResidencia, Complemento

//da pessoa:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Schema(description = "Nome de usuário")
	private String nome;
	@Schema(description = "Número de contato de usuário")
	private String telefone;
	@Schema(description = "Email único de usuário")
	private String email;
	@Schema(description = "Cadastro de pessoa física")
	private String cpf;

//da localidade:
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	@Schema(description = "Conjunto de informações que define o endereço de usuário")
	private Endereco endereco;
	@Schema(description = "Número do lote no logradouro")
	private String numeroResidencia;
	@Schema(description = "Descrição do domicílio no lote")
	private String complemento;

//gets sets
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	
}
