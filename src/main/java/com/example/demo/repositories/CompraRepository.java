package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Compra;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{
  Optional<Compra> findById(Integer id);
}
