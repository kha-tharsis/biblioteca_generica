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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private DaoLibro daoLibro;
    private DefaultTableModel tablemodel;
    private DefaultComboBoxModel combo;

    public LibroMenu(Usuario usuario){
        super("Menu de Libros");
        setVisible(true);
        setSize(660,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                dispose();
            }
        });

        eliminarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtdisponible1.setText(Integer.toString(daoLibro.cantidadLibros(1)));
                txtNodiponible0.setText(Integer.toString(daoLibro.cantidadLibros(0)));

                int fila = tablalibros.getSelectedRow();
                if (fila < 1){
                    JOptionPane.showMessageDialog(null,"Selecione un libro de la tabla","Selecionar",2);
                }else {
                    try {
                        String eliminar = tablemodel.getValueAt(fila,0).toString();
                        tablemodel.removeRow(fila);
                        System.out.println(eliminar+"---");
                        daoLibro.deleteLibro(eliminar);
                        JOptionPane.showMessageDialog(null,"El libro ha sido eliminado");
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"ERROR al eliminar" + e.getMessage());
                    }
                }
            }
        });

        añadirbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtdisponible1.setText(Integer.toString(daoLibro.cantidadLibros(1)));
                txtNodiponible0.setText(Integer.toString(daoLibro.cantidadLibros(0)));

                if (titulotxt.getText().isEmpty() || fechatxt.getText().isEmpty() || paginastxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe ingresar un elemento en todos los campos","ERROR",0);
                }else{
                    try {
                        String titulo = titulotxt.getText();
                        String fecha_publicacion = fechatxt.getText();
                        String autor = autortxt.getText();
                        Categoria c = (Categoria)combo.getSelectedItem();
                        int categoria_id_fk = c.getId();
                        int numero_paginas = Integer.parseInt(paginastxt.getText());

                        daoLibro.insertarLibro(titulo,fecha_publicacion,autor,categoria_id_fk,numero_paginas);
                        JOptionPane.showMessageDialog(null,"El libro ha sido agregado exitosamente");
                        limpiarTxt();
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Alguno de los valores ingresados pueden no ser validos");
                    }
                    tablemodel.setRowCount(0);
                    List<Libro> allLibros;
                    allLibros = daoLibro.getAllLibros();
                    for(Libro libro : allLibros) {
                        Datos[0] = Integer.toString(libro.getId());
                        Datos[1] = libro.getTitulo();
                        Datos[2] = libro.getFecha_publicacion();
                        Datos[3] = libro.getAutor();
                        Datos[4] = daoLibro.getCategoriaPorId(libro.getCategoria_id_fk());
                        Datos[5] = Integer.toString(libro.getNumero_paginas());
                        Datos[6] = daoLibro.getEstadoPorInt(libro.getEstado());
                        System.out.println(Datos[1]);

                        tablemodel.addRow(Datos);
                        tablalibros.setModel(tablemodel);
                    }
                }
            }
        });

        tablalibros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                //Actualiza un dato directamente de la tabla al hacer Enter
                int fila = tablalibros.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    int id = Integer.parseInt( tablemodel.getValueAt(fila,0).toString());
                    String titulo = tablemodel.getValueAt(fila,1).toString();
                    String fecha = tablemodel.getValueAt(fila,2).toString();
                    String autor = tablemodel.getValueAt(fila,3).toString();

                    String cat = tablemodel.getValueAt(fila,4).toString();
                    int categoria = daoLibro.getIdporCategoria(cat);
                    if (categoria != 0){
                        int pagina = Integer.parseInt( tablemodel.getValueAt(fila,5).toString());
                        daoLibro.updateLibro(id,titulo,fecha,autor,categoria,pagina);
                        JOptionPane.showMessageDialog(null,"Libro actualizado con éxito!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Ingrese una categoria correcta");
                    }
                }
            }
        });
    }
    private void limpiarTxt(){
        titulotxt.setText("");
        fechatxt.setText("");
        autortxt.setText("");
        paginastxt.setText("");
    }
}
