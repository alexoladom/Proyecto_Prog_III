package gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domain.BDmanager;
import domain.Datos;


public class VentanaInicioTrabajador extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");

	private static final long serialVersionUID = 1L;
	protected JButton botonAtras, botonRegistro, botonIniSesion;
	protected JLabel lblNombre, lblContra, lblTrabajador;
	protected JPanel pBotones, pCentro, pArriba;
	protected JTextField textoNombre;
	protected JPasswordField textoContra;
	protected Datos datos;

	public VentanaInicioTrabajador(Datos datos,String seleccionDatos, BDmanager bdManager) {
		try {
			FileHandler fileTxt = new FileHandler("log/logger.txt");
			SimpleFormatter formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
		} catch (SecurityException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		ImageIcon h = new ImageIcon("src/Imagenes/h.png");
		setIconImage(h.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		this.datos= datos;
		botonAtras = new JButton("ATRAS");
		botonIniSesion = new JButton("INICIO DE SESION");

		lblNombre = new JLabel("Introduce tu DNI: ");
		lblContra = new JLabel("Introduce tu contraseña: ");
		lblTrabajador = new JLabel(new ImageIcon("src\\Imagenes\\Trabajadores.jpeg"));

		pBotones = new JPanel();
		pCentro = new JPanel();
		pArriba = new JPanel();

		textoContra = new JPasswordField(20);
		textoNombre = new JTextField(20);

		pBotones.add(botonIniSesion);
		pArriba.add(botonAtras);
		pCentro.add(lblNombre);
		pCentro.add(textoNombre);
		pCentro.add(lblContra);
		pCentro.add(textoContra);
		pCentro.add(lblTrabajador);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonAtras.addActionListener((e) -> {
			dispose();
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new VentanaSeleccion(datos,seleccionDatos,bdManager);	
					logger.info("Se vuelve a la ventana de selección");
				}
			});
			
		});
		
		botonIniSesion.addActionListener((e) -> {
			String dni = textoNombre.getText();
			if (datos.getMapaTrabajadoresPorDNI().containsKey(dni)) {
				if(datos.comprobarContraseñaTrabajador(dni,String.valueOf(textoContra.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
					logger.info("El trabajador ha iniciado sesión");
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							new VentanaTrabajador(datos, datos.getMapaTrabajadoresPorDNI().get(dni),seleccionDatos,bdManager);
							
						}
					});
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
					logger.warning("La contraseña del trabajador es incorrecta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No existe el trabajador con ese DNI");
				logger.warning("El trabajador introducido no existe");
			}
		}); 
		setLocationRelativeTo(null);
		setVisible(true);
	}

}