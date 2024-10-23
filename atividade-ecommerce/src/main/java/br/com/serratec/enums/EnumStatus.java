package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum EnumStatus {
	
			PREPARANDO, 
			FINALIZADO, 
			ENVIANDO, 
			CANCELADO;
			
		
			 @JsonCreator
			    public static EnumStatus verificar(String valor) {
			        for (EnumStatus s : EnumStatus.values()) {
			            if (s.name().equals(valor)) {
			                return s;
			            }
			        }
			        
			   throw new EnumException ("Status inexistente");
			   
			 }

			
			}




