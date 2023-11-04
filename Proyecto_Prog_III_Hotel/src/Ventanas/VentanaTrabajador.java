package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import Clases.Datos;
import Clases.Trabajador;

public class VentanaTrabajador extends JFrame {
	protected JProgressBar tareasHacer;
	protected JTextField textDni, textNombre, textApellido1, textApellido2, textEmail, textDireccion, textfNacimiento,
			textContraseña, textTelefono, textSalario, textHorasTrabajadas;
	protected JLabel lblDatos, lblDni, lblNombre, lblApellido1, lblApellido2, lblEmail, lblDireccion, lblfNacimiento,
    lblContraseña, lblTelefono, lblSalario, lblHorasTrabajadas;
	protected JPanel pDatos, pTareas, pBotones;
	protected JButton botonCerrar, botonTareaHecha;
	protected Trabajador trabajador;
	protected int tareasPendientes = 10;//ejemplo de tareas pendientes que tiene un trabajador

	public VentanaTrabajador(String dni) {
		//trabajador= Datos.getMapaTrabajadoresPorDNI(dni);//esta linea se me esta complicando
		//no entiendo por que pilla error si alguien ve el error porfa decirme que es
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		botonCerrar = new JButton("CERRAR");
		botonTareaHecha = new JButton("TAREA HECHA");

		tareasHacer = new JProgressBar(0,tareasPendientes);
		tareasHacer.setStringPainted(true); 
		tareasHacer.setValue(tareasPendientes);


		pDatos = new JPanel();
		pDatos.setLayout(new GridLayout(12, 2));
		pTareas = new JPanel();
		pBotones = new JPanel();
		
		//Crear los labels
		lblDni = new JLabel("DNI: ");
        lblNombre = new JLabel("Nombre: ");
        lblApellido1 = new JLabel("Primer apellido: ");
        lblApellido2 = new JLabel("Segundo apellido: ");
        lblEmail = new JLabel("Email: ");
        lblDireccion = new JLabel("Dirección: ");
        lblfNacimiento = new JLabel("Fecha de nacimiento: ");
        lblContraseña = new JLabel("Contraseña: ");
        lblTelefono = new JLabel("Teléfono: ");
        lblSalario = new JLabel("Salario: ");
        lblHorasTrabajadas = new JLabel("Horas trabajadas: ");


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
		
		//Colocar la informacion sacada de datos
		 textDni.setText(trabajador.getDni());//toca crear un par de estos para cada textfield
	     textDni.setEditable(false);//cuando arregle el problema con la carga de informacion los coloco
		
		// añadir los labels y textfields al panel
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
        pDatos.add(lblSalario);
        pDatos.add(textSalario);
        pDatos.add(lblHorasTrabajadas);
        pDatos.add(textHorasTrabajadas);
		

		pBotones.add(botonCerrar);

		//pTareas.add(tareasHacer);
		pDatos.add(new JLabel("Tareas pendientes: "));
        pDatos.add(tareasHacer);
        pBotones.add(botonTareaHecha);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pDatos, BorderLayout.CENTER);
		getContentPane().add(pTareas, BorderLayout.WEST);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		botonTareaHecha.addActionListener((e) -> {
            if (tareasPendientes > 0) {
                tareasPendientes--;
                tareasHacer.setValue(tareasPendientes);
                // se puede crear una nueva ventana que muestre un menu donde se puedan selecionar
                //por ahora se queda asi
            }
        });
		setVisible(true);

	}

}
