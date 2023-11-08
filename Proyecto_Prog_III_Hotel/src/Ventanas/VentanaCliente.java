package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;


import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import Clases.Cliente;
import Clases.ComparadorReservasPorFecha;
import Clases.ComparadorReservasPorPagado;
import Clases.ComparadorReservasPorPrecio;
import Clases.Datos;
import Clases.Reserva;

public class VentanaCliente extends JFrame{

	private static final long serialVersionUID = 1L;
	protected JPanel pListaReservas, pCrearEditarReserva,pInformacion,pPerfil,pCambiarPerfil,pBotonesVerReservas;
	protected JList<Reserva> listaReservas;
	protected JButton botonCerrar, botonReserva,bBorrarReserva,bEditarReserva,bInvertirLista;
	protected JComboBox<String>  comboOrdenar;
	protected JSpinner numeroDeHabitaciones;
	
	
	

	public VentanaCliente(Datos datos, Cliente cliente) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700,300);
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
		
		
		
		//Panel para ver y editar los datos del cliente
		
		pPerfil= new JPanel();
		pPerfil.setVisible(true);
		pInformacion = new JPanel();
		
		pInformacion.setLayout(new GridLayout(8,2));
		
		JLabel lblNombre = new JLabel("Introduzca su Nombre: ");
		JLabel lblContra = new JLabel("Introduzca su contraseña: ");
		JLabel lblDNI = new JLabel("Introduzca su DNI: ");
		JLabel lblApellido = new JLabel("Introdzca su apellido: ");
		JLabel lblEmail = new JLabel("Introduzca su e-mail: ");
		JLabel lblDireccion = new JLabel("Introduzca su direccion: ");
		JLabel lblFechaNacimiento = new JLabel("Introduzca su fecha de nacimiento: ");
		JLabel lblTelefono = new JLabel("Introduzca su teléfono: ");
		
		JTextField textoNombre = new JTextField(20);
		JTextField textoContra = new JPasswordField(20);
		JTextField textoDNI = new JTextField(20);
		JTextField textoApellido = new JTextField(20);
		JTextField textoEmail = new JTextField(20);
		JTextField textoDireccion = new JTextField(20);
		JDatePicker date = new JDatePicker();
		JTextField textoTelefono= new JTextField(20);
		
		pInformacion.add(lblNombre);
		pInformacion.add(textoNombre);
		pInformacion.add(lblApellido);
		pInformacion.add(textoApellido);
		pInformacion.add(lblContra);
		pInformacion.add(textoContra);
		pInformacion.add(lblDNI);
		pInformacion.add(textoDNI);
		pInformacion.add(lblEmail);
		pInformacion.add(textoEmail);
		pInformacion.add(lblDireccion);
		pInformacion.add(textoDireccion);
		pInformacion.add(lblFechaNacimiento);
		pInformacion.add(date);
		pInformacion.add(lblTelefono);
		pInformacion.add(textoTelefono);
		
		pPerfil.add(pInformacion);
		
		
		//Panel para ver todas las reservas, borrarlas y editarlas
		pListaReservas = new JPanel();
		pListaReservas.setBackground(new Color(0,140,255));
		
		
		DefaultListModel<Reserva> modeloListaReservas = new DefaultListModel<Reserva> ();
		modeloListaReservas.addAll(cliente.getListaReservasCliente());
		
		
		listaReservas = new JList<Reserva>(modeloListaReservas);
		listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		boolean run = true;
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				while(run) {
					for (int i = 0; i < 255; i++) {
						listaReservas.setSelectionBackground(new Color(0,i,255));
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for (int i = 0; i < 255; i++) {
						listaReservas.setSelectionBackground(new Color(0,255-i,255));
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		});
		
		hilo.start();
		
		JScrollPane scrollListaReservas = new JScrollPane(listaReservas);
		scrollListaReservas.setSize(400, 400);
		pListaReservas.add(scrollListaReservas);
		
		pBotonesVerReservas = new JPanel();
		pBotonesVerReservas.setBackground(new Color(0,140,255));
		
		bBorrarReserva= new JButton();
		bBorrarReserva.setBackground(Color.white);
		bBorrarReserva.setBorder(null);
		bBorrarReserva.setOpaque(false);
		ImageIcon basura = new ImageIcon("src/Imagenes/basura.png");
		Image basuraResized = basura.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bBorrarReserva.setIcon(new ImageIcon(basuraResized));
		
		
		bEditarReserva= new JButton();
		bEditarReserva.setBackground(Color.white);
		bEditarReserva.setBorder(null);
		bEditarReserva.setOpaque(false);
		ImageIcon editar = new ImageIcon("src/Imagenes/editar.png");
		Image editarResized = editar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bEditarReserva.setIcon(new ImageIcon(editarResized));
		
		
		bInvertirLista = new JButton();
		bInvertirLista.setBackground(Color.white);
		bInvertirLista.setBorder(null);
		bInvertirLista.setOpaque(false);
		ImageIcon flecha = new ImageIcon("src/Imagenes/flechaHaciaArriba.png");
		Image flechaResized = flecha.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bInvertirLista.setIcon(new ImageIcon(flechaResized));
		
		
		comboOrdenar = new JComboBox<String>();
		comboOrdenar.addItem("Fecha inicial");
		comboOrdenar.addItem("Precio");
		comboOrdenar.addItem("Pagado");
		comboOrdenar.setToolTipText("Seleccione el critecio de ordenacion");
		
		comboOrdenar.addActionListener((e) -> {
			String selec = (String) comboOrdenar.getSelectedItem();
			
			if(selec=="Fecha inicial") {
				cliente.getListaReservasCliente().sort(new ComparadorReservasPorFecha());
				modeloListaReservas.removeAllElements();
				modeloListaReservas.addAll(cliente.getListaReservasCliente());
			}else if(selec =="Precio") {
				cliente.getListaReservasCliente().sort(new ComparadorReservasPorPrecio());
				modeloListaReservas.removeAllElements();
				modeloListaReservas.addAll(cliente.getListaReservasCliente());
			}else if(selec=="Pagado") {
				cliente.getListaReservasCliente().sort(new ComparadorReservasPorPagado());
				modeloListaReservas.removeAllElements();
				modeloListaReservas.addAll(cliente.getListaReservasCliente());
			}
		});
		
		pBotonesVerReservas.add(bBorrarReserva);
		pBotonesVerReservas.add(bEditarReserva);
		pBotonesVerReservas.add(new JLabel("Ordenar por:"));
		pBotonesVerReservas.add(comboOrdenar);
		pBotonesVerReservas.add(bInvertirLista);
		
		
		bInvertirLista.addActionListener((e)->{
			Collections.reverse(cliente.getListaReservasCliente());
			modeloListaReservas.removeAllElements();
			modeloListaReservas.addAll(cliente.getListaReservasCliente());
			
		});
		
		bBorrarReserva.addActionListener((e)-> {
			Reserva seleccionado =listaReservas.getSelectedValue();
			cliente.getListaReservasCliente().remove(seleccionado);
			modeloListaReservas.removeElement(seleccionado);
			listaReservas.repaint();
		});
		
		
		pListaReservas.add(pBotonesVerReservas, BorderLayout.SOUTH);
		
		
		pListaReservas.setVisible(false);
		
		
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
				lblFechaIni = new JLabel("Fecha inicial: ",SwingConstants.CENTER);
				lblFechaFin = new JLabel("Fecha final: ",SwingConstants.CENTER);
				lblHabSimples = new JLabel("Habitaciones Simples: ",SwingConstants.CENTER);
				lblHabDobles = new JLabel("Habitaciones dobles: ",SwingConstants.CENTER);
				lblHabSuites = new JLabel("Suites: ",SwingConstants.CENTER);
				lblParking = new JLabel("Reservar plazas de parking -> ",SwingConstants.CENTER);
				
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
		
		
		verReservas.addActionListener((e)->{
			pCrearEditarReserva.setVisible(false);
			pListaReservas.setVisible(true);
			pPerfil.setVisible(false);
			setSize(900,350);
			setLocationRelativeTo(null);
		});
		
		cerrarSesion.addActionListener((e)->{
			datos.guardarDatos();
			dispose();
			new VentanaInicioCliente(datos);
		});
		
		informacionCliente.addActionListener((e)->{
			pCrearEditarReserva.setVisible(false);
			pListaReservas.setVisible(false);
			pPerfil.setVisible(true);
			setSize(700,300);
		});
		crearReservas.addActionListener((e)->{
			pListaReservas.setVisible(false);
			pCrearEditarReserva.setVisible(true);
			pPerfil.setVisible(false);
			setSize(700,300);
			setLocationRelativeTo(null);
		});
		add(pPerfil,BorderLayout.NORTH);
		add(pCrearEditarReserva,BorderLayout.SOUTH);
		add(pListaReservas, BorderLayout.CENTER);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		Datos datos = new Datos();
		datos.inicializarDatos();
		Cliente c = new Cliente();
		Reserva r = new Reserva();
		r.setPrecioCobrar(34);
		r.setFechaInicio(LocalDate.now());
		Reserva g = new Reserva();
		g.setFechaInicio(LocalDate.now().plusDays(1));
		Reserva h = new Reserva();
		h.setEstaPagado(true);
		h.setPrecioCobrar(245);
		h.setFechaInicio(LocalDate.now().plusDays(4));
		Reserva j = new Reserva();
		j.setFechaInicio(LocalDate.now().plusDays(3));
		c.getListaReservasCliente().add(r);
		c.getListaReservasCliente().add(h);
		c.getListaReservasCliente().add(j);
		c.getListaReservasCliente().add(g);
		c.getListaReservasCliente().add(j);
		c.setNombre("Alex");
		c.setApellido1("Olazabal");
		new VentanaCliente(datos,c);
	}
	
}
