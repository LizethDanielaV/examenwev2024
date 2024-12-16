package com.example.demo.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_pago_id")
	private TipoPago tipoPago;
	
	@Column(name = "tarjeta_tipo", length = 50, nullable = true)
	private String tajertaTipo;
	
	@Column(nullable = true)
	private double cuotas;
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
}
