package com.example.demo.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrearFacturaDto {

	private double impuesto;
	List<ProductoDto> productos;
	@JsonProperty("medios_pago")
	List<MediosPagoDto> mediosPago;
	private VendedorDto vendedor;
	private CajeroDto cajero;
	private ClienteDto cliente;
}
