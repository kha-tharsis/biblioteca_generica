package com.biblioteca_generica.model;

public class Registro {
    int id;
    int usuario_id;
    int libro_id;
    String fecha_solicitud;
    String fecha_entrega;
    int estado_registro_id;

    public Registro(){}

    public Registro(int id, int usuario_id, int libro_id, String fecha_solicitud, String fecha_entrega, int estado_registro_id) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.libro_id = libro_id;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_entrega = fecha_entrega;
        this.estado_registro_id = estado_registro_id;
    }

    public Registro(int usuario_id, int libro_id, String fecha_solicitud, String fecha_entrega, int estado_registro_id){
        this.usuario_id = usuario_id;
        this.libro_id = libro_id;
        this.fecha_solicitud = fecha_solicitud;
        this.fecha_entrega = fecha_entrega;
        this.estado_registro_id = estado_registro_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public String getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(String fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public int getEstado_registro_id() {
        return estado_registro_id;
    }

    public void setEstado_registro_id(int estado_registro_id) {
        this.estado_registro_id = estado_registro_id;
    }
}
