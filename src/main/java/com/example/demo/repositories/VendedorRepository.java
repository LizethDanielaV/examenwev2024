package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Vendedor;
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{
		Vendedor findByDocumento(String docuemento);
}
