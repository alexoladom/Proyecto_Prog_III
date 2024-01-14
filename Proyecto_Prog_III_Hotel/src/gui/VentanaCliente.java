package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import domain.BDexception;
import domain.BDmanager;
import domain.Cliente;
import domain.ComparadorReservasPorFecha;
import domain.ComparadorReservasPorPagado;
import domain.ComparadorReservasPorPrecio;
import domain.Datos;
import domain.Reserva;


public class VentanaCliente extends JFrame{

	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	private static final long serialVersionUID = 1L;
	protected JPanel pListaReservas, pCrearEditarReserva,pInformacion,pPerfil,pCambiarPerfil,pBotonesVerReservas;
	protected JList<Reserva> listaReservas;
	protected JButton botonCerrar, botonReserva,bBorrarReserva,bEditarReserva,bInvertirLista;
	protected JComboBox<String>  comboOrdenar;
	protected JSpinner numeroDeHabitaciones;
	protected DefaultListModel<Reserva> modeloListaReservas;
	
	
	

	public VentanaCliente(Datos datos, Cliente cliente, String seleccionDatos, BDmanager bdManager) {
		
		
		setIconImage(new ImageIcon(cliente.getFotoPerfil()).getImage());
		System.out.println("Tamaño de la lista de reservas del cliente ->"+cliente.getListaReservasCliente().size());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700,300);
		setLocationRelativeTo(null);
		setTitle("Cliente "+ cliente.getNombre()+" "+cliente.getApellido1());
		
	class PanelCrearReserva extends JPanel{
			
			private static final long serialVersionUID = 1L;
			protected JPanel pAbajo, pPrincipal;
			protected JLabel lblFechaIni, lblFechaFin, lblReservaHabitacion, lblParking;
			protected JDatePicker datePickerIni, datePickerFin;
			protected JButton bReservarParking, bCancelarReserva, bConfirmarReserva, bReservarHabitacion;
			protected Reserva reserva;

			
			public void init() {
				logger.info("INIFORMACION: inicializado el panel crear/editar reserva");
				setVisible(false);
				setLayout(new BorderLayout());
				lblFechaIni = new JLabel("Fecha inicial: ",SwingConstants.CENTER);
				lblFechaFin = new JLabel("Fecha final: ",SwingConstants.CENTER);
				lblReservaHabitacion = new JLabel("Reserva de habitaciones y comedor -> ",SwingConstants.CENTER);
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

				
				bReservarParking = new JButton("Reserva Parking");
				bCancelarReserva = new JButton("Cancelar Reserva");
				bCancelarReserva.addActionListener((e)->{
		
					if (pCrearEditarReserva != null) {
						pCrearEditarReserva.setVisible(false);
					}
					pListaReservas.setVisible(false);
					pPerfil.setVisible(true);
					setSize(700,300);
					JOptionPane.showMessageDialog(null, "Reserva Cancelada", null, JOptionPane.INFORMATION_MESSAGE);
				});
				bConfirmarReserva = new JButton ("Confirmar Reserva");
				bReservarHabitacion = new JButton ("Reservar habitaciones");
				
				pPrincipal = new JPanel(new GridLayout(6,2));
				pPrincipal.add(lblFechaIni);
				pPrincipal.add(datePickerIni);
				pPrincipal.add(lblFechaFin);
				pPrincipal.add(datePickerFin);
				pPrincipal.add(lblReservaHabitacion);
				pPrincipal.add(bReservarHabitacion);
				pPrincipal.add(lblParking);
				pPrincipal.add(bReservarParking);
				
				pAbajo = new JPanel();
				pAbajo.add(bCancelarReserva);
				pAbajo.add(bConfirmarReserva);
				
				add(pPrincipal, BorderLayout.NORTH);
				add(pAbajo, BorderLayout.SOUTH);
			}
			public PanelCrearReserva() {
				init();	
				reserva = new Reserva();
				bConfirmarReserva.addActionListener((e)->{
					System.out.println("Tamaño de la lista de reservas del cliente "+cliente.getNombre()+" "+
				        	cliente.getApellido1()+" -> "+cliente.getListaReservasCliente().size());
					boolean ini = false;
					boolean fin = false;
					
					GregorianCalendar calendar = (GregorianCalendar) datePickerIni.getModel().getValue();
			        if(calendar!=null) {
			        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
				        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				        reserva.setFechaInicio(fechaLocal);
				        ini =true;
			        }else {
			        	JOptionPane.showMessageDialog(null, "Seleccione una fecha inicial", "ERROR", JOptionPane.ERROR_MESSAGE);
			        }
			        GregorianCalendar calendar2 = (GregorianCalendar) datePickerFin.getModel().getValue();
			        if(calendar2!=null) {
			        	ZonedDateTime zonedDateTime = calendar2.toZonedDateTime();
				        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				        reserva.setFechaFinal(fechaLocal);
				        fin =true;
			        }else {
			        	JOptionPane.showMessageDialog(null, "Seleccione una fecha final", "ERROR", JOptionPane.ERROR_MESSAGE);
			        }
			        
			        if(ini && fin) {
						
			        	modeloListaReservas.addElement(reserva);
			        	if(!cliente.getListaReservasCliente().contains(reserva)) {
							cliente.getListaReservasCliente().add(reserva);
						}
			        	datos.getListaReservas().add(reserva);
			        	reserva.setCliente(cliente);
			        	System.out.println("Tamaño de la lista de reservas del cliente "+cliente.getNombre()+" "+
			        	cliente.getApellido1()+" -> "+cliente.getListaReservasCliente().size());
			        	JOptionPane.showMessageDialog(this, "Reserva Guardada");
			        	
			        	if(seleccionDatos=="Base de datos") {
				        	try {
								bdManager.guardarReserva(reserva);
							} catch (BDexception e1) {
								System.err.println("Error guardando la reserva en la bd");
								e1.printStackTrace();
							}
			        	}
			        	
			        	reserva = new Reserva();
			        	
			        }

				});
				bReservarParking.addActionListener((e)->{
					reserva.setCliente(cliente);
					System.out.println("ID de la reserva creada ->"+reserva.getId());
					System.out.println("Tamaño de la lista de reservas del cliente "+cliente.getNombre()+" "+
				    cliente.getApellido1()+" -> "+cliente.getListaReservasCliente().size());
					if(!cliente.getListaReservasCliente().contains(reserva)) {
						cliente.getListaReservasCliente().add(reserva);
					}
					new VentanaParking(datos,reserva,cliente,seleccionDatos,bdManager);
					
				});
				
				bReservarHabitacion.addActionListener((e)->{
					reserva.setCliente(cliente);
					if(!cliente.getListaReservasCliente().contains(reserva)) {
						cliente.getListaReservasCliente().add(reserva);
					}
					new VentanaHotel(datos,reserva,cliente,seleccionDatos,bdManager);
				});
				
			}
			public PanelCrearReserva(Reserva reserva) {
				init();
				setVisible(true);
				bConfirmarReserva.addActionListener((e)->{
					GregorianCalendar calendar = (GregorianCalendar) datePickerIni.getModel().getValue();
			        if(calendar!=null) {
			        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
				        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				        reserva.setFechaInicio(fechaLocal);
			        }
			        GregorianCalendar calendar2 = (GregorianCalendar) datePickerFin.getModel().getValue();
			        if(calendar2!=null) {
			        	ZonedDateTime zonedDateTime = calendar2.toZonedDateTime();
				        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				        reserva.setFechaFinal(fechaLocal);     
			        }
			        
			        if(seleccionDatos=="Base de datos") {
			        	try {
							bdManager.actualizarReserva(reserva);
						} catch (BDexception e1) {
							System.err.println("Error actualizando la reserva en la bd");
							e1.printStackTrace();
						}
			        }
			        
				});
				bReservarParking.addActionListener((e)->{
					if (!cliente.getListaReservasCliente().contains(reserva)) {
						cliente.getListaReservasCliente().add(reserva);
					}
					
					new VentanaParking(datos,reserva,cliente,seleccionDatos, bdManager);
				});
				
				bReservarHabitacion.addActionListener((e)->{
					if (!cliente.getListaReservasCliente().contains(reserva)) {
						cliente.getListaReservasCliente().add(reserva);
					}
					new VentanaHotel(datos,reserva,cliente, seleccionDatos, bdManager);
				});
				
				
			}
			
		}
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
		
		cambiarFotoPerfil.addActionListener((e)->{
			 JFileChooser fileChooser = new JFileChooser();
			 FileFilter filter = new FileNameExtensionFilter("Fichero jpg o png", "jpg","png");
             fileChooser.setFileFilter(filter);
             int result = fileChooser.showOpenDialog(VentanaCliente.this);
             if (result == JFileChooser.APPROVE_OPTION) {
            	 File file = fileChooser.getSelectedFile();
            	 try {            		 
                	 File destino =new File( "src/Imagenes/"+file.getName());
					 Files.copy(Paths.get(file.getPath()), Paths.get(destino.getPath()),StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e2) {
					logger.log(Level.SEVERE, e2.getMessage());
					e2.printStackTrace();
				}
                 cliente.setFotoPerfil(file.getPath());
                 this.setIconImage(new ImageIcon(cliente.getFotoPerfil()).getImage());
                 Image imagenPerfilEscala = (new ImageIcon(cliente.getFotoPerfil()).getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH));
                 menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));
                 this.repaint();
                 
                 
                 System.out.println("Fichero seleccionado: " + file.getPath());
                 
                 if(seleccionDatos=="Base de datos") {
                	 try {
						bdManager.actualizarCliente(cliente);
					} catch (BDexception e1) {
						logger.log(Level.SEVERE, e1.getMessage());
						e1.printStackTrace();
					}
                 }
             }
             logger.info("INFORMACION: cambiada la foto de perfil");
		});
		
			
		
		
	
		ImageIcon imagenReserva = new ImageIcon("src/Imagenes/button_reserva-ahora.png");
		Image imagenReservaEscala = imagenReserva.getImage().getScaledInstance(100, 45,Image.SCALE_SMOOTH);
		menuCrearReservas.setIcon(new ImageIcon(imagenReservaEscala));
		
		ImageIcon imagenVerReserva = new ImageIcon("src/Imagenes/button_ver-reservas.png");
		Image imagenVerReservaEscala = imagenVerReserva.getImage().getScaledInstance(100, 45,Image.SCALE_SMOOTH);
		menuVerReservas.setIcon(new ImageIcon(imagenVerReservaEscala));
		
		ImageIcon imagenPerfil = new ImageIcon(cliente.getFotoPerfil());
		Image imagenPerfilEscala = imagenPerfil.getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH);
		menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));
		
		
		
		//Panel para ver y editar los datos del cliente
		
		pPerfil= new JPanel();
		pPerfil.setVisible(true);
		pInformacion = new JPanel();
		
		pInformacion.setLayout(new GridLayout(9,2));
		
		JLabel lblNombre = new JLabel("Introduzca su Nombre: ");
		JLabel lblContra = new JLabel("Introduzca su contraseña: ");
		JLabel lblDNI = new JLabel("Introduzca su DNI: ");
		JLabel lblApellido = new JLabel("Introdzca su apellido: ");
		JLabel lblEmail = new JLabel("Introduzca su e-mail: ");
		JLabel lblDireccion = new JLabel("Introduzca su direccion: ");
		JLabel lblFechaNacimiento = new JLabel("Introduzca su fecha de nacimiento: ");
		JLabel lblTelefono = new JLabel("Introduzca su teléfono: ");
		
		JTextField textoNombre = new JTextField(20);
		textoNombre.setEditable(false);
		textoNombre.setText(cliente.getNombre());
		JPasswordField textoContra = new JPasswordField(20);
		textoContra.setEditable(false);
		textoContra.setText(cliente.getContraseña());
		JTextField textoDNI = new JTextField(20);
		textoDNI.setEditable(false);
		textoDNI.setText(cliente.getDni());
		JTextField textoApellido = new JTextField(20);
		textoApellido.setEditable(false);
		textoApellido.setText(cliente.getApellido1());
		JTextField textoEmail = new JTextField(20);
		textoEmail.setEditable(false);
		textoEmail.setText(cliente.getEmail());
		JTextField textoDireccion = new JTextField(20);
		textoDireccion.setEditable(false);
		textoDireccion.setText(cliente.getDireccion());
		JTextField textoTelefono= new JTextField(20);
		textoTelefono.setEditable(false);
		textoTelefono.setText(cliente.getTelefono());
		JDatePicker date = new JDatePicker();
		date.setEnabled(false);
		date.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar calendar = (GregorianCalendar) date.getModel().getValue();
				ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
		        LocalDate fechaLocal = zonedDateTime.toLocalDate();
		        if (calendar!=null) {
			        if (fechaLocal.isAfter(LocalDate.now())) {
						date.getModel().setValue(null);
					}
		        }
			}
		});

		date.addDateSelectionConstraint(new DateSelectionConstraint() {
			
			@Override
			public boolean isValidSelection(DateModel<?> arg0) {
				
				GregorianCalendar calendar = (GregorianCalendar) arg0.getValue();
		        if(calendar!=null) {
		        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
			        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				    if (fechaLocal.isAfter(LocalDate.now())) {
							return false;
						}else {
							return true;
					}
		        }else{
		        	return true;
		        }		         
			}
		});
		
		
		
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
		
		JButton botonEditar = new JButton("Editar datos");
		JButton botonGuardar = new JButton("Guardar edicion");
		botonGuardar.setEnabled(false);
		
		botonEditar.addActionListener((e)->{
			textoNombre.setEditable(true);
			textoApellido.setEditable(true);
			textoContra.setEditable(true);
			textoDireccion.setEditable(true);
			textoDNI.setEditable(true);
			textoEmail.setEditable(true);
			textoTelefono.setEditable(true);
			date.setEnabled(true);
			
			botonGuardar.setEnabled(true);
			botonEditar.setEnabled(false);
		});
		
		botonGuardar.addActionListener((e)->{
			datos.getMapaClientesPorDNI().remove(cliente.getDni());
			textoNombre.setEditable(false);
			textoApellido.setEditable(false);
			textoContra.setEditable(false);
			textoDireccion.setEditable(false);
			textoDNI.setEditable(false);
			textoEmail.setEditable(false);
			textoTelefono.setEditable(false);
			date.setEnabled(false);
			
			botonGuardar.setEnabled(false);
			botonEditar.setEnabled(true);
			
			cliente.setNombre(textoNombre.getText());
			cliente.setApellido1(textoApellido.getText());
			cliente.setDni(textoDNI.getText());
			cliente.setContraseña(String.valueOf(textoContra.getPassword()));
			cliente.setDireccion(textoDireccion.getText());
			cliente.setEmail(textoEmail.getText());
			cliente.setTelefono(textoTelefono.getText());
			
			GregorianCalendar calendar = (GregorianCalendar) date.getModel().getValue();
	        if(calendar!=null) {
	        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
		        LocalDate fechaLocal = zonedDateTime.toLocalDate();
		        cliente.setfNacimiento(fechaLocal);
	        }
	        datos.getMapaClientesPorDNI().put(cliente.getDni(), cliente);
	        
	        if(seleccionDatos=="Base de datos") {
	        	try {
					bdManager.actualizarCliente(cliente);
				} catch (BDexception e1) {
					System.err.println("Error actualizando cliente");
					e1.printStackTrace();
				}
	        }
	        logger.info("INFORMACION: actualizados los datos del cliente");
	        
	        setTitle("Cliente "+ cliente.getNombre()+" "+cliente.getApellido1());
	    });
		pPerfil.add(botonEditar);
		pPerfil.add(botonGuardar);
		
		pPerfil.add(pInformacion);
		
		
		//Panel para ver todas las reservas, borrarlas y editarlas
		pListaReservas = new JPanel();
		pListaReservas.setBackground(new Color(0,140,255));
		
		modeloListaReservas = new DefaultListModel<Reserva> ();
		if(seleccionDatos=="Base de datos") {
			try {
				modeloListaReservas.addAll(bdManager.getReservasDeCliente(cliente));
			} catch (BDexception e1) {
				e1.printStackTrace();
			}
		}else {
			modeloListaReservas.addAll(cliente.getListaReservasCliente());
		}
		
		
		listaReservas = new JList<Reserva>(modeloListaReservas);
		listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		boolean run = true;
		Thread hilo = new Thread(new Runnable() {

			private int j=0;
			@Override
			public void run() {
				while(run) {
					for (j = 0; j < 255; j++) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								listaReservas.setSelectionBackground(new Color(0,j,255));								
							}
						});

						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							logger.log(Level.WARNING, "ERROR HILO INTERRUMPIDO");
						}
					}
					for (j = 0; j < 255; j++) {
						
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								listaReservas.setSelectionBackground(new Color(0,255-j,255));
							}
						});
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							logger.log(Level.WARNING, "ERROR HILO INTERRUMPIDO");
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
		bEditarReserva.addActionListener((e)->{
			pCrearEditarReserva= new PanelCrearReserva(listaReservas.getSelectedValue());
			add(pCrearEditarReserva,BorderLayout.SOUTH);
			pListaReservas.setVisible(false);
			pCrearEditarReserva.setVisible(true);
			pPerfil.setVisible(false);
			setSize(700,300);
			
		});
		
		
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
			datos.getListaReservas().remove(seleccionado);
			cliente.getListaReservasCliente().remove(seleccionado);
			modeloListaReservas.removeElement(seleccionado);
			listaReservas.repaint();
			
			if(seleccionDatos=="Base de datos") {
				try {
					bdManager.deleteReserva(seleccionado);
				} catch (BDexception e1) {
					e1.printStackTrace();
				}
			}
			
			seleccionado.getListaPlazasParking().forEach(p->{
				p.setOcupada(false);
				Reserva r = new Reserva();
				r.setId(-1);
				p.setReserva(r);
				
				if(seleccionDatos=="Base de datos") {
					try {
						bdManager.actualizarPlazaparking(p);
					} catch (BDexception e1) {
						e1.printStackTrace();
					}
				}
				
			});
			
			seleccionado.getListaHabitacionesReservadas().forEach(h->{
				h.setOcupado(false);
				Reserva r = new Reserva();
				r.setId(-1);
				h.setReserva(r);
				
				if(seleccionDatos=="Base de datos") {
					try {
						bdManager.actualizarHabitacion(h);
					} catch (BDexception e1) {
						e1.printStackTrace();
					}
				}
			});
			
		});
		
		
		pListaReservas.add(pBotonesVerReservas, BorderLayout.SOUTH);
		
		
		pListaReservas.setVisible(false);
		
		
		//Panel para crear nuevas reservas

		verReservas.addActionListener((e)->{
			if (pCrearEditarReserva != null) {
				remove(pCrearEditarReserva);
			}
			pListaReservas.setVisible(true);
			pPerfil.setVisible(false);
			setSize(900,350);
			setLocationRelativeTo(null);
		});
		
		cerrarSesion.addActionListener((e)->{
			if(seleccionDatos=="Fichero de datos") {
				datos.guardarDatos();
			}	
			dispose();
			new VentanaInicioCliente(datos,seleccionDatos,bdManager);
		});
		
		informacionCliente.addActionListener((e)->{
			if (pCrearEditarReserva != null) {
				remove(pCrearEditarReserva);
			}
			pListaReservas.setVisible(false);
			pPerfil.setVisible(true);
			setSize(700,300);
		});
		crearReservas.addActionListener((e)->{
			pCrearEditarReserva = new PanelCrearReserva();
			pListaReservas.setVisible(false);
			pCrearEditarReserva.setVisible(true);
			pPerfil.setVisible(false);
			add(pCrearEditarReserva,BorderLayout.SOUTH);
			setSize(700,300);
			setLocationRelativeTo(null);
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				if(seleccionDatos=="Fichero de datos") {
					datos.guardarDatos();
				}else if(seleccionDatos=="Base de datos") {
					try {
						bdManager.disconnect();
					} catch (BDexception e1) {
						System.err.println("Error desconectando la base de datos");
						e1.printStackTrace();
					}
				}
				System.out.println("Tamaño de lista de reservas del cliente ->" +cliente.getListaReservasCliente().size());
			}

		});
		add(pPerfil,BorderLayout.NORTH);
		
		add(pListaReservas, BorderLayout.CENTER);
		
		setVisible(true);
		
	}
	
}
