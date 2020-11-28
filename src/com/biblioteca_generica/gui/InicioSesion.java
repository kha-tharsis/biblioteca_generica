package com.biblioteca_generica.gui;

import com.biblioteca_generica.dao.DaoUsuario;
import com.biblioteca_generica.model.Conexion;
import com.biblioteca_generica.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class InicioSesion extends JFrame{
    private JPanel mainPanel;
    private JTextField ruttxt;
    private JPasswordField passtxt;
    private JButton ingresarbtn;
    private JLabel registrolbl;

    private DaoUsuario daoUsuario;
    private Conexion con;
    private MainMenu mainMenu;
    private MenuUsuario menuUsuario;

    public InicioSesion(){
        super("Inicio de Sesión");
        setVisible(true);
        setSize(350,150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainPanel);

        String ip = "localhost";
        int port = 3306;
        String db = "biblioteca_generica";
        String usuario = "root";
        String pass = "";
        con = new Conexion(ip,port,db,usuario,pass);
        daoUsuario = new DaoUsuario(con);

        ingresarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String rut = ruttxt.getText().trim();
                String password = new String(passtxt.getPassword());
                Usuario usuario = new Usuario(rut,password);
                if (rut.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Hay casillas vacías");
                }else{
                    if (daoUsuario.ValidarUsuario(usuario)){
                        Usuario u = daoUsuario.getUserFromRut(rut);
                        if(u.getTipo_usuario() == 1){
                            SwingUtilities.invokeLater(()-> mainMenu = new MainMenu(null, u));
                        }else if(u.getTipo_usuario() == 2){
                            SwingUtilities.invokeLater(()-> menuUsuario = new MenuUsuario(null,u));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"User or password invalid");
                    }
                }
            }
        });

        registrolbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                CrearUsuario crear = new CrearUsuario();
                crear.setVisible(true);
            }
        });


        registrolbl.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                registrolbl.setForeground(Color.decode("#0900AF"));
            }
        });

        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                registrolbl.setForeground(Color.black);
            }
        });
    }
}
