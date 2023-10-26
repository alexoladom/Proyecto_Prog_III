package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaSeleccion extends JFrame{
	protected JButton botonCerrar, botonCliente, botonTrabajador;
	protected JPanel pAbajo, pCentro;
	protected JTextField textoIdentificacion;
	protected JLabel lblIdentificacion;
	
	public VentanaSeleccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ns porque pero me ha mandado añadir varios metodos que estan abajo
		setBounds(100, 100, 450, 300);

		botonCerrar = new JButton("CERRAR");
		botonCliente = new JButton("SOY CLIENTE");
		botonTrabajador = new JButton("SOY TRABAJADOR");

		lblIdentificacion = new JLabel("Introduce quien eres: ");

		pAbajo = new JPanel();
		pCentro = new JPanel();

		textoIdentificacion = new JTextField(20);

		pCentro.add(lblIdentificacion);
		pCentro.add(botonTrabajador);
		pCentro.add(botonCliente);
		pAbajo.add(botonCerrar);

		getContentPane().add(pAbajo, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});

		botonTrabajador.addActionListener((e) -> {
			@SuppressWarnings("unused")
			VentanaInicioTrabajador ventanaInicioTrabajador = new VentanaInicioTrabajador();
            dispose();
		});

		botonCliente.addActionListener((e) -> {
			@SuppressWarnings("unused")
			VentanaInicioCliente ventanaInicioCliente = new VentanaInicioCliente();
            dispose();
		});
		setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VentanaSeleccion v = new VentanaSeleccion();
	}
}
