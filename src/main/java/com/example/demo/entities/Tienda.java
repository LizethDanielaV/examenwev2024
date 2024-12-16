package com.example.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = {"compras", "cajeros"})
public class Tienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String direccion;
	private String uuid;
	
	@OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Compra> compras = null;
	
	@OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Cajero> cajeros = null;
}
