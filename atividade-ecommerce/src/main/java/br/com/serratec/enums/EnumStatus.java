package br.com.serratec.enums;

public class EnumStatus {
	
		PREPARANDO, 
		FINALIZADO, 
		ENVIANDO, 
		CANCELADO;
		
		@JsonCreator
		public static EnumStatus verificar(String valor) {
			
}
}