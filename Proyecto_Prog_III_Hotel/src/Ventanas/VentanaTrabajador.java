package Ventanas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VentanaTrabajador extends JFrame {
	protected JProgressBar tareasHacer;
	protected JTextField textDni, textNombre, textApellido1, textApellido2, textEmail, textDireccion, textfNacimiento,
			textContraseña, textTelefono, textSalario, textHorasTrabajadas;
	protected JLabel lblDatos;
	protected JPanel pDatos, pTareas, pBotones;
	protected JButton botonCerrar;

	public VentanaTrabajador() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		botonCerrar = new JButton("CERRAR");

		tareasHacer = new JProgressBar();

		lblDatos = new JLabel("Trabajador");// Con el format se podria poner el nombre del trabajador
		pDatos = new JPanel();
		pTareas = new JPanel();
		pBotones = new JPanel();

		// crear los textfields
		textDni = new JTextField(15);
		textNombre = new JTextField(15);
		textApellido1 = new JTextField(15);
		textApellido2 = new JTextField(15);
		textEmail = new JTextField(15);
		textDireccion = new JTextField(15);
		textfNacimiento = new JTextField(15);
		textContraseña = new JTextField(15);
		textTelefono = new JTextField(15);
		textSalario = new JTextField(15);
		textHorasTrabajadas = new JTextField(15);
		
		lblDatos.add(textDni);
		lblDatos.add(textNombre);
		lblDatos.add(textApellido1);
		lblDatos.add(textApellido2);
		lblDatos.add(textEmail);
		lblDatos.add(textDireccion);
		lblDatos.add(textfNacimiento);
		lblDatos.add(textContraseña);
		lblDatos.add(textTelefono);
		lblDatos.add(textSalario);
		lblDatos.add(textHorasTrabajadas);
		

		pBotones.add(botonCerrar);
		pDatos.add(lblDatos);
		pTareas.add(tareasHacer);

		getContentPane().add(pBotones, BorderLayout.NORTH);
		getContentPane().add(pDatos, BorderLayout.SOUTH);
		getContentPane().add(pTareas, BorderLayout.WEST);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		setVisible(true);

	}

}
