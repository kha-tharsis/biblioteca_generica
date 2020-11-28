package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;

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
    private JButton buttonVerMasDatos;
    private JButton buttonSolicitarLibro;
    private JList list1;

    public MenuUsuario(Usuario usuario){
        super("Menu");
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;
    }
}
