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
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_pago_id")
	private TipoPago tipoPago;
	
	private double tajerta_tipo;
	private double cuotas;
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
}
