package com.bedu.inventariodos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;


@Entity
public class Producto {


    @Id // Campo que funcionará como clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se generará automáticamente (autoincremental)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @DecimalMin(value = "1.0", message = "El precio debe ser al menos 1")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id") // nombre de la columna FK en la tabla productos
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    protected Producto() {} // Constructor por defecto requerido por JPA

    // Constructor público para crear objetos de tipo Producto con los campos necesarios
    public Producto(String nombre, String descripcion, double precio, Categoria categoria, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }

    // Getters para acceder a los atributos (necesarios para el funcionamiento de JPA y buenas prácticas)
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() {return categoria;}
    public Marca getMarca() { return marca;}

    // Método que permite imprimir el objeto de forma legible (útil para logs o consola)
    @Override
    public String toString() {
        return String.format("Producto[id=%d, nombre='%s', precio=%.2f, categoria= '%s']",
                id, nombre, precio, categoria != null ? categoria.getNombre() : "Sin categoría");
    }
}
