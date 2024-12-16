package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TipoPago;
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
	TipoPago findByNombre(String nombre);
}
