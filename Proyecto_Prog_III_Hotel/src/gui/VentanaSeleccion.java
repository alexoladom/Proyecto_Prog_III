package gui;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domain.Datos;

public class VentanaSeleccion extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");

	private static final long serialVersionUID = 1L;
	protected JButton botonCerrar, botonCliente, botonTrabajador;
	protected JPanel pAbajo, pCentro;
	protected JTextField textoIdentificacion;
	protected JLabel lblIdentificacion, lblImagenTrabajador, lblImagenCliente;
	
	public VentanaSeleccion(Datos datos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		botonCerrar = new JButton("CERRAR");
		botonCliente = new JButton("SOY CLIENTE");
		botonTrabajador = new JButton("SOY TRABAJADOR");


		lblIdentificacion = new JLabel("Introduce que eres: ");
		lblImagenTrabajador = new JLabel(new ImageIcon("src\\Imagenes\\imagenTrabajador.jpeg"));
		lblImagenCliente = new JLabel(new ImageIcon("src\\Imagenes\\ImagenCliente.jpeg"));

		pAbajo = new JPanel();
		pCentro = new JPanel();

		textoIdentificacion = new JTextField(20);

		pCentro.add(lblIdentificacion);
		pCentro.add(botonTrabajador);
		pCentro.add(lblImagenTrabajador);
		pCentro.add(botonCliente);
		pCentro.add(lblImagenCliente);
		pAbajo.add(botonCerrar);

		getContentPane().add(pAbajo, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
			logger.info("Se ha cerrado la ventana");
		});

		botonTrabajador.addActionListener((e) -> {
			SwingUtilities.invokeLater(new Runnable() {
			    @Override
				public void run() {
					new VentanaInicioTrabajador(datos);	
					logger.info("Se ha abierto la ventana de inicio del trabajador");
				}
			});	
            dispose();
		});

		botonCliente.addActionListener((e) -> {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new VentanaInicioCliente(datos);
					logger.info("Se ha abierto la ventana de inicio del cliente");
				}
			});
			
            dispose();
		});
		pack();
		setVisible(true);
	}
}

