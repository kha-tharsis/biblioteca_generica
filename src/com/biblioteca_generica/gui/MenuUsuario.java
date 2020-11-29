package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoLibro;
import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Categoria;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuUsuario extends JFrame {
    private JPanel mainPanel;

    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DaoUsuario daoUsuario;
    private JPanel panel1;
    private JLabel lableNombres;
    private JLabel labelRut;
    private JLabel labelApellidos;
    private JLabel labelActualizarDatos;
    private JList listaLibrosActuales;
    private JButton buttonLibrosDisp;
    private JButton buttonAllLibros;
    private JComboBox comboBoxFiltroCategoria;
    private JComboBox comboBoxCategoria;
    private JTextField textFieldFiltro;
    private JButton buttonFiltrar;
    private JButton buttonCerrarSesion;
    private JButton buttonSolicitar;
    private JTable tablaLibros;

    public MenuUsuario(Usuario usuario){
        super("Menu");
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Titulo");
        model.addColumn("Fecha Publicacion");
        model.addColumn("Autor");
        model.addColumn("Categoria");
        model.addColumn("N° Paginas");
        model.addColumn("¿Disponible?");

        DefaultComboBoxModel combo = new DefaultComboBoxModel();

        String Datos[] = new String [6];
        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");

        DaoLibro daoLibro = new DaoLibro(c);
        List<Libro> allLibros = new ArrayList<>();
        allLibros = daoLibro.getAllLibros();

        List<Categoria> allCategorias = new ArrayList<>();
        allCategorias = daoLibro.getAllCategorias();

        for (Categoria ca : allCategorias){
            combo.addElement(ca);
            comboBoxCategoria.setModel(combo);
        }

        for(Libro libro : allLibros) {
            Datos[0] = libro.getTitulo();
            Datos[1] = libro.getFecha_publicacion();
            Datos[2] = libro.getAutor();
            Datos[3] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
            Datos[4] = Integer.toString(libro.getNumero_paginas());
            Datos[5] = daoLibro.getEstadoPorInt(libro.getEstado());


            model.addRow(Datos);
            tablaLibros.setModel(model);
        }


        buttonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InicioSesion is = new InicioSesion();
                dispose();
            }
        });

        buttonAllLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.setRowCount(0);
                List<Libro> allLibros = new ArrayList<>();
                allLibros = daoLibro.getAllLibros();
                for(Libro libro : allLibros) {
                    Datos[0] = libro.getTitulo();
                    Datos[1] = libro.getFecha_publicacion();
                    Datos[2] = libro.getAutor();
                    Datos[3] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[4] = Integer.toString(libro.getNumero_paginas());
                    Datos[5] = daoLibro.getEstadoPorInt(libro.getEstado());


                    model.addRow(Datos);
                    tablaLibros.setModel(model);
                }
            }
        });

        buttonLibrosDisp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.setRowCount(0);
                List<Libro> allLibros = new ArrayList<>();
                allLibros = daoLibro.getLibrosDisponibles();
                for(Libro libro : allLibros) {
                    Datos[0] = libro.getTitulo();
                    Datos[1] = libro.getFecha_publicacion();
                    Datos[2] = libro.getAutor();
                    Datos[3] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[4] = Integer.toString(libro.getNumero_paginas());
                    Datos[5] = daoLibro.getEstadoPorInt(libro.getEstado());


                    model.addRow(Datos);
                    tablaLibros.setModel(model);
                }
            }
        });

    }
}
