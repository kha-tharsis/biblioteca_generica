package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;

public class MenuUsuario extends JFrame {
    private JPanel mainPanel;

    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DaoUsuario daoUsuario;

    public MenuUsuario(Usuario usuario){
        super("Inicio de Sesión");
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);
        this.usuario = usuario;
    }
}
