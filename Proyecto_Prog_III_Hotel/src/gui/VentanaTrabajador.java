package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import domain.BDexception;
import domain.BDmanager;
import domain.Cliente;
import domain.Datos;
import domain.Reserva;
import domain.Tarea;
import domain.Trabajador;


public class VentanaTrabajador extends JFrame {
    
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	private static final long serialVersionUID = 1L;
	protected JProgressBar tareasHacer;
	protected JPanel pTrabajadores,pClientes,pPerfil,pInformacion;
    protected JTable tablaDatosTrabajadores, tablaDatosClientes;
    protected JButton botonCerrar, botonTareaHecha;
    protected List<Tarea> tareasPendientes; //= trabajador.getListaTareasPorHacer(); // ejemplo de tareas pendientes que tiene un trabajador

    public VentanaTrabajador(Datos datos, Trabajador trabajador,String seleccionDatos, BDmanager bdManager) {
		//TODO falta implementar la base de datos

    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setSize(900,350);
    	setLocationRelativeTo(null);
    	setTitle("Trabajador: "+ trabajador.getNombre()+" "+trabajador.getApellido1());
    	JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuCliente = new JMenu();
		menuBar.add(menuCliente);
		JMenuItem itemCliente = new JMenuItem("INFORMACION DE CLIENTES");
		menuCliente.add(itemCliente);
		JMenu menuTrabajador = new JMenu();
		menuBar.add(menuTrabajador);
		JMenuItem itemTrabajador = new JMenuItem("INFORMACION DE LOS TRABAJADORES");
		menuTrabajador.add(itemTrabajador);
		JMenu menuPerfil = new JMenu();
		menuBar.add(menuPerfil);
		JMenuItem cambiarFotoPerfil = new JMenuItem("CAMBIAR FOTO DE PERFIL");
		menuPerfil.add(cambiarFotoPerfil);
		JMenuItem informacionTrabajador = new JMenuItem("VER/EDITAR MIS DATOS");
		menuPerfil.add(informacionTrabajador);
		JMenuItem cerrarSesion = new JMenuItem("CERRAR SESION");
		menuPerfil.add(cerrarSesion);

		//Listeners de los menuItems
		
		itemTrabajador.addActionListener((e)->{
			getContentPane().removeAll();
			getContentPane().add(pTrabajadores);
			repaint();
		
		});
		
		itemCliente.addActionListener((e)->{
			getContentPane().removeAll();
			getContentPane().add(pClientes);
			repaint();
		});
		
		cambiarFotoPerfil.addActionListener((e)->{

			JFileChooser fileChooser = new JFileChooser();
			 FileFilter filter = new FileNameExtensionFilter("Fichero jpg o png", "jpg","png");
             fileChooser.setFileFilter(filter);
             int result = fileChooser.showOpenDialog(VentanaTrabajador.this);
             if (result == JFileChooser.APPROVE_OPTION) {
            	 File file = fileChooser.getSelectedFile();
            	 try {            		 
                	 File destino =new File("src/Imagenes/"+trabajador.getDni()+file.getName());
					 Files.copy(Paths.get(file.getPath()), Paths.get(destino.getPath()),StandardCopyOption.REPLACE_EXISTING);
					 if(trabajador.getFotoPerfil()!="src/Imagenes/imagenPerfilpng.png") {
						 Files.deleteIfExists(Paths.get(trabajador.getFotoPerfil()));
					 }
					 trabajador.setFotoPerfil("src/Imagenes/"+trabajador.getDni()+file.getName());
					 
				} catch (IOException e2) {
					logger.log(Level.SEVERE, e2.getMessage());
					e2.printStackTrace();
				}
                 this.setIconImage(new ImageIcon(trabajador.getFotoPerfil()).getImage());
                 Image imagenPerfilEscala = (new ImageIcon(trabajador.getFotoPerfil()).getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH));
                 menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));
                 this.repaint();
                 
                 
                 logger.info("Fichero seleccionado: " + file.getPath());
                 
                 if(seleccionDatos=="Base de datos") {
                	 try {
						bdManager.actualizarTrabajador(trabajador);
					} catch (BDexception e1) {
						logger.log(Level.SEVERE, e1.getMessage());
						e1.printStackTrace();
					}
                 }
             }
             logger.info("INFORMACION: cambiada la foto de perfil");
		});
		
		informacionTrabajador.addActionListener((e)->{
			getContentPane().remove(pTrabajadores);
			getContentPane().remove(pClientes);
			getContentPane().add(pPerfil,BorderLayout.CENTER);
			pPerfil.repaint();
			pPerfil.setVisible(true);
			getContentPane().repaint();


		});
		
		cerrarSesion.addActionListener((e)->{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new VentanaInicioTrabajador(datos, seleccionDatos,bdManager);
					
				}
			});
			dispose();
		});
		
		//Fotos de los menus
		ImageIcon imagenPerfil = new ImageIcon("src/Imagenes/imagenPerfilpng.png");
		Image imagenPerfilEscala = imagenPerfil.getImage().getScaledInstance(60, 45,Image.SCALE_SMOOTH);
		menuPerfil.setIcon(new ImageIcon(imagenPerfilEscala));

		
		ImageIcon imagenClientes = new ImageIcon("src/Imagenes/iconoClientesMenu.png");
		Image imagenCclienteEscala = imagenClientes.getImage().getScaledInstance(150, 45,Image.SCALE_SMOOTH);
		menuCliente.setIcon(new ImageIcon(imagenCclienteEscala));

		ImageIcon imagenTrabajadores = new ImageIcon("src/Imagenes/iconoMenuTrabajadores.png");
		Image imagenTrabajadoresEscala = imagenTrabajadores.getImage().getScaledInstance(150, 45,Image.SCALE_SMOOTH);
		menuTrabajador.setIcon(new ImageIcon(imagenTrabajadoresEscala));

		//Panel del perfil
		
		

		pPerfil= new JPanel();
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
		textoNombre.setText(trabajador.getNombre());
		JPasswordField textoContra = new JPasswordField(20);
		textoContra.setEditable(false);
		textoContra.setText(trabajador.getContraseña());
		JTextField textoDNI = new JTextField(20);
		textoDNI.setEditable(false);
		textoDNI.setText(trabajador.getDni());
		JTextField textoApellido = new JTextField(20);
		textoApellido.setEditable(false);
		textoApellido.setText(trabajador.getApellido1());
		JTextField textoEmail = new JTextField(20);
		textoEmail.setEditable(false);
		textoEmail.setText(trabajador.getEmail());
		JTextField textoDireccion = new JTextField(20);
		textoDireccion.setEditable(false);
		textoDireccion.setText(trabajador.getDireccion());
		JTextField textoTelefono= new JTextField(20);
		textoTelefono.setEditable(false);
		textoTelefono.setText(trabajador.getTelefono());
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
			datos.getMapaTrabajadoresPorDNI().remove(trabajador.getDni());
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
			
			trabajador.setNombre(textoNombre.getText());
			trabajador.setApellido1(textoApellido.getText());
			trabajador.setDni(textoDNI.getText());
			trabajador.setContraseña(String.valueOf(textoContra.getPassword()));
			trabajador.setDireccion(textoDireccion.getText());
			trabajador.setEmail(textoEmail.getText());
			trabajador.setTelefono(textoTelefono.getText());
			
			GregorianCalendar calendar = (GregorianCalendar) date.getModel().getValue();
	        if(calendar!=null) {
	        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
		        LocalDate fechaLocal = zonedDateTime.toLocalDate();
		        trabajador.setfNacimiento(fechaLocal);
	        }
	        datos.getMapaTrabajadoresPorDNI().put(trabajador.getDni(), trabajador);
	        
	        if(seleccionDatos=="Base de datos") {
	        	try {
					bdManager.actualizarTrabajador(trabajador);
				} catch (BDexception e1) {
					System.err.println("Error actualizando trabajador");
					e1.printStackTrace();
				}
	        }
	        logger.info("INFORMACION: actualizados los datos del trabajador");
	        
	        setTitle("Trabajador "+ trabajador.getNombre()+" "+trabajador.getApellido1());
	    });
		pPerfil.add(botonEditar);
		pPerfil.add(botonGuardar);
		
		pPerfil.add(pInformacion);
		
		
		
		
        
        //Panel de la informacion de los trabajadores
		pTrabajadores = new JPanel();

		
		
		
		class ModeloTablaTrabajadores extends AbstractTableModel{

			private static final long serialVersionUID = 1L;
			private String[] headers= {"DNI", "Nombre", "Apellido", "E-mail", "Contraseña", "Fecha Nac.", "Teléfono", "Salário", "Horas"};

			
			
			@Override
			public String getColumnName(int column) {
				return headers[column];
			}

			@Override
			public int getRowCount() {
				
				return datos.getListaTrabajadores().size();
			}

			@Override
			public int getColumnCount() {
				return headers.length;
			}

			
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Trabajador t = datos.getListaTrabajadores().get(rowIndex);
				
				switch (columnIndex) {
				
				case 0: return t.getDni();
				case 1: return t.getNombre();
				case 2: return t.getApellido1();
				case 3: return t.getEmail();
				case 4: return t.getContraseña();
				case 5: return t.getfNacimiento();
				case 6: return t.getTelefono();
				case 7: return t.getSalario();
				case 8: return t.getNumHorasTrabajadas();
						
				}
				
				return null;
			}
			
		}
		
		class RendererTablaTrabajadores implements TableCellRenderer{


			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel("", SwingConstants.CENTER);
				label.setOpaque(true);
				label.setText(value.toString());
				if (row%2==0) {
					label.setBackground(new Color(255,204,153));
				}else {
					label.setBackground(new Color(255,255,153));
				}
				
				return label;
			}
			
			
			
		}
		
		tablaDatosTrabajadores = new JTable(new ModeloTablaTrabajadores());
		tablaDatosTrabajadores.setDefaultRenderer(Object.class, new RendererTablaTrabajadores());
		JScrollPane scrollTrabajadores = new JScrollPane(tablaDatosTrabajadores);

		for (int i = 0; i < 3; i++) {
			tablaDatosTrabajadores.getColumnModel().getColumn(i).setPreferredWidth(70);
		}
		tablaDatosTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(110);
		tablaDatosTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(70);
		tablaDatosTrabajadores.getColumnModel().getColumn(5).setPreferredWidth(60);
		tablaDatosTrabajadores.getColumnModel().getColumn(6).setPreferredWidth(60);
		tablaDatosTrabajadores.getColumnModel().getColumn(7).setPreferredWidth(40);
		tablaDatosTrabajadores.getColumnModel().getColumn(8).setPreferredWidth(20);
		
		pTrabajadores.setPreferredSize(new Dimension((int)(this.getSize().getWidth()-40),(int)(this.getSize().getHeight()-40)));
		scrollTrabajadores.setPreferredSize(pTrabajadores.getPreferredSize());
		pTrabajadores.add(scrollTrabajadores, BorderLayout.CENTER);
		
		
		//Creacion del panel de la informacion de los clientes
		
		
		pClientes = new JPanel();
		
		
		//Creacion del modelo de tabla
		
		
		class ModeloTablaClientes extends AbstractTableModel{

		
			private static final long serialVersionUID = 1L;
			private String[] headers= {"DNI", "Nombre", "Apellido", "E-mail", "Contraseña"
					, "Fecha Nac.", "Teléfono", "Reservas", "Último Login"};
			@Override
			public int getRowCount() {
				return datos.getListaClientes().size();
			}

			@Override
			public int getColumnCount() {
				return headers.length;
			}

			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    if(columnIndex==7) {
			    	return false;
			    }else {
					return true;

			    }
			}

			@Override
			public String getColumnName(int column) {
				return headers[column];
			}

			
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				Cliente c = datos.getListaClientes().get(rowIndex);
				
				switch (columnIndex) {
				
				case 0: c.setDni(aValue.toString());
				break;
				case 1: c.setNombre(aValue.toString());
				break;
				case 2: c.setApellido1(aValue.toString());
				break;
				case 3: c.setEmail(aValue.toString());
				break;
				case 4: c.setContraseña(aValue.toString());
				break;
				case 5: c.setfNacimiento((LocalDate) aValue);
				break;
				case 6: c.setTelefono(aValue.toString());
				break;
				case 8: c.setUltimoLogin((LocalDate) aValue);
				break;
				}
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Cliente c = datos.getListaClientes().get(rowIndex);
				
				switch (columnIndex) {
				
				case 0: return c.getDni();
				case 1: return c.getNombre();
				case 2: return c.getApellido1();
				case 3: return c.getEmail();
				case 4: return c.getContraseña();
				case 5: return c.getfNacimiento();
				case 6: return c.getTelefono();
				case 7: return "Reservas";
				case 8: return c.getUltimoLogin();
				
				}
				return null;
			}		
			
		}
		
		//Renderer de la tabla de clientes
		
		class RendererTablaClientes implements TableCellRenderer{

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel("",SwingConstants.CENTER);
				label.setOpaque(true);
				if(value==null) {
					label.setText("---");
				}else {
					label.setText(value.toString());

				}
				if(row%2==0) {
					label.setBackground(new Color(153,204,255));
				}else {
					label.setBackground(new Color(153,153,255));

				}
				
				if (isSelected) {
					label.setBorder(BorderFactory.createLineBorder(Color.black,2));
				}
				return label;
			}
		}
		//Etitor de las columnas de fNacimiento y ultimoLogin
		
		class EditorTablaClientesReservas extends AbstractCellEditor implements TableCellEditor{

			private static final long serialVersionUID = 1L;
			private LocalDate fecha;
			private JDatePicker picker;
			
			@Override
			public Object getCellEditorValue() {
				return fecha;	
			}	

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				picker = new JDatePicker();
								
				picker.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar calendar = (GregorianCalendar) picker.getModel().getValue();
						ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
				        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				        if (calendar!=null) {
					        if (fechaLocal.isAfter(LocalDate.now())) {
								picker.getModel().setValue(null);
							}else {
								fecha = fechaLocal;
								fireEditingStopped();
							}
				        }
						
					}
				});

				picker.addDateSelectionConstraint(new DateSelectionConstraint() {
					
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
								 
				
				return picker;
			}
		}
		
		
		
		tablaDatosClientes = new JTable(new ModeloTablaClientes());
		
	

		tablaDatosClientes.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(tablaDatosClientes.getSelectedRow()!=-1) {
					Cliente cliente = datos.getMapaClientesPorDNI().get(tablaDatosClientes.getValueAt(tablaDatosClientes.getSelectedRow(), 0));
					if(tablaDatosClientes.getSelectedColumn()==7) {
						
						DefaultListModel<String> modelo = new DefaultListModel<>();
						cliente.getListaReservasCliente().forEach(r->{
							modelo.addElement(r.toString());
						});
						JList<String> lista = new JList<>();
						if (modelo.isEmpty()) {
							modelo.addElement("No hay reservas para este cliente");
						}
						lista.setModel(modelo);
						JScrollPane scroll = new JScrollPane(lista);
						
						JOptionPane.showMessageDialog(VentanaTrabajador.this, scroll,"Reservas del cliente "+ cliente.getNombre()+" "+cliente.getApellido1(), JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			
		});
		
		
		tablaDatosClientes.setDefaultRenderer(Object.class, new RendererTablaClientes());
		
		tablaDatosClientes.getColumnModel().getColumn(5).setCellEditor(new EditorTablaClientesReservas());
		tablaDatosClientes.getColumnModel().getColumn(8).setCellEditor(new EditorTablaClientesReservas());
		
		tablaDatosClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaDatosClientes.getColumnModel().getColumn(1).setPreferredWidth(25);
		tablaDatosClientes.getColumnModel().getColumn(2).setPreferredWidth(30);

		tablaDatosClientes.getColumnModel().getColumn(3).setPreferredWidth(90);
		tablaDatosClientes.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablaDatosClientes.getColumnModel().getColumn(5).setPreferredWidth(35);
		tablaDatosClientes.getColumnModel().getColumn(6).setPreferredWidth(45);
		tablaDatosClientes.getColumnModel().getColumn(7).setPreferredWidth(20);
		tablaDatosClientes.getColumnModel().getColumn(8).setPreferredWidth(20);
		
		JScrollPane scrollClientes = new JScrollPane(tablaDatosClientes);
		pClientes.setPreferredSize(new Dimension((int)(this.getSize().getWidth()-20),(int)(this.getSize().getHeight()-40)));
		scrollClientes.setPreferredSize(pClientes.getPreferredSize());
		pClientes.add(scrollClientes, BorderLayout.CENTER);
		
		


        setVisible(true);
        
    }
  /*
    public static void main(String[] args) {
    	Datos datos = new Datos ();
    	datos.inicializarDatos();
    	Trabajador t = new Trabajador();
		 new VentanaTrabajador(datos, t, "Fichero de datos", null);
	}
	*/
    
}
