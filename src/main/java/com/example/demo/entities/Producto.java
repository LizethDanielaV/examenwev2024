package com.example.demo.entities;

import java.math.BigDecimal;
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
@ToString(exclude = {"tipoProducto", "detallesCompra"})
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "tipo_producto_id")
	private TipoProducto tipoProducto;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	@JsonIgnore
	List<DetallesCompra> detallesCompra;
	
	private String referencia;
	

}
