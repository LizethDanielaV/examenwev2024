package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Cajero;

@Repository
public interface CajeroRepository extends JpaRepository<Cajero, Integer>{
 Cajero findByToken(String token);
}
