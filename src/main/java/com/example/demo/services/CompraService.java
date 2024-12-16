package com.example.demo.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.FacturaException;

import com.example.demo.dtos.CajeroDto;
import com.example.demo.dtos.ClienteDto;
import com.example.demo.dtos.CrearFacturaDto;
import com.example.demo.dtos.MediosPagoDto;
import com.example.demo.dtos.ProductoDto;
import com.example.demo.dtos.VendedorDto;
import com.example.demo.entities.Cajero;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Compra;
import com.example.demo.entities.DetallesCompra;
import com.example.demo.entities.Pago;
import com.example.demo.entities.Producto;
import com.example.demo.entities.Tienda;
import com.example.demo.entities.TipoDocumento;
import com.example.demo.entities.TipoPago;
import com.example.demo.entities.Vendedor;
import com.example.demo.repositories.CajeroRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CompraRepository;
import com.example.demo.repositories.DetallesCompraRepository;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.TiendaRepository;
import com.example.demo.repositories.TipoDocumentoRepository;
import com.example.demo.repositories.TipoPagoRepository;
import com.example.demo.repositories.VendedorRepository;

@Service
public class CompraService {

	@Autowired
	TipoDocumentoRepository tipoDocRepo;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TiendaRepository tiendaRepository;
	
	@Autowired
	VendedorRepository vendedorRepository;
	
	@Autowired
	CajeroRepository cajeroRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	PagoRepository pagoRepository;
	
	@Autowired
	TipoPagoRepository tipoPagoRepo;
	
	@Autowired
	DetallesCompraRepository detallesCompraRepository;
	
	public Compra procesarFactura(String uuid, CrearFacturaDto facturaDto) {
		validarDatosNulos( facturaDto);
		Cajero cajero = buscarCajero(facturaDto.getCajero());
		System.out.println(cajero);
		Tienda tienda = buscarTienda(uuid);
		System.out.println(tienda);
		Cliente cliente = buscarOCrearCliente(facturaDto.getCliente());
		System.out.println(cliente);
		Vendedor vendedor = buscarVendedor(facturaDto.getVendedor());
		System.out.println(vendedor);
		Compra compra = crearCompra(tienda, cliente, vendedor, cajero, facturaDto);
		System.out.println(compra);
		
		List<DetallesCompra> detallesCompra = validarProductos(compra, facturaDto.getProductos());
		System.out.println(detallesCompra);
		procesarPagos(compra, facturaDto.getMediosPago());

        compra = compraRepository.save(compra);
       // detallesCompraRepository.saveAll(detallescompra);
		//compraRepository.save(compra);
        return compra;
	}
	
	public void validarDatosNulos(CrearFacturaDto facturaDto) {
		if (facturaDto == null) {
	        throw new FacturaException("El objeto CrearFacturaDto no puede ser nulo", HttpStatus.NOT_FOUND);
	    }

	    if (facturaDto.getProductos() == null || facturaDto.getProductos().isEmpty()) {
	        throw new FacturaException("La lista de productos no puede ser nula o vacía", HttpStatus.NOT_FOUND);
	    }
	    System.out.print(facturaDto.getMediosPago());
	    if (facturaDto.getMediosPago() == null || facturaDto.getMediosPago().isEmpty()) {
	        throw new FacturaException("La lista de medios de pago no puede ser nula o vacía", HttpStatus.NOT_FOUND);
	    }

	    if (facturaDto.getVendedor() == null) {
	        throw new FacturaException("El vendedor no puede ser nulo", HttpStatus.NOT_FOUND);
	    }

	    if (facturaDto.getCajero() == null) {
	        throw new FacturaException("El cajero no puede ser nulo", HttpStatus.NOT_FOUND);
	    }

	    if (facturaDto.getImpuesto() < 0) {
	        throw new FacturaException("El impuesto no puede ser negativo", HttpStatus.NOT_FOUND);
	    }
	}
	
	public Cliente buscarOCrearCliente(ClienteDto clienteDto) {
		TipoDocumento tipoDoc = tipoDocRepo.findByNombre(clienteDto.getTipoDocumento());
		System.out.println(tipoDoc);
		if (tipoDoc == null) {
			throw new RuntimeException("Tipo de documento no válido");
		}
		Cliente cliente = clienteRepository.findByDocumentoAndTipoDocumento_Nombre(clienteDto.getDocumento(), clienteDto.getTipoDocumento());
		if (cliente == null) {
            cliente = new Cliente();
            cliente.setDocumento(clienteDto.getDocumento());
            cliente.setNombre(clienteDto.getNombre());
            cliente.setTipoDocumento(tipoDoc);
            cliente = clienteRepository.save(cliente);
        }
        return cliente;
	}
	
	public Tienda buscarTienda(String uuid) {
		Tienda tienda = tiendaRepository.findByUuid(uuid);
        if (tienda == null) {
            throw new RuntimeException("Tienda no encontrada");
        }
        return tienda;
	}
	/*
	public Tienda buscarPorUuid(String uuid) {
		List<Tienda> tiendas = tiendaRepository.findAll();
		for(Tienda t: tiendas) {
			System.out.println(t.getUuid());
			if(t.getUuid().equals(uuid)) {
				return t;
			}
		}
		return null;
	}*/
	
	public Vendedor buscarVendedor(VendedorDto vendedorDto) {
		Vendedor vendedor = vendedorRepository.findByDocumento(vendedorDto.getDocumento());
		if(vendedor == null) {
			throw new RuntimeException("Vendedor no encontrado");
		}
		return vendedor;
	}
	
	public Cajero buscarCajero(CajeroDto cajeroDto) {
	    Cajero cajero = cajeroRepository.findByToken(cajeroDto.getToken());
	    if (cajero == null) {
	        throw new FacturaException("Cajero no encontrado", HttpStatus.NOT_FOUND);
	    }
	    return cajero;
	}
	
	public Compra crearCompra (Tienda tienda, Cliente cliente, Vendedor vendedor, Cajero cajero, CrearFacturaDto facturaDto) {
		Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTienda(tienda);
        compra.setVendedor(vendedor);
        compra.setCajero(cajero);
        compra.setFecha(LocalDate.now());
        compra.setImpuestos(facturaDto.getImpuesto());
        return compra;
	}
	
	public List<DetallesCompra> validarProductos(Compra compra, List<ProductoDto> productosDto){
		List<DetallesCompra> detallesCompra = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		
		for (ProductoDto productoDTO : productosDto) {
			Producto producto = productoRepository.findByReferencia(productoDTO.getReferencia());
			if (producto == null) {
		        throw new FacturaException("\"La referencia del producto\r\n"
		        		+productoDTO.getReferencia() + "no existe, por favor revisar los datos", HttpStatus.NOT_FOUND);
		    }
			if(productoDTO.getCantidad()> producto.getCantidad()) {
				throw new FacturaException("La cantidad a comprar supera el máximo del producto en tienda", HttpStatus.NOT_FOUND);
			}
			
			DetallesCompra detalleCompra = new DetallesCompra();
			detalleCompra.setCompra(compra);
			detalleCompra.setProducto(producto);
			detalleCompra.setCantidad(productoDTO.getCantidad());
			detalleCompra.setDescuento(productoDTO.getDescuento());
			detalleCompra.setPrecio(producto.getPrecio());
			producto.setCantidad(producto.getCantidad() - productoDTO.getCantidad());
			productoRepository.save(producto);
			
			BigDecimal subtotal = producto.getPrecio()
				    .multiply(BigDecimal.valueOf(100).subtract(productoDTO.getDescuento()))
				    .divide(BigDecimal.valueOf(100))
				    .multiply(BigDecimal.valueOf(productoDTO.getCantidad()));
			total = total.add(subtotal);
			System.out.println(detalleCompra);
			detallesCompra.add(detalleCompra);
		}
		
		compra.setTotal(total);
		return detallesCompra;
	}
	
	public void procesarPagos(Compra compra, List<MediosPagoDto> pagosDto) {
		BigDecimal total = BigDecimal.ZERO;
		for (MediosPagoDto pagoDTO : pagosDto) {
			TipoPago tipoPago = tipoPagoRepo.findByNombre(pagoDTO.getTipoPago());
			if(tipoPago == null) {
				throw new FacturaException("Tipo de pago no permitido en la tienda", HttpStatus.NOT_FOUND);
			}
			Pago pago = new Pago();
			pago.setCompra(compra);
			pago.setTipoPago(tipoPago);
			pago.setCuotas(pagoDTO.getCuotas());
			pago.setValor(pagoDTO.getValor());
			
			//pagoRepository.save(pago);
			total = total.add(BigDecimal.valueOf(pago.getValor()));
		}
		System.out.println(compra);
		compra.setTotal(total);
	}
	
	
	
}
