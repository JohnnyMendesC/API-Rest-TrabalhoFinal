package br.com.serratec.dto;

import br.com.serratec.entity.Endereco;

public class EnderecoResponseDTO {
	//atributos
	private String cep;
	private String logradouro;
	private String bairro;
	private String municipio;
	private String uf;
	
	//construtores vazio e cheio
	public EnderecoResponseDTO() {
	}
	public EnderecoResponseDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.municipio = endereco.getMunicipio();
		this.uf = endereco.getUf();
	}
	//get set
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
}
