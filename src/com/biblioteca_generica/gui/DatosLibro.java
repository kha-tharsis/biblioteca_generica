package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoLibro;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatosLibro extends JFrame {

    private Libro libro;
    private JPanel panel1;
    private JButton button1;
    private JLabel labeld;
    private JLabel labelTitulo;
    private JLabel labelFecha;
    private JLabel labelAutor;
    private JLabel labelCategoria;
    private JLabel labelPaginas;
    private JLabel labelEstado;

    public DatosLibro(Libro libro){
        super("Datos del Libro");
        setVisible(true);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);

        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");
        DaoLibro daoLibro = new DaoLibro(c);

        labeld.setText(libro.getId()+"");
        labelTitulo.setText(libro.getTitulo());
        labelAutor.setText(libro.getAutor());
        labelFecha.setText(libro.getFecha_publicacion());
        labelCategoria.setText(daoLibro.getCategoriaPorId(libro.getCategoria_id_fk()));
        labelPaginas.setText(libro.getNumero_paginas()+"");
        labelEstado.setText(daoLibro.getEstadoPorInt(libro.getEstado()));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }
}
