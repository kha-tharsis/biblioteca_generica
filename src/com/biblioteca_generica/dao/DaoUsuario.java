package com.biblioteca_generica.dao;

import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {
    private Conexion con;

    public DaoUsuario(Conexion con) {
        this.con = con;
    }

    public boolean ValidarUsuario(Usuario usuario){
        String sql = "SELECT COUNT(*) as 'existe' FROM usuario " +
                "WHERE rut = '"+usuario.getRut()+"' " +
                "AND pass = SHA2('"+usuario.getPass()+"',0)";
        try {
            ResultSet rs = con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            if(rs.next()){
                return rs.getInt("existe") == 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Usuario getUserFromRut (String rut){
        String sql = "SELECT * FROM usuario WHERE rut = '" + rut + "'";
        Usuario u = new Usuario();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String rut_user = rs.getString("rut");
                String pass = rs.getString("pass");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String correo = rs.getString("correo");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                int fono = rs.getInt("fono");
                int tipo_user = rs.getInt("tipo_usuario_id_fk");
                Usuario us = new Usuario(id,rut_user,pass,nombres,apellidos,correo,fecha_nacimiento,fono,tipo_user);
                u = us;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public void addUser(String rut,String passw,String nombre,String apellido,String correo,String fecha_nacimiento,int telefono){
        String sql = "INSERT INTO usuario VALUES(NULL,'"+rut+"',SHA2('"+passw+"',0),'"+nombre+"','"+apellido+"','"+correo+"','"+fecha_nacimiento+"',"+telefono+",(SELECT id FROM tipo_usuario WHERE tipo_usuario = 'Usuario'))";
        try{
            this.con.getCon()
                    .createStatement()
                    .execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void actualizarDatosUsuario(String correo,int fono,int id){
        String sql = "UPDATE usuario " +
                "SET correo = '"+correo+"'," +
                "SET fono = "+fono+"" +
                "WHERE id = "+id+"";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
