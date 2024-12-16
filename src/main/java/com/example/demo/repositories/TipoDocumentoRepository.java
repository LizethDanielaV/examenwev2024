package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TipoDocumento;
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
	TipoDocumento findByNombre(String nombre);
}
