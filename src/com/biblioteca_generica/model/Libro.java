package com.biblioteca_generica.model;

public class Libro {
    int id;
    String titulo;
    String fecha_publicacion;
    String autor;
    int categoria_id_fk;
    int numero_paginas;
    int estado;

    public Libro(){}

    public Libro(int id, String titulo, String fecha_publicacion, String autor, int categoria_id_fk, int numero_paginas, int estado) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_publicacion = fecha_publicacion;
        this.autor = autor;
        this.categoria_id_fk = categoria_id_fk;
        this.numero_paginas = numero_paginas;
        this.estado = estado;
    }

    public Libro(String titulo, String fecha_publicacion, String autor, int categoria_id_fk, int numero_paginas, int estado) {
        this.titulo = titulo;
        this.fecha_publicacion = fecha_publicacion;
        this.autor = autor;
        this.categoria_id_fk = categoria_id_fk;
        this.numero_paginas = numero_paginas;
        this.estado = estado;
    }

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

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCategoria_id_fk() {
        return categoria_id_fk;
    }

    public void setCategoria_id_fk(int categoria_id_fk) {
        this.categoria_id_fk = categoria_id_fk;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
