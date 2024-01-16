package gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.jdatepicker.JDatePicker;

import domain.BDexception;
import domain.BDmanager;
import domain.Cliente;
import domain.Datos;

public class VentanaInicioCliente extends JFrame {
	private Logger logger = java.util.logging.Logger.getLogger("Logger");

	private static final long serialVersionUID = 1L;
	protected JButton botonAtras1, botonRegistrarme, botonRegistro,  botonIniSesion,botonAtras2;
	protected JLabel lblNombre, lblContra,lblContra2, lblDNI,lblDNI2,lblApellido,lblEmail,lblDireccion,lblFechaNacimiento,lblTelefono, lblCliente;
	protected JPanel pBotones, pCentro, pArriba, pCentroRegistro;
	protected JPasswordField textoContra, textoContra2;
	protected JTextField textoNombre,textoDNI,textoDNI2,textoApellido,textoEmail,textoDireccion,textoFechaNacimiento,textoTelefono;
	protected Map<String, Cliente> mapaClientesPorDNI;
	protected Datos datos;

	public VentanaInicioCliente(Datos datos, String seleccionDatos, BDmanager bdManager) {
		ImageIcon h = new ImageIcon("src/Imagenes/h.png");
		setIconImage(h.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		
		this.datos = datos;
		botonAtras1 = new JButton("ATRAS");
		botonAtras2 = new JButton("ATRAS");
		botonAtras2.setVisible(false);
		botonRegistrarme = new JButton("REGISTRARME");
		botonRegistro = new JButton("REGISTRO");
		botonRegistro.setVisible(false);
		botonIniSesion = new JButton("INICIO DE SESION");

		lblNombre = new JLabel("Introduzca su Nombre: ",SwingConstants.CENTER);
		lblContra = new JLabel("Introduzca su contraseña: ",SwingConstants.CENTER);
		lblContra2 = new JLabel("Introduzca su contraseña: ",SwingConstants.CENTER);
		lblDNI = new JLabel("Introduzca su DNI: ",SwingConstants.CENTER);
		lblDNI2 = new JLabel("Introduzca su DNI",SwingConstants.CENTER);
		lblApellido = new JLabel("Introdzca su apellido: ",SwingConstants.CENTER);
		lblEmail = new JLabel("Introduzca su e-mail: ",SwingConstants.CENTER);
		lblDireccion = new JLabel("Introduzca su direccion: ",SwingConstants.CENTER);
		lblFechaNacimiento = new JLabel("Introduzca su fecha de nacimiento(dd/mm/aaaa): ",SwingConstants.CENTER);
		lblTelefono = new JLabel("Introduzca su teléfono: ",SwingConstants.CENTER);
		lblCliente = new JLabel(new ImageIcon("src\\Imagenes\\Clientes.jpeg"));

		

		pBotones = new JPanel();
		pCentro = new JPanel();
		pCentroRegistro = new JPanel();
		pCentroRegistro.setVisible(false);
		pArriba = new JPanel();

		textoNombre = new JTextField(20);
		textoContra = new JPasswordField(20);
		textoContra2 = new JPasswordField(20);
		textoDNI = new JTextField(20);
		textoDNI2 = new JTextField(20);
		textoApellido = new JTextField(20);
		textoEmail = new JTextField(20);
		textoDireccion = new JTextField(20);
		JDatePicker date = new JDatePicker();
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
		pCentroRegistro.add(date);
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
		pCentro.add(lblCliente);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		

		botonAtras1.addActionListener((e) -> {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new VentanaSeleccion(datos,seleccionDatos,bdManager);	
					logger.info("Se vuelve a la anterior ventana");
				}
			});
			
			dispose();
		});
		
		botonAtras2.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new VentanaInicioCliente(datos,seleccionDatos, bdManager);	
					logger.info("Se vuelve a la anterior ventana");
				}
			});
			
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
			if(datos.getMapaClientesPorDNI().containsKey(textoDNI2.getText())){
				JOptionPane.showMessageDialog(null, "El DNI introducido ya esta en uso","Advertencia",JOptionPane.WARNING_MESSAGE);
			}else if(datos.getMapaClientesPorDNI().containsKey(textoEmail.getText())) {
				JOptionPane.showMessageDialog(null, "El e-mail introducido ya esta en uso","Advertencia",JOptionPane.WARNING_MESSAGE);
			}else if(date.getModel().getValue()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca una fecha de nacimiento","Advertencia",JOptionPane.WARNING_MESSAGE);
			}else if(textoNombre.getText().length()== 0|| textoApellido.getText().length()== 0||textoDireccion.getText().length()== 0||textoDNI2.getText().length()== 0
					||textoEmail.getText().length()== 0||textoTelefono.getText().length()== 0){
				JOptionPane.showMessageDialog(null, "Rellene todos los campos","Advertencia",JOptionPane.WARNING_MESSAGE);
			}else {
				Cliente cliente = new Cliente();
				cliente.setNombre(textoNombre.getText());
				cliente.setDni(textoDNI2.getText());
				cliente.setApellido1(textoApellido.getText());
				cliente.setEmail(textoEmail.getText());
				cliente.setContraseña(String.valueOf(textoContra2.getPassword()));
				cliente.setDireccion(textoDireccion.getText());
				
				GregorianCalendar calendar = (GregorianCalendar) date.getModel().getValue();
				ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
		        LocalDate fechaLocal = zonedDateTime.toLocalDate();
		        
				cliente.setfNacimiento(fechaLocal);
				cliente.setTelefono(textoTelefono.getText());
				datos.getListaClientes().add(cliente);
				datos.getMapaClientesPorDNI().put(cliente.getDni(), cliente);
				if(seleccionDatos=="Base de datos") {
					try {
						bdManager.guardarCliente(cliente);
					} catch (BDexception e1) {
						System.err.println("Error guardando el cliente en la bd");
						e1.printStackTrace();
					}
				}
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new VentanaInicioCliente(datos,seleccionDatos,bdManager);	
					}
				});
				
				dispose();
			}
		});
		
		botonIniSesion.addActionListener((e) -> {
			String dni = textoDNI.getText();
			if (datos.getMapaClientesPorDNI().containsKey(dni)) {
				if(datos.comprobarContraseñaCliente(dni,String.valueOf(textoContra.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
					logger.info("Un usuario inicia sesión");
					new VentanaCliente(datos, datos.getMapaClientesPorDNI().get(dni),seleccionDatos, bdManager);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
					logger.warning("El usuario ha aplicado una contraseña incorrecta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Primero tienes que registrarte");
				logger.warning("El usuario no esta registrado");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				if(seleccionDatos=="Fichero de datos") {
					datos.guardarDatos();
					logger.info("Se guardan los datos en el fichero");
				}else if(seleccionDatos=="Base de datos") {
					try {
						bdManager.disconnect();
					} catch (BDexception e1) {
						System.err.println("Error desconectando la BD");
						e1.printStackTrace();
					}
			    }
				
			}
			
			
		});
		setVisible(true);
	}
}