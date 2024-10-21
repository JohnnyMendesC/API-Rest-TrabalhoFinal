package br.com.serratec.dto;

import br.com.serratec.entity.Categoria;
import br.com.serratec.entity.Produto;

//Retorno | Resposta 
//DTO = Objeto de transferencia de dado | Data transfer object
public class ProdutoResponseDTO {

	public ProdutoResponseDTO() {
	}
	public ProdutoResponseDTO(Produto produto) {
	}
	
//Response existe para filtrar as informações de acordo com suas regras de
//negócio e responder as requisições que chegam no service, que então 
//envia para o controller o dados tratados sem expor o que não pode
}
