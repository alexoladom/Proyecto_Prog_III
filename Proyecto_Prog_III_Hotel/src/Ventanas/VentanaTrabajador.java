package Ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VentanaTrabajador extends JFrame{
	protected JProgressBar tareasHacer;
	protected JTextField textDni,textNombre,textApellido1,textApellido2,
	textEmail,textDireccion,textfNacimiento,textContraseña,textTelefono,textSalario,textHorasTrabajadas;
	protected JLabel lblDatos;
	protected JPanel pDatos, pTareas, pBotones;
	protected JButton botonCerrar;
	
	public VentanaTrabajador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		botonCerrar = new JButton("CERRAR");
		
		tareasHacer=new JProgressBar();
		
		lblDatos=new JLabel("Trabajador");//Con el format se podria poner el nombre del trabajador
		pDatos=new JPanel();
		pTareas=new JPanel();
		pBotones=new JPanel();
		
		
		//crear los textfields
		
		
		
		pBotones.add(botonCerrar);
		pDatos.add(lblDatos);
		pTareas.add(tareasHacer);
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
	});
		setVisible(true);//al arrancar esta pestaña tiene que estar oculta no?
		
	}

}
