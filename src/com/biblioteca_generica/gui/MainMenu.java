package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel mainPanel;
    private JButton cerrarSesiónButton;
    private JButton librosButton;

    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DaoUsuario daoUsuario;

    public MainMenu(Usuario usuario){
        super("Menu Principal");
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);
        this.usuario = usuario;


        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InicioSesion is = new InicioSesion();
                dispose();
            }
        });

        librosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LibroMenu lm = new LibroMenu(usuario);
                lm.setVisible(true);
                dispose();
            }
        });
    }
}
