package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarUsuario extends JFrame {
    private Usuario usuario;
    private JPanel panel1;
    private JTextField textFieldCorreo;
    private JTextField textFieldFono;
    private JButton actualizarButton;
    private JButton volverButton;

    public ActualizarUsuario(Usuario usuario){
        super("Menu");
        setVisible(true);
        setSize(350,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;
        textFieldCorreo.setText(usuario.getCorreo());
        textFieldFono.setText(usuario.getFono()+"");


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MenuUsuario mu = new MenuUsuario(usuario);
                dispose();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(textFieldCorreo.getText().isEmpty() || textFieldFono.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese los datos porfavor");
                }
                else{
                    Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");
                    DaoUsuario daoUsuario = new DaoUsuario(c);
                    String correo = textFieldCorreo.getText();
                    String f = textFieldFono.getText();
                    int fono = 0;
                    try {
                        fono = Integer.parseInt(f);
                    } catch (NumberFormatException excepcion) {
                        JOptionPane.showMessageDialog(null, "Ingrese datos numericos en el numero");
                    }
                    daoUsuario.actualizarDatosUsuario(correo,fono,usuario.getId());

                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                    InicioSesion is = new InicioSesion();
                    dispose();
                }

            }
        });
    }

}
