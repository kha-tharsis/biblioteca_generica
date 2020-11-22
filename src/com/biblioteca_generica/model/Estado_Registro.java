package com.biblioteca_generica.model;

public class Estado_Registro {
    int id;
    String estado;

    public Estado_Registro(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
