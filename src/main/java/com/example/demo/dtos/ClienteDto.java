package com.example.demo.dtos;

import com.example.demo.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClienteDto {

	private String documento;
    private String nombre;
    @JsonProperty("tipo_documento")
    private String tipoDocumento;
    
    public ClienteDto fromEntity(Cliente cliente) {
    	ClienteDto clienteDto = new ClienteDto();
    	clienteDto.setDocumento(cliente.getDocumento());
    	clienteDto.setNombre(cliente.getNombre());
    	clienteDto.setTipoDocumento(cliente.getTipoDocumento().getNombre());
		return clienteDto;
	}
}
