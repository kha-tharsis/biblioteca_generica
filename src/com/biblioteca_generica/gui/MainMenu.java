package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoRegistro;
import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Registro;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel mainPanel;
    private JButton cerrarSesiónButton;
    private JButton librosButton;
    private JLabel nombrelbl;
    private JLabel rutlbl;
    private JButton registrobtn;

    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DaoUsuario daoUsuario;

    public MainMenu(Usuario usuario){
        super("Menu Principal");
        setVisible(true);
        setSize(450,190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);
        this.usuario = usuario;
        nombrelbl.setText(usuario.getNombres() + " " + usuario.getApellidos() + " !");
        rutlbl.setText(usuario.getRut());

        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");
        DaoRegistro daoRegistro = new DaoRegistro(c);
        daoRegistro.cambiarAFechaLimite();


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

        registrobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegistroMenu rm = new RegistroMenu(usuario);
                rm.setVisible(true);
                dispose();
            }
        });
    }
}
