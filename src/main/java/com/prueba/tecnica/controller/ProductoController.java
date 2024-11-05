package com.prueba.tecnica.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.model.Producto;
import com.prueba.tecnica.service.ProductoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Transactional
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // para obtener productos
    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    // para crear prodiucto
    @PostMapping
    public Producto postNewProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    // para obtener producto por id
    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@RequestParam int id) {
        return productoService.getProductoById(id);
    }
    

    // para editar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> putUpdateProducto(@PathVariable int id, @Valid @RequestBody Producto productoDetails) {
        Producto producto = productoService.getProductoById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setCantidad(productoDetails.getCantidad());
        producto.setPrecio(productoDetails.getPrecio());

        final Producto updatedProducto = productoService.saveProducto(producto);
        return ResponseEntity.ok(updatedProducto);
    }

    // para eliminar producto
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable int id){
        productoService.deleteProducto(id);
    }

    // caso menos consonantes en el nombre del productro
    @GetMapping("/consonantes")
    public ResponseEntity<Producto> getProductoConMenosConsonantes() {
        Producto producto = productoService.getProductoConMenosConsonantes();
        return ResponseEntity.ok(producto);
    }

    // caso primo mas cercano al menor precio
    @GetMapping("/primo-precio")
    public ResponseEntity<Integer> getNumeroPrimo() {
        int primoCercano = productoService.getNumeroPrimo();
        return ResponseEntity.ok(primoCercano);
    }

    // caso suma total de los productos en base de datos (inventario)
    @GetMapping("/total")
    public ResponseEntity<Integer> sumaTotalCantidad() {
        int sumaTotal = productoService.sumaTotalCantidad();
        return ResponseEntity.ok(sumaTotal);
    }
    
    
}
