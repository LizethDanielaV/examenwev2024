package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{

}
