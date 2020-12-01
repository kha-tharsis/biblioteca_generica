package com.biblioteca_generica.dao;

import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Registro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRegistro {
    private Conexion con;

    public DaoRegistro(Conexion con){
        this.con = con;
    }
    public List<Registro> getRegistrosActualesporIdUser(int id_user){
        String sql = "SELECT * FROM registro WHERE usuario_id_fk = "+id_user+"";
        List<Registro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                int id_usuario = rs.getInt("usuario_id_fk");
                int id_libro = rs.getInt("libro_id_fk");
                String fecha_s = rs.getString("fecha_solicitud");
                String fecha_e = rs.getString("fecha_entrega");
                int estado = rs.getInt("estado_registro_id_fk");
                Registro r = new Registro(id,id_usuario,id_libro,fecha_s,fecha_e,estado);
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String retornarEstadoRegistro(int id_estado){
        String n = "";
        String sql = "SELECT estado FROM estado_registro WHERE id = "+id_estado+"";
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                n = rs.getString("estado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    public boolean exedeLimite(int id_user){
        int cont = 0;
        boolean est = false;
        String sql = "SELECT COUNT(*) FROM registro WHERE usuario_id_fk = "+id_user+" AND estado_registro_id_fk = 1 OR estado_registro_id_fk = 2 OR estado_registro_id_fk = 3";
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                cont = rs.getInt("COUNT(*)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cont >= 2){
            est = true;
        }else{
            est = false;
        }

        return est;
    }
    public List<Registro> allgetRegistros(){
        String sql = "SELECT * FROM registro";
        List<com.biblioteca_generica.model.Registro> list = new ArrayList();
        try {
            ResultSet rs = this.con.getCon()
                    .createStatement()
                    .executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                int id_usuario = rs.getInt("usuario_id_fk");
                int id_libro = rs.getInt("libro_id_fk");
                String fecha_s = rs.getString("fecha_solicitud");
                String fecha_e = rs.getString("fecha_entrega");
                int estado = rs.getInt("estado_registro_id_fk");
                com.biblioteca_generica.model.Registro r = new com.biblioteca_generica.model.Registro(id,id_usuario,id_libro,fecha_s,fecha_e,estado);
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void entregarLibro(int id_registro){
        String sql = "UPDATE registro SET estado_registro_id_fk = 2 WHERE id ="+id_registro;
        try {
            this.con.getCon()
                    .createStatement()
                    .executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void recibirLibro(int id_registro, int id_libro){
        String sql_r = "UPDATE registro SET estado_registro_id_fk = 4 WHERE id ="+id_registro;
        String sql_l = "UPDATE libro SET estado = 1 WHERE id ="+id_libro;
        try {
            this.con.getCon()
                    .createStatement()
                    .executeUpdate(sql_r);
            this.con.getCon()
                    .createStatement()
                    .executeUpdate(sql_l);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
