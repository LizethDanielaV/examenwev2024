package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = {"detallesCompra", "pagos", "vendedor", "cliente"})
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal total;
	private double impuestos;
	private LocalDate fecha;
	private String observaciones;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	@JsonIgnore
	List<DetallesCompra> detallesCompra = null;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "cajero_id")
	private Cajero cajero;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Pago> pagos = null;
	
	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
}
