package br.com.serratec.dto;

import br.com.serratec.enums.EnumPreparo;

//Requisição | Pergunta
//DTO = Objeto de transferencia de dado | Data transfer object
public class CategoriaRequestDTO {
	private String nome;
	private EnumPreparo enumpreparo;
	
	
	
//Request existe para pegar as requisições utilizando suas regras de negócio
//Nela por exemplo não precisa setar o ID, pois ele é gerado automáticamente
}
