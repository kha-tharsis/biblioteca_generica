package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Categoria;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    private DaoUsuario daoUsuario;
    private Usuario usuario;

    public CrearUsuario(){
        super("Crear Usuario");
        setVisible(true);
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);

        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");
        daoUsuario = new DaoUsuario(c);



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (txtrut.getText().isEmpty() || passwordField1.getText().isEmpty() || txtnombre.getText().isEmpty() || txtapellido.getText().isEmpty() || txtcorreo.getText().isEmpty() || txtfechanaci.getText().isEmpty() || txtfono.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Hay campos vacios","ERROR",0);
                }else {
                    try {
                        String rut = txtrut.getText();
                        String pass = passwordField1.getText();
                        String nombre = txtnombre.getText();
                        String apellido = txtapellido.getText();
                        String correo = txtcorreo.getText();
                        String fecha_nacimiento = txtfechanaci.getText();
                        int telefono = Integer.parseInt(txtfono.getText());

                        daoUsuario.addUser(rut, pass, nombre, apellido, correo, fecha_nacimiento, telefono);
                        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                        limpiarTxt();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Alguno de los valores ingresados pueden no ser validos");
                    }
                }
            }
        });

    }
    private void limpiarTxt(){
        txtrut.setText("");
        passwordField1.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtcorreo.setText("");
        txtfechanaci.setText("");
        txtfono.setText("");
    }
}
