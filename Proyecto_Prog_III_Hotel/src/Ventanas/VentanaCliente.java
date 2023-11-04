package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Clases.Cliente;
import Clases.Datos;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class VentanaCliente extends JFrame{
	protected JProgressBar reservasHabitaciones;
	protected JTextField textDni,textNombre,textApellido1,textApellido2,
	textEmail,textDireccion,textfNacimiento,textContraseña,textTelefono;
	protected JLabel lblDatoslblDatos, lblDni, lblNombre, lblApellido1, lblApellido2, lblEmail, lblDireccion, lblfNacimiento,
    lblContraseña, lblTelefono;
	protected JPanel pDatos, pBotones, pReservas, pHabitaciones, pFecha;
	protected JButton botonCerrar, botonReserva;
	protected JComboBox comboBoxHoteles;
	protected JSpinner numeroDeHabitaciones;
	
	
	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Hotel");
		
		comboBoxHoteles = new JComboBox<>();
		
		JRadioButton habitacionSimple = new JRadioButton("Habitacion Simple");
		JRadioButton habitacionDoble = new JRadioButton("Habitacion Doble");
		JRadioButton habitacionSuite = new JRadioButton("Habitacion Suite");
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1,1,10,1);
		numeroDeHabitaciones = new JSpinner(spinnerModel);
		
		pHabitaciones = new JPanel();
		pReservas = new JPanel();
		pBotones = new JPanel();
		pFecha = new JPanel();
		pDatos = new JPanel();
		pDatos.setLayout(new GridLayout(12, 2));
		
		
		botonCerrar = new JButton("CERRAR");
		botonReserva = new JButton("RESERVAR");
		
		pBotones.add(botonCerrar);
		pBotones.add(botonReserva);
		pHabitaciones.add(new JLabel("Selecciona el tipo de Habitacion: "));
		pHabitaciones.add(habitacionSimple);
		pHabitaciones.add(habitacionDoble);
		pHabitaciones.add(habitacionSuite);
		pReservas.add(pHabitaciones);
		pReservas.add(new JLabel("Seleccionaa un Hotel: "));
		pReservas.add(comboBoxHoteles);
		pReservas.add(new JLabel("Selecciona e numero de Habitaciones: "));
		pReservas.add(numeroDeHabitaciones);
		
		textDni = new JTextField(10);
		textNombre = new JTextField(10);
		textApellido1 = new JTextField(10);
		textApellido2 = new JTextField(10);
		textEmail = new JTextField(10);
		textDireccion = new JTextField(10);
		textfNacimiento = new JTextField(1);
		textContraseña = new JTextField(10);
		textTelefono = new JTextField(10);
		
		lblDni = new JLabel("DNI: ");
        lblNombre = new JLabel("Nombre: ");
        lblApellido1 = new JLabel("Primer apellido: ");
        lblApellido2 = new JLabel("Segundo apellido: ");
        lblEmail = new JLabel("Email: ");
        lblDireccion = new JLabel("Dirección: ");
        lblfNacimiento = new JLabel("Fecha de nacimiento: ");
        lblContraseña = new JLabel("Contraseña: ");
        lblTelefono = new JLabel("Teléfono: ");
        
        pDatos.add(lblDni);
        pDatos.add(textDni);
        pDatos.add(lblNombre);
        pDatos.add(textNombre);
        pDatos.add(lblApellido1);
        pDatos.add(textApellido1);
        pDatos.add(lblApellido2);
        pDatos.add(textApellido2);
        pDatos.add(lblEmail);
        pDatos.add(textEmail);
        pDatos.add(lblDireccion);
        pDatos.add(textDireccion);
        pDatos.add(lblfNacimiento);
        pDatos.add(textfNacimiento);
        pDatos.add(lblContraseña);
        pDatos.add(textContraseña);
        pDatos.add(lblTelefono);
        pDatos.add(textTelefono);
        
		
		getContentPane().add(pBotones, BorderLayout.NORTH);
		getContentPane().add(pReservas, BorderLayout.WEST);
		getContentPane().add(pDatos, BorderLayout.SOUTH);
		
		
		
		botonReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		
		setVisible(true);
		
	}
	
}
