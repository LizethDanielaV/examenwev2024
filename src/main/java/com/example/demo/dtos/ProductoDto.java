package com.example.demo.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoDto {

	 	private String referencia;
	    private int cantidad;
	    private BigDecimal descuento;
}
