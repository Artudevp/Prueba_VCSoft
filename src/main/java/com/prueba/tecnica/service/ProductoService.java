package com.prueba.tecnica.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.model.Producto;
import com.prueba.tecnica.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    // crear productp
    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    // obtener productos
    public List<Producto> getAllProductos(){
        return (List<Producto>) productoRepository.findAll();
    }

    // seleccionar por id para editar
    public Optional<Producto> getProductoById(int id){
        return productoRepository.findById(id);
    }

    // borrar producto por id
    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

    // caso obtener producto con menos consonantes en su nombre
    public Producto getProductoConMenosConsonantes() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
            .min(Comparator.comparingInt(producto -> contarConsonantes(producto.getNombre())))
            .orElse(null);
    }
    

    // metodo para obtener el numero de consonantes de cada producto
    private int contarConsonantes(String nombre) {
        String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        int count = 0;
        for (char c : nombre.toCharArray()) {
            if (consonantes.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // caso obtener el numero primo mas cercano al precio mas bajo en inventario
    public int getNumeroPrimo() {
        List<Producto> productos = productoRepository.findAll();
        double precioMinimo = productos.stream()
            .mapToDouble(Producto::getPrecio)
            .min()
            .orElseThrow(() -> new RuntimeException("No hay productos"));

        int precioEntero = (int) Math.round(precioMinimo);
        int arriba = precioEntero;
        int abajo = precioEntero;

        while (true) {
            if (esPrimo(arriba)) return arriba;
            if (abajo > 1 && esPrimo(abajo)) return abajo;
            arriba++;
            abajo--;
        }
    }

    // metodo para verificar si un numero es primo
    private boolean esPrimo(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // caso suma de la cantidad todal de los productos
    public int sumaTotalCantidad() {
        List<Producto> productos = productoRepository.findAll();
        return suma(productos, 0);
    }

    // metodo para sumar la cantidad que hay en productos
    private int suma(List<Producto> productos, int index) {
        if (index >= productos.size()) {
            return 0; //
        }
        return productos.get(index).getCantidad() + suma(productos, index + 1);
    }
}
