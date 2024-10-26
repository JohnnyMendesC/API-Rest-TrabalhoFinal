package br.com.serratec.dto;

import br.com.serratec.entity.Funcionario;

public class FuncionarioResponseDTO {
	//ATRIBUTOS
	private String nome;
	private String cargo;
	
	//CONSTRUTOR VAZIO
	public FuncionarioResponseDTO() {
	}
	
	//CONSTRUTOR CHEIO
	public FuncionarioResponseDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.cargo = funcionario.getCargo();
	}

	//GETTERS SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}