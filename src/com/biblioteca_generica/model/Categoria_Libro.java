package com.biblioteca_generica.model;

public class Categoria_Libro {
    int id;
    int libro_id_fk;
    int categoria_id_fk;

    public Categoria_Libro(){}

    public Categoria_Libro(int id, int libro_id_fk, int categoria_id_fk) {
        this.id = id;
        this.libro_id_fk = libro_id_fk;
        this.categoria_id_fk = categoria_id_fk;
    }

    public Categoria_Libro(int libro_id_fk, int categoria_id_fk) {
        this.libro_id_fk = libro_id_fk;
        this.categoria_id_fk = categoria_id_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLibro_id_fk() {
        return libro_id_fk;
    }

    public void setLibro_id_fk(int libro_id_fk) {
        this.libro_id_fk = libro_id_fk;
    }

    public int getCategoria_id_fk() {
        return categoria_id_fk;
    }

    public void setCategoria_id_fk(int categoria_id_fk) {
        this.categoria_id_fk = categoria_id_fk;
    }
}
