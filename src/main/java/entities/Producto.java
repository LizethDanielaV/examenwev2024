package entities;

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

@Data
@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private double precio;
	private double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "tipo_producto_id")
	private TipoDocumento tipoDocumento;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	@JsonIgnore
	List<DetallesCompra> detallesCompra;
	

}
