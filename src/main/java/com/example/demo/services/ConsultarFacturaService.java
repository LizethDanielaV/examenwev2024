package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ConsultarFacturarReqDto;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Compra;
import com.example.demo.exceptions.FacturaException;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CompraRepository;

@Service
public class ConsultarFacturaService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	public Compra consultar(String uuid, ConsultarFacturarReqDto consultaDto ) {
		//compruebo nulos
		comprobarNulos(consultaDto);
		//miro que si exista la factura y el cliente
		Compra compra = existeCompra(consultaDto.getFactura());
		//Cliente cliente = existeCliente(consultaDto.getCliente());
		if(!consultaDto.getToken().equals(compra.getCajero().getToken())) {
			throw new FacturaException("El cajero no puede acceder a la factura", HttpStatus.NOT_FOUND);
		}
		return compra;
	}
	/*
	public Cliente existeCliente(String documento) {
		Cliente cliente = clienteRepository.findByDocumento(documento);
		if(cliente == null) {
			throw new FacturaException("El objeto cliente no encontrado", HttpStatus.NOT_FOUND);
		}
		return cliente;
	}*/
	
	public Compra existeCompra(Integer compra) {
		Optional<Compra> compraOpt = compraRepository.findById(compra);
		if(compraOpt.get() == null) {
			throw new FacturaException("COmpra no encontrado", HttpStatus.NOT_FOUND);
		}
		return compraOpt.get();
	}
	public void comprobarNulos(ConsultarFacturarReqDto consultaDto) {
		if(consultaDto.getCliente() == null) {
			throw new FacturaException("El objeto CrearFacturaDto no puede ser nulo", HttpStatus.NOT_FOUND);
		}
		if(consultaDto.getToken() == null) {
			throw new FacturaException("El objeto CrearFacturaDto no puede ser nulo", HttpStatus.NOT_FOUND);
		}
		if(consultaDto.getFactura() == null) {
			throw new FacturaException("El objeto CrearFacturaDto no puede ser nulo", HttpStatus.NOT_FOUND);
		}
	}
	

}
