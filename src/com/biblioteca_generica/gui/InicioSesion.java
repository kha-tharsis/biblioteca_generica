package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InicioSesion extends JFrame{
    private JPanel mainPanel;
    private JTextField ruttxt;
    private JPasswordField passtxt;
    private JButton ingresarbtn;

    private DaoUsuario daoUsuario;
    private Conexion con;
    private MainMenu mainMenu;

    public InicioSesion(){
        super("Inicio de Sesión");
        setVisible(true);
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);

        String ip = "localhost";
        int port = 3306;
        String db = "biblioteca_generica";
        String usuario = "root";
        String pass = "";
        con = new Conexion(ip,port,db,usuario,pass);
        daoUsuario = new DaoUsuario(con);

        ingresarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String rut = ruttxt.getText().trim();
                String password = new String(passtxt.getPassword());
                Usuario usuario = new Usuario(rut,password);
                if (rut.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Hay casillas vacías");
                }else{
                    if (daoUsuario.ValidarUsuario(usuario)){
                        int idUsuario = daoUsuario.getUserIdFromRut(rut);
                        usuario.setId(idUsuario);
                        SwingUtilities.invokeLater(()-> mainMenu = new MainMenu(null, usuario));
                    }else{
                        JOptionPane.showMessageDialog(null,"User or password invalid");
                    }
                }
            }
        });
    }
}
