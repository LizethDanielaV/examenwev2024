package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
