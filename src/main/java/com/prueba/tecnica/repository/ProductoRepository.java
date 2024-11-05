package com.prueba.tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.tecnica.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
}
