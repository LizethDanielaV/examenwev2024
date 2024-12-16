package com.example.demo.dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Compra;
import com.example.demo.entities.DetallesCompra;

import lombok.Data;


@Data
public class ConsultaFacturaResDto {

	private Integer total;
	private Double impuestos;
	private ClienteDto cliente;
	List<ProductoConsultaDto> productos;
	private CajeroConsultaDto cajero;
	
	public ConsultaFacturaResDto fromEntity(Compra compra) {
		ConsultaFacturaResDto consultaDto = new ConsultaFacturaResDto();
		//convierto y paso el cajero
		CajeroConsultaDto cajeroDto = new CajeroConsultaDto();
		cajeroDto.fromEntity(compra.getCajero());
		consultaDto.setCajero(cajeroDto);
		
		//convierto y paso el cliente
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.fromEntity(compra.getCliente());
		consultaDto.setCliente(clienteDto);
		
		consultaDto.setTotal(compra.getTotal().intValue());
		consultaDto.setImpuestos(compra.getImpuestos());
		List<ProductoConsultaDto> productosDto = productosaDto(compra.getDetallesCompra());
		consultaDto.setProductos(productosDto);
		return consultaDto;
	}
	
	public List<ProductoConsultaDto> productosaDto(List<DetallesCompra> detallesCompra){
		List<ProductoConsultaDto> productosDto = new ArrayList<>();
		for(DetallesCompra producto : detallesCompra) {
			ProductoConsultaDto productoDto = new ProductoConsultaDto();
			productoDto.fromEntity(producto);
			productosDto.add(productoDto);
		}
		return productosDto;
	}
}
