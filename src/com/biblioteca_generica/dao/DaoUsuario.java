package com.biblioteca_generica.dao;

import com.biblioteca_generica.model.Conexion;
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

    public int getUserIdFromRut (String rut){
        String sql = "SELECT id FROM usuario WHERE rut = '" + rut + "'";
        try (ResultSet resultSet = con.getCon().createStatement().executeQuery(sql)){
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void addUser(String rut,String passw,String nombre,String apellido,String correo,String fecha_nacimiento,int telefono){
        String sql = "INSERT INTO usuario VALUES(NULL,'"+rut+"','"+passw+"','"+nombre+"','"+apellido+"','"+correo+"','"+fecha_nacimiento+"',"+telefono+"),(SELECT id FROM tipo_usuario WHERE tipo_usuario = 'Usuario')";
        try {
            this.con.getCon()
                    .createStatement()
                    .execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
