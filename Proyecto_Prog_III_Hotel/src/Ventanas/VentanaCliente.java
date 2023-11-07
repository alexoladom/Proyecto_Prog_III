package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import Clases.Cliente;
import Clases.Datos;
import Clases.Reserva;

public class VentanaCliente extends JFrame{

	private static final long serialVersionUID = 1L;
	protected JProgressBar reservasHabitaciones;
	protected JTextField textDni,textNombre,textApellido1,textApellido2,
	textEmail,textDireccion,textfNacimiento,textContraseña,textTelefono;
	protected JLabel lblDatoslblDatos, lblDni, lblNombre, lblApellido1, lblApellido2, lblEmail, lblDireccion, lblfNacimiento,
    lblContraseña, lblTelefono;
	protected JPanel pDatos, pBotones, pReservas, pHabitaciones, pFecha;
	protected JPanel pListaReservas, pCrearEditarReserva,pInformacion,pCambiarPerfil,pBotonesVerReservas;
	protected JList<Reserva> listaReservas;
	protected JButton botonCerrar, botonReserva,bBorrarReserva,bEditarReserva;
	protected JComboBox comboBoxHoteles;
	protected JSpinner numeroDeHabitaciones;
	
	
	public VentanaCliente(Datos datos, Cliente cliente) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,350);
		setLocationRelativeTo(null);
		setTitle("Cliente "+ cliente.getNombre()+" "+cliente.getApellido1());
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuCrearReservas = new JMenu();
		menuBar.add(menuCrearReservas);
		JMenuItem crearReservas = new JMenuItem("CREAR RESERVA");
		menuCrearReservas.add(crearReservas);
		JMenu menuVerReservas = new JMenu();
		menuBar.add(menuVerReservas);
		JMenuItem verReservas = new JMenuItem("VER RESERVAS");
		menuVerReservas.add(verReservas);
		JMenu menuPerfil = new JMenu();
		menuBar.add(menuPerfil);
		JMenuItem cambiarFotoPerfil = new JMenuItem("CAMBIAR FOTO DE PERFIL");
		menuPerfil.add(cambiarFotoPerfil);
		JMenuItem informacionCliente = new JMenuItem("VER/EDITAR MIS DATOS");
		menuPerfil.add(informacionCliente);
		JMenuItem cerrarSesion = new JMenuItem("CERRAR SESION");
		menuPerfil.add(cerrarSesion);
		
		
	
		ImageIcon imagenReserva = new ImageIcon("src/Imagenes/button_reserva-ahora.png");
		Image imagenReservaEscala = imagenReserva.getImage().getScaledInstance(100, 45,Image.SCALE_SMOOTH);
		menuCrearReservas.setIcon(new ImageIcon(imagenReservaEscala));
		
		ImageIcon imagenVerReserva = new ImageIcon("src/Imagenes/button_ver-reservas.png");
		Image imagenVerReservaEscala = imagenVerReserva.getImage().getScaledInstance(100, 45,Image.SCALE_SMOOTH);
		menuVerReservas.setIcon(new ImageIcon(imagenVerReservaEscala));
		
		ImageIcon imagenPerfil = new ImageIcon("src/Imagenes/imagenPerfilpng.png");
		Image imagenPerfilEscala = imagenPerfil.getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH);
		menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));
		
		//Panel para ver todas las reservas, borrarlas y editarlas
		pListaReservas = new JPanel();
		pListaReservas.setBackground(Color.LIGHT_GRAY);
		DefaultListModel<Reserva> modeloListaReservas = new DefaultListModel<Reserva> ();
		modeloListaReservas.addAll(cliente.getListaReservasCliente());
		listaReservas = new JList<Reserva>(modeloListaReservas);
		
		JScrollPane scrollListaReservas = new JScrollPane(listaReservas);
		scrollListaReservas.setSize(400, 400);
		pListaReservas.add(scrollListaReservas);
		
		pBotonesVerReservas = new JPanel();
		pBotonesVerReservas.setBackground(Color.LIGHT_GRAY);
		bBorrarReserva= new JButton("Borrar reserva");
		bEditarReserva= new JButton("Editar reserva");
		
		pBotonesVerReservas.add(bBorrarReserva);
		pBotonesVerReservas.add(bEditarReserva);
		
		
		bBorrarReserva.addActionListener((e)-> {
			Reserva seleccionado =listaReservas.getSelectedValue();
			cliente.getListaReservasCliente().remove(seleccionado);
			modeloListaReservas.removeElement(seleccionado);
			listaReservas.repaint();
		});
		
		
		pListaReservas.add(pBotonesVerReservas, BorderLayout.SOUTH);
		
		add(pListaReservas);
		pListaReservas.setVisible(false);
		
		verReservas.addActionListener((e)->{
			pCrearEditarReserva.setVisible(false);
			pListaReservas.setVisible(true);
		});
		
		
		//Panel para crear nuevas reservas
		
		class PanelCrearReserva extends JPanel{
			
			private static final long serialVersionUID = 1L;
			protected JPanel pAbajo, pPrincipal;
			protected JLabel lblFechaIni, lblFechaFin, lblHabSimples, lblHabDobles, lblHabSuites, lblParking;
			protected JDatePicker datePickerIni, datePickerFin;
			protected JSpinner spinHabSimple, spinHabDoble, spinHabSuite;
			protected JButton bReservarParking, bCancelarReserva, bConfirmarReserva;
			
			
			public PanelCrearReserva() {
				setVisible(false);
				setLayout(new BorderLayout());
				lblFechaIni = new JLabel("Fecha inicial: ");
				lblFechaFin = new JLabel("Fecha final: ");
				lblHabSimples = new JLabel("Habitaciones Simples: ");
				lblHabDobles = new JLabel("Habitaciones dobles: ");
				lblHabSuites = new JLabel("Suites: ");
				lblParking = new JLabel("Reservar plazas de parking -> ");
				
				datePickerIni = new JDatePicker();
				
				//Configuracion del datepicker inicio
				datePickerIni.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					GregorianCalendar calendar = (GregorianCalendar) datePickerIni.getModel().getValue();
					ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
			        LocalDate fechaLocal = zonedDateTime.toLocalDate();
			        GregorianCalendar calendar2 = (GregorianCalendar) datePickerFin.getModel().getValue();
			        if (calendar2!=null) {
			        	ZonedDateTime zonedDateTime2 = calendar2.toZonedDateTime();
				        LocalDate fechaLocal2 = zonedDateTime2.toLocalDate();
				        if (fechaLocal.isBefore(LocalDate.now())|| fechaLocal.isAfter(fechaLocal2)) {
							datePickerIni.getModel().setValue(null);
						}
			        }else {
			        	if (fechaLocal.isBefore(LocalDate.now())) {
							datePickerIni.getModel().setValue(null);
						}
			        }
					
				}
			});
				
				datePickerIni.addDateSelectionConstraint(new DateSelectionConstraint() {
					
					@Override
					public boolean isValidSelection(DateModel<?> arg0) {
						
						GregorianCalendar calendar = (GregorianCalendar) arg0.getValue();
				        if(calendar!=null) {
				        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
					        LocalDate fechaLocal = zonedDateTime.toLocalDate();
					        GregorianCalendar calendar2 = (GregorianCalendar) datePickerFin.getModel().getValue();
					        if (calendar2!=null) {
					        	ZonedDateTime zonedDateTime2 = calendar2.toZonedDateTime();
						        LocalDate fechaLocal2 = zonedDateTime2.toLocalDate();
						        if (fechaLocal.isBefore(LocalDate.now())|| fechaLocal.isAfter(fechaLocal2)) {
									return false;
								}else {
									return true;
								}
					        }else{
					        	if(fechaLocal.isBefore(LocalDate.now())) {
						        	 return false;
						         }else {
						        	 return true;
						         }
					        }
				        }else{
				        	return true;
				        }		         
					}
				});

				
				datePickerFin = new JDatePicker();
				
				//Configuracion del datepicker final
				
				datePickerFin.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					GregorianCalendar calendar = (GregorianCalendar) datePickerFin.getModel().getValue();
					ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
			        LocalDate fechaLocal = zonedDateTime.toLocalDate();
			        GregorianCalendar calendar2 = (GregorianCalendar) datePickerIni.getModel().getValue();
			        if (calendar2!=null) {
			        	ZonedDateTime zonedDateTime2 = calendar2.toZonedDateTime();
				        LocalDate fechaLocal2 = zonedDateTime2.toLocalDate();
				        if (fechaLocal.isBefore(LocalDate.now())|| fechaLocal.isBefore(fechaLocal2)) {
							datePickerFin.getModel().setValue(null);
						}
			        }else {
			        	if (fechaLocal.isBefore(LocalDate.now())) {
							datePickerFin.getModel().setValue(null);
						}
			        }
					
				}
			});

				datePickerFin.addDateSelectionConstraint(new DateSelectionConstraint() {
					
					@Override
					public boolean isValidSelection(DateModel<?> arg0) {
						
						GregorianCalendar calendar = (GregorianCalendar) arg0.getValue();
				        if(calendar!=null) {
				        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
					        LocalDate fechaLocal = zonedDateTime.toLocalDate();
					        GregorianCalendar calendar2 = (GregorianCalendar) datePickerIni.getModel().getValue();
					        if (calendar2!=null) {
					        	ZonedDateTime zonedDateTime2 = calendar2.toZonedDateTime();
						        LocalDate fechaLocal2 = zonedDateTime2.toLocalDate();
						        if (fechaLocal.isBefore(LocalDate.now())|| fechaLocal.isBefore(fechaLocal2)) {
									return false;
								}else {
									return true;
								}
					        }else{
					        	if(fechaLocal.isBefore(LocalDate.now())) {
						        	 return false;
						         }else {
						        	 return true;
						         }
					        }
				        }else{
				        	return true;
				        }		         
					}
				});

				
				SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0,0,datos.getListaHabitaciones().size(),1);
				spinHabSimple = new JSpinner(modeloSpinner);
				spinHabDoble = new JSpinner(modeloSpinner);
				spinHabSuite = new JSpinner(modeloSpinner);
				
				bReservarParking = new JButton("Reserva Parking");
				bCancelarReserva = new JButton("Cancelar Reserva");
				bConfirmarReserva = new JButton ("Confirmar Reserva");
				
				pPrincipal = new JPanel(new GridLayout(6,2));
				pPrincipal.add(lblFechaIni);
				pPrincipal.add(datePickerIni);
				pPrincipal.add(lblFechaFin);
				pPrincipal.add(datePickerFin);
				pPrincipal.add(lblHabSimples);
				pPrincipal.add(spinHabSimple);
				pPrincipal.add(lblHabDobles);
				pPrincipal.add(spinHabDoble);
				pPrincipal.add(lblHabSuites);
				pPrincipal.add(spinHabSuite);
				pPrincipal.add(lblParking);
				pPrincipal.add(bReservarParking);
				
				pAbajo = new JPanel();
				pAbajo.add(bCancelarReserva,bConfirmarReserva);
				
				add(pPrincipal, BorderLayout.NORTH);
				add(pAbajo, BorderLayout.SOUTH);
			}
			
		}
		
		pCrearEditarReserva = new PanelCrearReserva();
		add(pCrearEditarReserva,BorderLayout.NORTH);
		
		crearReservas.addActionListener((e)->{
			pListaReservas.setVisible(false);
			pCrearEditarReserva.setVisible(true);
		});
		

		
		/*
		comboBoxHoteles = new JComboBox<>();
		
		JRadioButton habitacionSimple = new JRadioButton("Habitacion Simple");
		JRadioButton habitacionDoble = new JRadioButton("Habitacion Doble");
		JRadioButton habitacionSuite = new JRadioButton("Habitacion Suite");
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1,1,10,1);
		numeroDeHabitaciones = new JSpinner(spinnerModel);
		
		pHabitaciones = new JPanel();
		pReservas = new JPanel();
		pBotones = new JPanel();
		pFecha = new JPanel();
		pDatos = new JPanel();
		pDatos.setLayout(new GridLayout(12, 2));
		
		
		botonCerrar = new JButton("CERRAR");
		botonReserva = new JButton("RESERVAR");
		
		pBotones.add(botonCerrar);
		pBotones.add(botonReserva);
		pHabitaciones.add(new JLabel("Selecciona el tipo de Habitacion: "));
		pHabitaciones.add(habitacionSimple);
		pHabitaciones.add(habitacionDoble);
		pHabitaciones.add(habitacionSuite);
		pReservas.add(pHabitaciones);
		pReservas.add(new JLabel("Seleccionaa un Hotel: "));
		pReservas.add(comboBoxHoteles);
		pReservas.add(new JLabel("Selecciona e numero de Habitaciones: "));
		pReservas.add(numeroDeHabitaciones);
		
		textDni = new JTextField(10);
		textNombre = new JTextField(10);
		textApellido1 = new JTextField(10);
		textApellido2 = new JTextField(10);
		textEmail = new JTextField(10);
		textDireccion = new JTextField(10);
		textfNacimiento = new JTextField(1);
		textContraseña = new JTextField(10);
		textTelefono = new JTextField(10);
		
		lblDni = new JLabel("DNI: ");
        lblNombre = new JLabel("Nombre: ");
        lblApellido1 = new JLabel("Primer apellido: ");
        lblApellido2 = new JLabel("Segundo apellido: ");
        lblEmail = new JLabel("Email: ");
        lblDireccion = new JLabel("Dirección: ");
        lblfNacimiento = new JLabel("Fecha de nacimiento: ");
        lblContraseña = new JLabel("Contraseña: ");
        lblTelefono = new JLabel("Teléfono: ");
        
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
        
		
		getContentPane().add(pBotones, BorderLayout.NORTH);
		getContentPane().add(pReservas, BorderLayout.WEST);
		getContentPane().add(pDatos, BorderLayout.SOUTH);
		
		
		
		botonReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		
		
		*/
		setVisible(true);
	}
	public static void main(String[] args) {
		Datos datos = new Datos();
		datos.inicializarDatos();
		Cliente c = new Cliente();
		c.getListaReservasCliente().add(new Reserva());
		c.setNombre("Alex");
		c.setApellido1("Olazabal");
		new VentanaCliente(datos,c);
	}
	
}
