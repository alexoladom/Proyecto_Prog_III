package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Cliente;
import Clases.Datos;

public class VentanaInicioCliente extends JFrame {
	protected JButton botonAtras1, botonRegistrarme, botonRegistro,  botonIniSesion,botonAtras2;
	protected JLabel lblNombre, lblContra,lblContra2, lblDNI,lblDNI2,lblApellido,lblEmail,lblDireccion,lblFechaNacimiento,lblTelefono;
	protected JPanel pBotones, pCentro, pArriba, pCentroRegistro;
	protected JTextField textoNombre, textoContra,textoContra2,textoDNI,textoDNI2,textoApellido,textoEmail,textoDireccion,textoFechaNacimiento,textoTelefono;
	protected Map<String, Cliente> mapaClientesPorDNI;
	protected Datos datos;

	public VentanaInicioCliente(Datos datos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		
		this.datos = datos;
		
		mapaClientesPorDNI = datos.getMapaClientesPorDNI();
		botonAtras1 = new JButton("ATRAS");
		botonAtras2 = new JButton("ATRAS");
		botonAtras2.setVisible(false);
		botonRegistrarme = new JButton("REGISTRARME");
		botonRegistro = new JButton("REGISTRO");
		botonRegistro.setVisible(false);
		botonIniSesion = new JButton("INICIO DE SESION");

		lblNombre = new JLabel("Introduzca su Nombre: ");
		lblContra = new JLabel("Introduzca su contraseña: ");
		lblContra2 = new JLabel("Introduzca su contraseña: ");
		lblDNI = new JLabel("Introduzca su DNI: ");
		lblDNI2 = new JLabel("Introduzca su DNI");
		lblApellido = new JLabel("Introdzca su apellido: ");
		lblEmail = new JLabel("Introduzca su e-mail: ");
		lblDireccion = new JLabel("Introduzca su direccion: ");
		lblFechaNacimiento = new JLabel("Introduzca su fecha de nacimiento(dd/mm/aaaa): ");
		lblTelefono = new JLabel("Introduzca su teléfono: ");

		

		pBotones = new JPanel();
		pCentro = new JPanel();
		pCentroRegistro = new JPanel();
		pCentroRegistro.setVisible(false);
		pArriba = new JPanel();

		textoNombre = new JTextField(20);
		textoContra = new JTextField(20);
		textoContra2 = new JTextField(20);
		textoDNI = new JTextField(20);
		textoDNI2 = new JTextField(20);
		textoApellido = new JTextField(20);
		textoEmail = new JTextField(20);
		textoDireccion = new JTextField(20);
		textoFechaNacimiento= new JTextField(20);
		textoTelefono= new JTextField(20);
		
		
		//Diseño del panel que aparece cuando queremos registrar un nuevo cliente
		
		pCentroRegistro.setLayout(new GridLayout (8,2));
		pCentroRegistro.add(lblNombre);
		pCentroRegistro.add(textoNombre);
		pCentroRegistro.add(lblApellido);
		pCentroRegistro.add(textoApellido);
		pCentroRegistro.add(lblContra2);
		pCentroRegistro.add(textoContra2);
		pCentroRegistro.add(lblDNI2);
		pCentroRegistro.add(textoDNI2);
		pCentroRegistro.add(lblEmail);
		pCentroRegistro.add(textoEmail);
		pCentroRegistro.add(lblDireccion);
		pCentroRegistro.add(textoDireccion);
		pCentroRegistro.add(lblFechaNacimiento);
		pCentroRegistro.add(textoFechaNacimiento);
		pCentroRegistro.add(lblTelefono);
		pCentroRegistro.add(textoTelefono);
		

		pBotones.add(botonIniSesion);
		pBotones.add(botonRegistrarme);
		pBotones.add(botonRegistro);
		
		pArriba.add(botonAtras1);
		pArriba.add(botonAtras2);
		pCentro.add(lblDNI);
		pCentro.add(textoDNI);
		pCentro.add(lblContra);
		pCentro.add(textoContra);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		

		botonAtras1.addActionListener((e) -> {
			new VentanaSeleccion(datos);
			dispose();
		});
		
		botonAtras2.addActionListener(e -> {
			new VentanaInicioCliente(datos);
			dispose();
		});

		botonRegistrarme.addActionListener((e) -> {
			setSize(600,600);
			botonIniSesion.setVisible(false);
			botonRegistrarme.setVisible(false);
			botonAtras1.setVisible(false);
			botonAtras2.setVisible(true);
			botonRegistro.setVisible(true);
			getContentPane().add(pCentroRegistro,BorderLayout.CENTER);
			pCentro.setVisible(false);
			pCentroRegistro.setVisible(true);
			pack();
		});

		botonRegistro.addActionListener(e -> {
			if(mapaClientesPorDNI.containsKey(textoDNI.getText())){
				JOptionPane.showMessageDialog(null, "El DNI introducido ya esta en uso");
			}else if(mapaClientesPorDNI.containsKey(textoEmail.getText())) {
				JOptionPane.showMessageDialog(null, "El e-mail introducido ya esta en uso");
			}else {
				Cliente cliente = new Cliente();
				cliente.setNombre(textoNombre.getText());
				cliente.setDni(textoDNI2.getText());
				cliente.setApellido1(textoApellido.getText());
				cliente.setEmail(textoEmail.getText());
				cliente.setContraseña(textoContra2.getText());
				cliente.setDireccion(textoDireccion.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(textoFechaNacimiento.getText(), formatter);
				cliente.setfNacimiento(date);
				cliente.setTelefono(textoTelefono.getText());
				datos.getListaClientes().add(cliente);
				datos.getMapaClientesPorDNI().put(cliente.getDni(), cliente);
				mapaClientesPorDNI.put(cliente.getDni(), cliente);
				datos.guardarDatos();
				new VentanaInicioCliente(datos);
				for (String dni : mapaClientesPorDNI.keySet()) {
					System.out.println(mapaClientesPorDNI.get(dni));
				}
				dispose();
			}
		});
		
		botonIniSesion.addActionListener((e) -> {
			String dni = textoDNI.getText();
			if (datos.getMapaClientesPorDNI().containsKey(dni)) {
				if(datos.comprobarContraseña(dni, textoContra.getText())) {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Primero tienes que registrarte");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				datos.cargarDatos();
			}
		
			@Override
			public void windowClosing(WindowEvent e) {
				datos.guardarDatos();
				for (String dni : mapaClientesPorDNI.keySet()) {
					System.out.println(mapaClientesPorDNI.get(dni));
				}
			}
			
			
		});
		setVisible(true);
	}
}
