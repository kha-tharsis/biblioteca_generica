package com.biblioteca_generica.dao;

import com.biblioteca_generica.model.Categoria;
import com.biblioteca_generica.model.Conexion;
import  com.biblioteca_generica.model.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class DaoLibro {
    private Conexion con;

    public DaoLibro(Conexion con)
    {
        this.con = con;
    }
    public List<Libro> getAllLibros(){
        String sql = "SELECT * FROM libro";
        List<Libro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                String autor = rs.getString("autor");
                int categoria_id = rs.getInt("categoria_id_fk");
                int n_paginas = rs.getInt("numero_paginas");
                int estado = rs.getInt("estado");
                Libro l = new Libro(id,titulo,fecha_publicacion,autor,categoria_id,n_paginas,estado);
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void insertarLibro(String titulo,String fecha_publicacion,String autor,int categoria,int n_paginas){
        String c = "CALL agregar_libro('"+titulo+"','"+fecha_publicacion+"','"+autor+"',(SELECT categoria FROM categoria WHERE id = "+categoria+"),"+n_paginas+")";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(c);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateLibro(int id,String titulo,String fecha_publicacion,String autor,int categoria,int n_paginas){
        String consulta = "UPDATE libro " +
                "SET titulo = '"+titulo+"'," +
                "fecha_publicacion = '"+fecha_publicacion+"'," +
                "autor = '"+autor+"'," +
                "categoria_id_fk = "+categoria+"," +
                "numero_paginas = "+n_paginas+"" +
                "WHERE id ="+id+"";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(consulta);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Libro> filtrarLibro(String nombre_filtro,String parametro_filtro){
        String consulta = "";
        if(nombre_filtro != "id"){
            consulta = "SELECT * FROM libro WHERE "+nombre_filtro+" LIKE '%"+parametro_filtro+"%'";
        }
        else if (nombre_filtro == "id"){
            int filtro_num = 0;
            try {
                filtro_num = Integer.parseInt(parametro_filtro);
            } catch (NumberFormatException excepcion) {

            }
            consulta = "SELECT * FROM libro WHERE "+nombre_filtro+" = "+filtro_num+"";
        }
        List<Libro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(consulta);
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                String autor = rs.getString("autor");
                int categoria_id = rs.getInt("categoria_id_fk");
                int n_paginas = rs.getInt("numero_paginas");
                int estado = rs.getInt("estado");
                Libro l = new Libro(id,titulo,fecha_publicacion,autor,categoria_id,n_paginas,estado);
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public String getCategoriaPorId(int id){
        String sql = "SELECT categoria FROM categoria WHERE id = "+id+"";
        String cat = "";
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                cat = rs.getString("categoria");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cat;
    }
    public String getEstadoPorInt(int est){
        String estado = "";
        if(est == 1){
            estado = "Disponible";
        }else if(est == 0){
            estado = "No Disponible";
        }
        return estado;
    }
    public List<Libro> getLibrosDisponibles(){
        String sql = "SELECT * FROM libro WHERE estado = 1";
        List<Libro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                String autor = rs.getString("autor");
                int categoria_id = rs.getInt("categoria_id_fk");
                int n_paginas = rs.getInt("numero_paginas");
                int estado = rs.getInt("estado");
                Libro l = new Libro(id,titulo,fecha_publicacion,autor,categoria_id,n_paginas,estado);
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Categoria> getAllCategorias(){
        String sql = "SELECT * FROM categoria";
        List<Categoria> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String cat = rs.getString("categoria");
                Categoria c = new Categoria(id,cat);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Libro> getLibrosPoridCategoria(int id_categoria){
        String sql = "SELECT * FROM libro WHERE categoria_id_fk = "+id_categoria+"";
        List<Libro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                String autor = rs.getString("autor");
                int categoria_id = rs.getInt("categoria_id_fk");
                int n_paginas = rs.getInt("numero_paginas");
                int estado = rs.getInt("estado");
                Libro l = new Libro(id,titulo,fecha_publicacion,autor,categoria_id,n_paginas,estado);
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Libro> getLibrosUsuario(int id_user){
        String sql = "SELECT libro.id, libro.titulo, libro.fecha_publicacion, " +
                "libro.autor, libro.categoria_id_fk, libro.numero_paginas, libro.estado  " +
                "FROM registro  INNER JOIN libro ON libro.id = registro.libro_id_fk WHERE " +
                "registro.usuario_id_fk ="+id_user+"";
        List<Libro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                String autor = rs.getString("autor");
                int categoria_id = rs.getInt("categoria_id_fk");
                int n_paginas = rs.getInt("numero_paginas");
                int estado = rs.getInt("estado");
                Libro l = new Libro(id,titulo,fecha_publicacion,autor,categoria_id,n_paginas,estado);
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void solicitarLibro(int id_libro ,int id_user){
        String consulta = "CALL agregarRegistro("+id_user+","+id_libro+",'"+ LocalDate.now()+"','"+LocalDate.now().plusDays(31)+"')";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(consulta);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getNombreLibro(int id_libro){
        String n = "";
        String sql = "SELECT titulo FROM libro WHERE id = "+id_libro+"";
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                n = rs.getString("titulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public void deleteLibro(int id){
        String sql = "DELETE FROM libro WHERE id =" +id;
        try {
            this.con.getCon()
                    .createStatement()
                    .executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
