package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class DetallesCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double cantidad;
	private double precio;
	private double descuento;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
}
