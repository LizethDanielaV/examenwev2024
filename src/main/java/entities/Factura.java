package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Factura {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tienda tienda;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "factura")
    private List<DetallesCompra> detalles;

    @OneToMany(mappedBy = "factura")
    private List<Pago> pagos;
}
