package com.example.demo.dtos;

import com.example.demo.entities.Cajero;

import lombok.Data;

@Data
public class CajeroDto {

	private String token;
	
	public CajeroDto fromEntity(Cajero cajero) {
		CajeroDto cajeroDto = new CajeroDto();
		cajeroDto.setToken(cajero.getToken());
		return cajeroDto;
	}
}
