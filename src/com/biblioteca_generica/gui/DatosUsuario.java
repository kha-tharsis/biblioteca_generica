package com.biblioteca_generica.gui;

import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatosUsuario extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private JLabel labelId;
    private JLabel labelNombre;
    private JLabel labelApellidos;
    private JLabel labelRut;
    private JLabel labelNacimiento;
    private JLabel labelCorreo;
    private JLabel labelFono;
    private Usuario usuario;

    public DatosUsuario(Usuario usuario){
        super("Datos del Usuario");
        setVisible(true);
        setSize(320,320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);

        labelId.setText(usuario.getId()+"");
        labelNombre.setText(usuario.getNombres());
        labelApellidos.setText(usuario.getApellidos());
        labelRut.setText(usuario.getRut());
        labelCorreo.setText(usuario.getCorreo());
        labelNacimiento.setText(usuario.getFecha_nacimiento());
        labelFono.setText(usuario.getFono()+"");


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });


    }
}
