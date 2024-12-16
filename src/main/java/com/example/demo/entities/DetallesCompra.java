package com.example.demo.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "detalles_compra")
@ToString(exclude = {"compra", "producto"})
public class DetallesCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double cantidad;
	private BigDecimal precio;
	private BigDecimal descuento;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
}
