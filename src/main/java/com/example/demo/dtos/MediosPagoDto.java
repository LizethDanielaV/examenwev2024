package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class MediosPagoDto {

	@JsonProperty("tipo_pago")
	private String tipoPago;
	
	@JsonProperty("tipo_tarjeta")
	private String tipoTarjeta;
	
	private double cuotas;
	private double valor;
}
