package com.example.demo.dtos;

import java.math.BigDecimal;

import com.example.demo.entities.DetallesCompra;

import lombok.Data;
@Data
public class ProductoConsultaDto {

	private String referencia;
	private String nombre;
	private Double cantidad;
	private BigDecimal precio;
	private Integer subtotal;
	
	public ProductoConsultaDto fromEntity(DetallesCompra detallesCompra) {
		ProductoConsultaDto productoConsultaDto = new ProductoConsultaDto();
		productoConsultaDto.setReferencia(detallesCompra.getProducto().getReferencia());
		productoConsultaDto.setNombre(detallesCompra.getProducto().getNombre());
		productoConsultaDto.setCantidad(detallesCompra.getCantidad());
		BigDecimal subtotal = (detallesCompra.getProducto().getPrecio().multiply(new BigDecimal(detallesCompra.getCantidad())));
		subtotal = subtotal.subtract(detallesCompra.getDescuento());
		productoConsultaDto.setSubtotal(subtotal.intValue());
		return productoConsultaDto;
	}
}
