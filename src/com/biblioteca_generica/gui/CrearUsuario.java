package com.biblioteca_generica.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuario extends JFrame{
    private JPanel mainPanel;
    private JTextField txtrut;
    private JPasswordField passwordField1;
    private JTextField txtnombre;
    private JTextField txtapellido;
    private JTextField txtcorreo;
    private JTextField txtfechanaci;
    private JTextField txtfono;
    private JButton crearButton;
    private JButton atrásButton;

    public CrearUsuario(){
        super("Crear Usuario");
        setVisible(true);
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int telefono = Integer.parseInt(txtfono.getText());
            }
        });

        atrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InicioSesion login = new InicioSesion();
                login.setVisible(true);
                setVisible(false);
            }
        });

    }
}
