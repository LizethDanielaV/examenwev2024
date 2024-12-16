package com.example.demo.dtos;

import com.example.demo.entities.Cajero;

import lombok.Data;

@Data
public class CajeroConsultaDto {

	private String nombre;
	private String documento;
	
	public CajeroConsultaDto fromEntity(Cajero cajero) {
		CajeroConsultaDto cajeroDto = new CajeroConsultaDto();
		cajeroDto.setDocumento(cajero.getDocumento());
		cajeroDto.setNombre(cajero.getNombre());
		return cajeroDto;
	}
}
