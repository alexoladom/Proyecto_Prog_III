package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import Clases.Datos;
import Clases.Tarea;
import Clases.Trabajador;

public class VentanaTrabajador extends JFrame {
    protected JProgressBar tareasHacer;
    protected JTable tablaDatos;
    protected JButton botonCerrar, botonTareaHecha;
    protected List<Tarea> tareasPendientes; //= trabajador.getListaTareasPorHacer(); // ejemplo de tareas pendientes que tiene un trabajador

    public VentanaTrabajador(Datos datos, Trabajador trabajador) {
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setSize(900,350);
    	setLocationRelativeTo(null);
    	setTitle("Trabajador: "+ trabajador.getNombre()+" "+trabajador.getApellido1());
    	JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuPerfil = new JMenu();
		menuBar.add(menuPerfil);
		JMenuItem cambiarFotoPerfil = new JMenuItem("CAMBIAR FOTO DE PERFIL");
		menuPerfil.add(cambiarFotoPerfil);
		JMenuItem informacionTrabajador = new JMenuItem("VER/EDITAR MIS DATOS");
		menuPerfil.add(informacionTrabajador);
		JMenuItem cerrarSesion = new JMenuItem("CERRAR SESION");
		menuPerfil.add(cerrarSesion);
		
		
		ImageIcon imagenPerfil = new ImageIcon("src/Imagenes/imagenPerfilpng.png");
		Image imagenPerfilEscala = imagenPerfil.getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH);
		menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));

        botonCerrar = new JButton("CERRAR");
        botonTareaHecha = new JButton("TAREA HECHA");

        //tareasHacer = new JProgressBar(0, tareasPendientes);
        //tareasHacer.setStringPainted(true);
        //tareasHacer.setValue(tareasPendientes);
        
        
        //Panel del menu
        JPanel pMostrarInfo = new JPanel();
        

        String[] columnNames = {"DNI", "Nombre", "Apellido", "Email", "Direccion", "Fecha de Nacimiento", "Telefono", "Salario", "Horas Trabajadas"};
        Object[] data = {
        	    trabajador.getDni(),
        	    trabajador.getNombre(),
        	    trabajador.getApellido1(),
        	    trabajador.getEmail(),
        	    trabajador.getDireccion(),
        	    trabajador.getfNacimiento(),
        	    trabajador.getTelefono(),
        	    trabajador.getSalario(),
        	    trabajador.getNumHorasTrabajadas(),
        	};

        	tablaDatos = new JTable(new Object[][]{ data }, columnNames);

        //getContentPane().add(tareasHacer, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        pMostrarInfo.add(scrollPane);

        getContentPane().add(pMostrarInfo, BorderLayout.CENTER);

        JPanel pBotones = new JPanel();
        pBotones.add(botonCerrar);
        pBotones.add(botonTareaHecha);
        getContentPane().add(pBotones, BorderLayout.SOUTH);

        botonCerrar.addActionListener((e) -> {
            System.exit(0);
        });
//        botonTareaHecha.addActionListener((e) -> {
//            if (tareasPendientes > 0) {
//                tareasPendientes--;
//                tareasHacer.setValue(tareasPendientes);
//            }
//        });
        pMostrarInfo.add(tablaDatos);
        setVisible(true);
    }
}
