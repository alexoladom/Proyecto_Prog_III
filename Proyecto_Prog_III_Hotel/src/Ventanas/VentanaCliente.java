package Ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class VentanaCliente extends JFrame{
	protected JProgressBar reservasHabitaciones;
	protected JTextField textDni,textNombre,textApellido1,textApellido2,
	textEmail,textDireccion,textfNacimiento,textContraseña,textTelefono;
	protected JLabel lblDatos;
	protected JPanel pDatos, pBotones, pReservas;
	protected JButton botonCerrar;
	
	
	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Hotel");
		lblDatos = new JLabel("Cliente");
		

		pReservas = new JPanel();
		pBotones = new JPanel();
		pDatos = new JPanel();
		
		
		botonCerrar = new JButton("CERRAR");
		
		pBotones.add(botonCerrar);
		pDatos.add(lblDatos);
		pReservas.add(reservasHabitaciones);
		
		
		textDni = new JTextField(15);
		textNombre = new JTextField(15);
		textApellido1 = new JTextField(15);
		textApellido2 = new JTextField(15);
		textEmail = new JTextField(15);
		textDireccion = new JTextField(15);
		textfNacimiento = new JTextField(15);
		textContraseña = new JTextField(15);
		textTelefono = new JTextField(15);
		
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		
		setVisible(true);
		
	}
	
}
