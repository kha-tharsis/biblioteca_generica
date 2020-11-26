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
    public List<Libro> getAllLibros(int ban_id ,int menor, int mayor){
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
}
