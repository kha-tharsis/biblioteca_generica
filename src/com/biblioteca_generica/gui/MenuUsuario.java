package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoLibro;
import com.biblioteca_generica.dao.DaoRegistro;
import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class MenuUsuario extends JFrame {
    private JPanel mainPanel;

    private InicioSesion inicioSesion;
    private Usuario usuario;
    private DaoUsuario daoUsuario;
    private JPanel panel1;
    private JLabel labelNombres;
    private JLabel labelRut;
    private JLabel labelApellidos;
    private JButton buttonLibrosDisp;
    private JButton buttonAllLibros;
    private JComboBox comboBoxFiltro;
    private JComboBox comboBoxCategoria;
    private JTextField textFieldFiltro;
    private JButton buttonFiltrar;
    private JButton buttonCerrarSesion;
    private JButton buttonSolicitar;
    private JTable tablaLibros;
    private JButton buttonBuscarPorCategoria;
    private JButton actualizarDatosButton;
    private JTable tableLibrosUser;

    public MenuUsuario(Usuario usuario){
        super("Menu");
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;
        labelNombres.setText(usuario.getNombres());
        labelApellidos.setText(usuario.getApellidos());
        labelRut.setText(usuario.getRut());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Titulo");
        model.addColumn("Fecha Publicacion");
        model.addColumn("Autor");
        model.addColumn("Categoria");
        model.addColumn("N° Paginas");
        model.addColumn("¿Disponible?");

        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("Libro");
        model2.addColumn("Estado");
        model2.addColumn("Fecha a entregar");


        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        DefaultComboBoxModel combo2 = new DefaultComboBoxModel();

        String Datos[] = new String [7];
        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");

        DaoLibro daoLibro = new DaoLibro(c);
        DaoRegistro daoRegistro = new DaoRegistro(c);
        List<Libro> allLibros = new ArrayList<>();
        allLibros = daoLibro.getAllLibros();

        List<Registro> listRegistrosuser = new ArrayList<>();
        listRegistrosuser = daoRegistro.getRegistrosActualesporIdUser(usuario.getId());

        String Datos2[] = new String[3];
        for(Registro r : listRegistrosuser){
            Datos2[0] = daoLibro.getNombreLibro(r.getLibro_id());
            Datos2[1] = r.getFecha_entrega();
            Datos2[2] = daoRegistro.retornarEstadoRegistro(r.getEstado_registro_id());

            model2.addRow(Datos2);
            tableLibrosUser.setModel(model2);
        }

        List<String> listaFiltro = new ArrayList<>();
        listaFiltro.add("id");
        listaFiltro.add("titulo");
        listaFiltro.add("autor");


        for (String s : listaFiltro){
            combo2.addElement(s);
            comboBoxFiltro.setModel(combo2);
        }

        List<Categoria> allCategorias = new ArrayList<>();
        allCategorias = daoLibro.getAllCategorias();

        for (Categoria ca : allCategorias){
            combo.addElement(ca);
            comboBoxCategoria.setModel(combo);
        }

        for(Libro libro : allLibros) {
            Datos[0] = Integer.toString(libro.getId());
            Datos[1] = libro.getTitulo();
            Datos[2] = libro.getFecha_publicacion();
            Datos[3] = libro.getAutor();
            Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
            Datos[5] = Integer.toString(libro.getNumero_paginas());
            Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


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
                    Datos[0] = Integer.toString(libro.getId());
                    Datos[1] = libro.getTitulo();
                    Datos[2] = libro.getFecha_publicacion();
                    Datos[3] = libro.getAutor();
                    Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[5] = Integer.toString(libro.getNumero_paginas());
                    Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


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
                    Datos[0] = Integer.toString(libro.getId());
                    Datos[1] = libro.getTitulo();
                    Datos[2] = libro.getFecha_publicacion();
                    Datos[3] = libro.getAutor();
                    Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[5] = Integer.toString(libro.getNumero_paginas());
                    Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


                    model.addRow(Datos);
                    tablaLibros.setModel(model);
                }
            }
        });

        buttonBuscarPorCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Categoria c = (Categoria) comboBoxCategoria.getSelectedItem();
                model.setRowCount(0);
                List<Libro> allLibros = new ArrayList<>();
                allLibros = daoLibro.getLibrosPoridCategoria(c.getId());
                for(Libro libro : allLibros) {
                    Datos[0] = Integer.toString(libro.getId());
                    Datos[1] = libro.getTitulo();
                    Datos[2] = libro.getFecha_publicacion();
                    Datos[3] = libro.getAutor();
                    Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[5] = Integer.toString(libro.getNumero_paginas());
                    Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


                    model.addRow(Datos);
                    tablaLibros.setModel(model);
                }
            }
        });

        buttonFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedFiltro = comboBoxFiltro.getSelectedItem().toString();
                model.setRowCount(0);
                List<Libro> librosFiltrados = new ArrayList<>();
                librosFiltrados = daoLibro.filtrarLibro(selectedFiltro,textFieldFiltro.getText());
                for(Libro libro : librosFiltrados) {
                    Datos[0] = Integer.toString(libro.getId());
                    Datos[1] = libro.getTitulo();
                    Datos[2] = libro.getFecha_publicacion();
                    Datos[3] = libro.getAutor();
                    Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                    Datos[5] = Integer.toString(libro.getNumero_paginas());
                    Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());


                    model.addRow(Datos);
                    tablaLibros.setModel(model);
                }

            }
        });

        buttonSolicitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cuentaFilasSeleccionadas = tablaLibros.getSelectedRowCount();
                if (cuentaFilasSeleccionadas == 0) {
                    JOptionPane.showMessageDialog(null,"Porfavor, seleccione un libro");
                }
                else if(cuentaFilasSeleccionadas > 1){
                    JOptionPane.showMessageDialog(null,"Porfavor,seleccione SOLO un libro");
                }
                else if(cuentaFilasSeleccionadas == 1){
                    String disp= (String) model.getValueAt(tablaLibros.getSelectedRow(),6);
                    if(disp == "No Disponible"){
                        JOptionPane.showMessageDialog(null,"este libro no se encuentra disponible actualmente");
                    }
                    else if(disp == "Disponible"){
                        boolean e = daoRegistro.exedeLimite(usuario.getId());

                        if(e == true){
                            JOptionPane.showMessageDialog(null,"usted a excedido el limite de libros permitidos");
                        }
                        else if(e == false){
                            String dato= (String) model.getValueAt(tablaLibros.getSelectedRow(),0);
                            int id_libro = 0;
                            try {
                                id_libro = Integer.parseInt(dato);
                            } catch (NumberFormatException excepcion) {

                            }
                            daoLibro.solicitarLibro(id_libro,usuario.getId());
                            JOptionPane.showMessageDialog(null,"el libro se ha pedido correctamente");
                        }

                    }

                }
            }
        });
    }
}
