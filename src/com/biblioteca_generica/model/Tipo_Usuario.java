package com.biblioteca_generica.model;

public class Tipo_Usuario {
    int id;
    String tipo_usuario;

    public Tipo_Usuario(){}

    public Tipo_Usuario(int id, String tipo_usuario) {
        this.id = id;
        this.tipo_usuario = tipo_usuario;
    }

    public Tipo_Usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
