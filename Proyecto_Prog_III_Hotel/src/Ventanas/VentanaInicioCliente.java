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

import Clases.Cliente;

public class VentanaInicioCliente {
	protected JButton botonCerrar, botonRegistro, botonIniSesion;
	protected JLabel lblNombre, lblContra;
	protected JPanel pBotones, pCentro, pArriba;
	protected JTextField textoNombre, textoContra;
	protected Map<String, Cliente> mapaClientes = new HashMap();

	public VentanaInicioCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);


		botonCerrar = new JButton("CERRAR");
		botonRegistro = new JButton("REGISTRO");
		botonIniSesion = new JButton("INICIO DE SESION");

		lblNombre = new JLabel("Introduce tu nombre: ");
		lblContra = new JLabel("Introduce tu contraseña: ");

		pBotones = new JPanel();
		pCentro = new JPanel();
		pArriba = new JPanel();

		textoNombre = new JTextField(20);
		textoContra = new JTextField(20);

		pBotones.add(botonIniSesion);
		pBotones.add(botonRegistro);
		pArriba.add(botonCerrar);
		pCentro.add(lblNombre);
		pCentro.add(textoNombre);
		pCentro.add(lblContra);
		pCentro.add(textoContra);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});

		botonRegistro.addActionListener((e) -> {
			String nom = textoNombre.getText();
			String contra = textoContra.getText();
			Cliente c = new Cliente();//Falta añadir los datos de los clientes
			if (mapaClientes.containsKey(nom)) {
				JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe");
			} else {
				mapaClientes.put(nom, c);
			}
		});

		botonIniSesion.addActionListener((e) -> {
			String nom = textoNombre.getText();
			String contra = textoContra.getText();
			if (!mapaClientes.containsKey(nom)) {
				JOptionPane.showMessageDialog(null, "Primero tienes que registrarte");
			} else {
				Cliente cl = mapaClientes.get(nom);
				if (!cl.getContraseña().equals(contra)) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				} else {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
				}
			}
		});
		setVisible(true);
	}

	private void setBounds(int i, int j, int k, int l) {
	}

	private void setDefaultCloseOperation(int exitOnClose) {		
	}

	private Container getContentPane() {
		return null;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VentanaInicioCliente v = new VentanaInicioCliente();
	}
	public static void setVisible(boolean b) {		
	}

}
