package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Trabajador;

public class VentanaInicioTrabajador extends JFrame{
	protected JButton botonCerrar, botonRegistro, botonIniSesion;
	protected JLabel lblNombre, lblContra;
	protected JPanel pBotones, pCentro, pArriba;
	protected JTextField textoNombre, textoContra;
	protected Map<String, Trabajador> mapaTrabajadoresPorNombre;

	public VentanaInicioTrabajador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		mapaTrabajadoresPorNombre = new HashMap<String, Trabajador>();
		botonCerrar = new JButton("ATRAS");
		//botonRegistro = new JButton("REGISTRO");
		botonIniSesion = new JButton("INICIO DE SESION");

		lblNombre = new JLabel("Introduce tu nombre: ");
		lblContra = new JLabel("Introduce tu contraseña: ");

		pBotones = new JPanel();
		pCentro = new JPanel();
		pArriba = new JPanel();

		textoNombre = new JTextField(20);
		textoContra = new JTextField(20);

		pBotones.add(botonIniSesion);
		//pBotones.add(botonRegistro);
		pArriba.add(botonCerrar);
		pCentro.add(lblNombre);
		pCentro.add(textoNombre);
		pCentro.add(lblContra);
		pCentro.add(textoContra);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonCerrar.addActionListener((e) -> {
			dispose();
			new VentanaSeleccion();
		});
		botonIniSesion.addActionListener((e) -> {
			String nom = textoNombre.getText();
			String contra = textoContra.getText();
			if (!mapaTrabajadoresPorNombre.containsKey(nom)) {
				JOptionPane.showMessageDialog(null, "No existe el trabajador");
			} else {
				Trabajador t1 = mapaTrabajadoresPorNombre.get(nom);
				if (!t1.getContraseña().equals(contra)) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				} else {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
				}
			}
		}); 
		setVisible(true);
	}

}
