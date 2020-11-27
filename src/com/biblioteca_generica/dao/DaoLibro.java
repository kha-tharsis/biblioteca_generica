package com.biblioteca_generica.dao;

import com.biblioteca_generica.model.Conexion;
import  com.biblioteca_generica.model.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String consulta = "INSERT INTO libro VALUES(NULL,'"+titulo+"','"+fecha_publicacion+"','"+autor+"',"+categoria+","+n_paginas+")";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(consulta);
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
    public List<Libro> filtrarLibro(String nombre_filtro,String parametro_filtro,int esString){
        String consulta = "";
        if(esString == 1){
            consulta = "SELECT * FROM libro WHERE '"+nombre_filtro+"' LIKE '%"+parametro_filtro+"%'";
        }
        else if (esString == 0){
            int filtro_num = 0;
            try {
                filtro_num = Integer.parseInt(parametro_filtro);
            } catch (NumberFormatException excepcion) {

            }
            consulta = "SELECT * FROM libro WHERE '"+nombre_filtro+"' = "+filtro_num+"";
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
}
