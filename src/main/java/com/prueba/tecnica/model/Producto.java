package com.prueba.tecnica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @NotBlank(message = "El nombre del producto no debe estar en blanco")
    public String nombre;

    @NotBlank(message = "El producto debe tener una descripción")
    public String descripcion;

    @NotNull(message = "El precio no debe quedar en blanco")
    @Min(value = 100, message = "El precio del producto debe ser de al menos: $100")
    public int precio;

    @NotNull(message = "La cantidad debe ser de al menos: 1")
    @Min(value = 1, message = "La cantidad debe ser de al menos 1")
    public int cantidad;

    Producto(){
        
    }

    
}
