package com.example.demo.dtos;

import lombok.Data;

@Data
public class ConsultarFacturarReqDto {

	private String token;
	private String cliente;
	private Integer factura;
}
