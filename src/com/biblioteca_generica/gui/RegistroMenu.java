package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoLibro;
import com.biblioteca_generica.dao.DaoRegistro;
import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Libro;
import com.biblioteca_generica.model.Registro;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroMenu extends JFrame{
    private JPanel panel1;
    private JButton buttonVolver;
    private JButton buttonEntregar;
    private JButton buttonLibroDevuelto;
    private JTable tableRegistros;
    private JButton buttonVerDatosUser;
    private JButton buttonVerDatosDelLibro;
    private Usuario usuario;

    public RegistroMenu(Usuario usuario){
        super("Menu de Registros");
        setVisible(true);
        setSize(600,700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel1);
        this.usuario = usuario;

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Rut Usuario");
        model.addColumn("Libro");
        model.addColumn("Fecha Solicitud");
        model.addColumn("Fecha Entrega");
        model.addColumn("Estado");


        Conexion c = new Conexion("localhost",3306,"biblioteca_generica","root","");

        DaoRegistro daoRegistro = new DaoRegistro(c);
        DaoUsuario daoUsuario = new DaoUsuario(c);
        DaoLibro daoLibro = new DaoLibro(c);
        List<Registro> listRegistros = new ArrayList<>();
        listRegistros = daoRegistro.allgetRegistros();

        String Datos[] = new String[6];

        for(Registro r : listRegistros){
            Datos[0] = Integer.toString(r.getId());
            Datos[1] = daoUsuario.getUserFromId(r.getId()).getRut();
            Datos[2] = daoLibro.getNombreLibro(r.getLibro_id());
            Datos[3] = r.getFecha_solicitud();
            Datos[4] = r.getFecha_entrega();
            Datos[5] = daoRegistro.retornarEstadoRegistro(r.getEstado_registro_id());

            model.addRow(Datos);
            tableRegistros.setModel(model);
        }

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mu = new MainMenu(usuario);
                dispose();
            }
        });

        buttonEntregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cuentaFilasSeleccionadas = tableRegistros.getSelectedRowCount();
                if (cuentaFilasSeleccionadas == 0) {
                    JOptionPane.showMessageDialog(null, "Porfavor, seleccione un registro");
                } else if (cuentaFilasSeleccionadas > 1) {
                    JOptionPane.showMessageDialog(null, "Porfavor,seleccione SOLO un registro");
                } else if (cuentaFilasSeleccionadas == 1) {
                    String est = (String) model.getValueAt(tableRegistros.getSelectedRow(), 5);
                    if (est.equals("Reservado")) {
                        try {
                            String i = (String) model.getValueAt(tableRegistros.getSelectedRow(),0);
                            int id_r = Integer.parseInt(i);
                            daoRegistro.entregarLibro(id_r);

                            model.setRowCount(0);

                            List<Registro> listRegistros = new ArrayList<>();
                            listRegistros = daoRegistro.allgetRegistros();

                            for(Registro r : listRegistros){
                                Datos[0] = Integer.toString(r.getId());
                                Datos[1] = daoUsuario.getUserFromId(r.getId()).getRut();
                                Datos[2] = daoLibro.getNombreLibro(r.getLibro_id());
                                Datos[3] = r.getFecha_solicitud();
                                Datos[4] = r.getFecha_entrega();
                                Datos[5] = daoRegistro.retornarEstadoRegistro(r.getEstado_registro_id());

                                model.addRow(Datos);
                                tableRegistros.setModel(model);
                            }
                            JOptionPane.showMessageDialog(null, "El libro ha sido entegado exitosamente");
                        } catch (NumberFormatException excepcion) {
                            JOptionPane.showMessageDialog(null, "No se ha podido confirmar la entrega");
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Solo se puede entregar un libro Reservado");
                    }
                }
            }
        });

        buttonLibroDevuelto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cuentaFilasSeleccionadas = tableRegistros.getSelectedRowCount();
                if (cuentaFilasSeleccionadas == 0) {
                    JOptionPane.showMessageDialog(null, "Porfavor, seleccione un registro");
                } else if (cuentaFilasSeleccionadas > 1) {
                    JOptionPane.showMessageDialog(null, "Porfavor,seleccione SOLO un registro");
                } else if (cuentaFilasSeleccionadas == 1) {
                    String est = (String) model.getValueAt(tableRegistros.getSelectedRow(), 5);
                    if (est.equals("Esperando Devolución") || est.equals("Fecha limite exedida")) {
                        try {
                            String i = (String) model.getValueAt(tableRegistros.getSelectedRow(),0);
                            String l = (String) model.getValueAt(tableRegistros.getSelectedRow(),2);
                            int id_r = Integer.parseInt(i);
                            int id_l = daoLibro.getIdLibro(l);
                            daoRegistro.recibirLibro(id_r,id_l);

                            model.setRowCount(0);

                            List<Registro> listRegistros = new ArrayList<>();
                            listRegistros = daoRegistro.allgetRegistros();

                            for(Registro r : listRegistros){
                                Datos[0] = Integer.toString(r.getId());
                                Datos[1] = daoUsuario.getUserFromId(r.getId()).getRut();
                                Datos[2] = daoLibro.getNombreLibro(r.getLibro_id());
                                Datos[3] = r.getFecha_solicitud();
                                Datos[4] = r.getFecha_entrega();
                                Datos[5] = daoRegistro.retornarEstadoRegistro(r.getEstado_registro_id());

                                model.addRow(Datos);
                                tableRegistros.setModel(model);
                            }
                            JOptionPane.showMessageDialog(null, "El libro ha sido recibido exitosamente");
                        } catch (NumberFormatException excepcion) {
                            JOptionPane.showMessageDialog(null, "No se ha podido confirmar la entrega");
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Solo se puede recibir un libro que tenga una devolucion pendiente");
                    }
                }
            }
        });

        buttonVerDatosDelLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cuentaFilasSeleccionadas = tableRegistros.getSelectedRowCount();
                if (cuentaFilasSeleccionadas == 0) {
                    JOptionPane.showMessageDialog(null, "Porfavor, seleccione un registro");
                } else if (cuentaFilasSeleccionadas > 1) {
                    JOptionPane.showMessageDialog(null, "Porfavor,seleccione SOLO un registro");
                } else if (cuentaFilasSeleccionadas == 1) {
                    String est = (String) model.getValueAt(tableRegistros.getSelectedRow(), 2);
                    Libro l = daoLibro.getLibro(est);
                    DatosLibro dl = new DatosLibro(l);
                }
            }
        });

        buttonVerDatosUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cuentaFilasSeleccionadas = tableRegistros.getSelectedRowCount();
                if (cuentaFilasSeleccionadas == 0) {
                    JOptionPane.showMessageDialog(null, "Porfavor, seleccione un registro");
                } else if (cuentaFilasSeleccionadas > 1) {
                    JOptionPane.showMessageDialog(null, "Porfavor,seleccione SOLO un registro");
                } else if (cuentaFilasSeleccionadas == 1) {
                    String est = (String) model.getValueAt(tableRegistros.getSelectedRow(), 1);
                    Usuario u = daoUsuario.getUserFromRut(est);
                    DatosUsuario dl = new DatosUsuario(u);
                }
            }
        });
    }
}
