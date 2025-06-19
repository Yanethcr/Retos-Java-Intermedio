package com.bedu.inventariodos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    protected Marca() {}

    public Marca(String nombre){
        this.nombre = nombre;
    }
    public Long getId(){ return id;}

    public String getNombre() {return nombre;}
}
