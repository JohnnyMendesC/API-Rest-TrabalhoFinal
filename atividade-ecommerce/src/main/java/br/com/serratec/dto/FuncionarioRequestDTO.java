package br.com.serratec.dto;

import br.com.serratec.entity.Funcionario;

public class FuncionarioRequestDTO {
	//	ATRIBUTOS
	private String nome;
	private String telefone;
	private String cargo;
	private String cep;
	private String numeroResidencia;
	private String complemento;

	//CONSTRUTOR VAZIO
	public FuncionarioRequestDTO() {
	}

	//CONSTRUTOR CHEIO
	public FuncionarioRequestDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.telefone = funcionario.getTelefone();
		this.cargo = funcionario.getCargo();
		this.cep = funcionario.getEndereco().getCep();
		this.numeroResidencia = funcionario.getNumeroResidencia();
		this.complemento = funcionario.getComplemento();		
	}

	//GETTERS SETTERS
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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