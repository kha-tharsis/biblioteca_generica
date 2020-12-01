package com.biblioteca_generica.gui;

import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroMenu extends JFrame{
    private JPanel panel1;
    private JButton buttonVolver;
    private JButton buttonEntregar;
    private JButton buttonLibroDevuelto;
    private JTable tableRegistros;
    private JButton buttonVerDatosUser;
    private JButton buttonVerDatosDelLibro;
    private Usuario usuario;

    public RegistroMenu(Usuario usuario){
        super("Menu de Registros");
        setVisible(true);
        setSize(600,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;



        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mu = new MainMenu(usuario);
                dispose();
            }
        });

        buttonEntregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });

        buttonLibroDevuelto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        buttonVerDatosDelLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        buttonVerDatosUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }
}
