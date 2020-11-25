package com.biblioteca_generica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection con;

    public Conexion(String host,int port,String db,String user,String pass) {
        String url = String.format("jdbc:mariadb://%s:%d/%s",host,port,db);
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon(){
        return con;
    }

}
