package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoLibro;
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

public class LibroMenu extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField titulotxt;
    private JTextField fechatxt;
    private JTextField autortxt;
    private JTextField paginastxt;
    private JComboBox categoriacombo;
    private JButton añadirbtn;
    private JTable tablalibros;
    private JButton eliminarbtn;
    private JButton atrasbtn;
    private JTextField txtdisponible1;
    private JTextField txtNodiponible0;
    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DefaultTableModel tablemodel;
    private DefaultComboBoxModel combo;

    public LibroMenu(Usuario usuario){
        super("Menu");
        setVisible(true);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);
        this.usuario = usuario;

        //tabla libros
        tablemodel = new DefaultTableModel();
        tablalibros.setModel(tablemodel);
        tablemodel.addColumn("ID");
        tablemodel.addColumn("Titulo");
        tablemodel.addColumn("Fecha Publicacion");
        tablemodel.addColumn("Autor");
        tablemodel.addColumn("Categoria");
        tablemodel.addColumn("N° Paginas");
        tablemodel.addColumn("¿Disponible?");

        //combobox
        combo = new DefaultComboBoxModel<>();
        categoriacombo.setModel(combo);

        String Datos[] = new String [7];
        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");

        DaoLibro daoLibro = new DaoLibro(c);
        List<Libro> allLibros;
        allLibros = daoLibro.getAllLibros();

        List<Categoria> allCategorias;
        allCategorias = daoLibro.getAllCategorias();

        for (Categoria ca : allCategorias){
            combo.addElement(ca);
            categoriacombo.setModel(combo);
        }

        for(Libro libro : allLibros) {
            Datos[0] = Integer.toString(libro.getId());
            Datos[1] = libro.getTitulo();
            Datos[2] = libro.getFecha_publicacion();
            Datos[3] = libro.getAutor();
            Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
            Datos[5] = Integer.toString(libro.getNumero_paginas());
            Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


            tablemodel.addRow(Datos);
            tablalibros.setModel(tablemodel);
        }
        txtdisponible1.setText(Integer.toString(daoLibro.cantidadLibros(1)));
        txtNodiponible0.setText(Integer.toString(daoLibro.cantidadLibros(0)));


        atrasbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(usuario);
                mainMenu.setVisible(true);
                setVisible(false);
            }
        });

        eliminarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //eliminar libro
            }
        });

        añadirbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtdisponible1.setText(Integer.toString(daoLibro.cantidadLibros(1)));
                txtNodiponible0.setText(Integer.toString(daoLibro.cantidadLibros(0)));

                String titulo = titulotxt.getText();
                String fecha_publicacion = fechatxt.getText();
                String autor = autortxt.getText();
                //int categoria_id_fk = (String)combo.getSelectedItem(daoLibro.getCategoriaPorId());
                int numero_paginas = Integer.parseInt(paginastxt.getText());

                //Libro libro = new Libro(titulo,fecha_publicacion,autor,categoria_id_fk,numero_paginas,1);
                //daoLibro.insertarLibro(libro);
            }
        });
    }
}
