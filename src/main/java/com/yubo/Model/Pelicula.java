package com.yubo.Model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
	/*
	GenerationType.IDENTITY     Se basa en una columna de base de datos con incremento automático y
	permite que la base de datos genere un nuevo valor con cada operación de inserción
	 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private LocalDate fecha;
    private String director;
    private String genero;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", director=" + director + ", genero=" + genero + "]";
    }
}

