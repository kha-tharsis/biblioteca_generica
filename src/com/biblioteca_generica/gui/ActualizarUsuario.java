package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.*;
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
        super("Actualizar Usuario");
        setVisible(true);
        setSize(350,200);
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
                        if(daoUsuario.correoDisponible(correo,usuario.getId()) && daoUsuario.fonoDisponible(fono,usuario.getId())){
                            JOptionPane.showMessageDialog(null, "El número y el correo están registrados por otro usuario, porfavor ingrese otro");
                        }
                        else if(daoUsuario.correoDisponible(correo,usuario.getId()) && !daoUsuario.fonoDisponible(fono,usuario.getId())){
                            JOptionPane.showMessageDialog(null, "El correo está registrado por otro usuario");
                        }
                        else if(!daoUsuario.correoDisponible(correo,usuario.getId()) && daoUsuario.fonoDisponible(fono,usuario.getId())){
                            JOptionPane.showMessageDialog(null, "El teléfono está registrado por otro usuario");
                        }
                        else if (!daoUsuario.correoDisponible(correo,usuario.getId()) && !daoUsuario.fonoDisponible(fono,usuario.getId())){
                            daoUsuario.actualizarDatosUsuario(correo,fono,usuario.getId());
                            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                            MenuUsuario meus = new MenuUsuario(usuario);
                            dispose();
                        }
                    } catch (NumberFormatException excepcion) {
                        JOptionPane.showMessageDialog(null, "Ingrese datos numericos en el teléfono");
                    }
                }

            }
        });
    }

}
