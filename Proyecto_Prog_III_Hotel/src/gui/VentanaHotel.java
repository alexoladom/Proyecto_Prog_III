package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import domain.BDexception;
import domain.BDmanager;
import domain.Cliente;
import domain.Datos;
import domain.Habitacion;
import domain.Mesa;
import domain.Reserva;

public class VentanaHotel extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	
	private static final long serialVersionUID = 1L;
	protected JButton botonReserva, botonTerminarReserva, botonCerrar;
	protected JPanel pBotones, pArbol;
	protected Datos datos;
//	protected JDatePicker datePicker;
	protected boolean estado = false;
	//Componentes para la JTable
	private JTable tablaComedor, tablaA, tablaB, tablaC;
	private JScrollPane scrollComedor, scrollA, scrollB, scrollC;
	//Componentes para el arbol
	private DefaultTreeModel modeloArbol;							
	private JTree arbol;
	//Componentes para una JList
	private DefaultListModel<Habitacion> modeloLista;
	private JList<Habitacion> listaReservas;
	
	public VentanaHotel(Datos datos,Reserva reserva, Cliente cliente, String seleccionDatos, BDmanager bdManager) {
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
		ImageIcon image = new ImageIcon("src/Imagenes/h.png");
		setIconImage(image.getImage());
		this.datos=datos;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,800);
		setTitle("Hotel");		
		
		
		//Creo los botones
		
		botonReserva = new JButton ("Reserva");
		botonTerminarReserva = new JButton ("Acabar reserva");
		botonCerrar = new JButton ("Cerrar");
		pBotones = new JPanel();
		pArbol = new JPanel();
		logger.info("Se han creado los botones");
		
		//Creación de la tabla A
		String [] titulos = {"Estado ocupación","nº Planta","nº Habitacion"};
		class MiModeloA extends AbstractTableModel{

		
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(0) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(0).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
//				if(calendar != null) {
//			        editable =Habitacion.comprobarHabitacionDisponible(cliente, Habitacion.getDistribucion()[rowIndex-1][columnIndex-1]);
//				}else {
//					editable=false;
//				}
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
				switch(columnIndex) {
					case 0: if(h.getReserva()==reserva){
						return "Reservado"; 
					}else if(h.getReserva()!=reserva && h.getReserva().getId()!=-1){
						return "Ocupado por otra reserva o cliente";
					}else{
						return "Libre";
					}
					
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
					  if(h.getReserva()==null||h.getReserva()==reserva||h.getReserva().getId()==-1) {
						  h.setOcupado(ocupado);
						    
						    if(ocupado==true) {
						    	h.setReserva(reserva);
						    }else {
						    	Reserva r = new Reserva();
						    	r.setId(-1);
						    	h.setReserva(r);
						    }
						    
						    if(seleccionDatos=="Base de datos") {
						    	try {
									bdManager.actualizarHabitacion(h);
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error actualizando la habitacion en la bd");
									e.printStackTrace();
								}
						    }
					  }else {
						  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta ocupada por otra reserva cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
					  }
					   
				}

			    fireTableCellUpdated(rowIndex, 0);
			}
		}
		logger.info("Se ha creado el modelo de la tabla A");
		tablaA = new JTable(new MiModeloA());
		tablaA.setRowHeight(50);
		tablaA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollA = new JScrollPane(tablaA);
		
		//Creación de la tabla B
		class MiModeloB extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(1) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(1).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(1).get(rowIndex);
				switch(columnIndex) {
					case 0: if(h.getReserva()==reserva){
						return "Reservado"; 
					}else if(h.getReserva()!=reserva && h.getReserva().getId()!=-1){
						return "Ocupado por otra reserva o cliente";
					}else{
						return "Libre";
					}
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(1).get(rowIndex);
					  if(h.getReserva()==null||h.getReserva()==reserva||h.getReserva().getId()==-1) {
						  h.setOcupado(ocupado);
						    
						    if(ocupado==true) {
						    	h.setReserva(reserva);
						    }else {
						    	Reserva r = new Reserva();
						    	r.setId(-1);
						    	h.setReserva(r);
						    }
						    
						    if(seleccionDatos=="Base de datos") {
						    	try {
									bdManager.actualizarHabitacion(h);
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error actualizando la habitacion en la bd");
									e.printStackTrace();
								}
						    }
					  }else {
						  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta ocupada por otra reserva cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
					  }
				}

			    fireTableCellUpdated(rowIndex, 1);
			}
		}
		logger.info("Se ha creado el modelo de la tabla B");
		tablaB = new JTable(new MiModeloB());
		tablaB.setRowHeight(50);
		tablaB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollB = new JScrollPane(tablaB);
		
		//Creación de la tabla C
		class MiModeloC extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(2) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(2).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(2).get(rowIndex);
				switch(columnIndex) {
					case 0: if(h.getReserva()==reserva){
						return "Reservado"; 
					}else if(h.getReserva()!=reserva &&h.getReserva().getId()!=-1){					
						return "Ocupado por otra reserva o cliente";
					}else{
						return "Libre";
					}
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(2).get(rowIndex);
					  if(h.getReserva()==null||h.getReserva()==reserva||h.getReserva().getId()==-1) {
						    h.setOcupado(ocupado);
						    
						    if(ocupado==true) {
						    	h.setReserva(reserva);
						    }else {
						    	Reserva r = new Reserva();
						    	r.setId(-1);
						    	h.setReserva(r);
						    }
						    
						    if(seleccionDatos=="Base de datos") {
						    	try {
									bdManager.actualizarHabitacion(h);
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error actualizando la habitacion en la bd");
									e.printStackTrace();
								}
						    }
					  }else {
						  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta ocupada por otra reserva o cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
					  }
				}

			    fireTableCellUpdated(rowIndex, 2);
			}
		}
		logger.info("Se ha creado el modelo de la tabla C");
		tablaC = new JTable(new MiModeloC());
		tablaC.setRowHeight(50);
		tablaC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollC = new JScrollPane(tablaC);
		
		//Creación de la tabla Comedor
		String [] titulos1 = {"nº Mesas","Estado"};
		class MiModeloComedor extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getListaComedor() == null) {
					return 0;
				}else {
					return datos.getListaComedor().size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos1.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos1[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Mesa c = datos.getListaComedor().get(rowIndex);
				switch(columnIndex) {
				case 0: return c.getNumero();
				case 1: if(c.getReserva()==reserva){
					return "Reservado"; 
				}else if(c.getReserva()!=reserva &&c.getReserva().getId()!=-1){					
					return "Ocupado por otra reserva o cliente";
				}else{
					return "Libre";
				}
				
				default : return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
			   if(rowIndex!=-1) {
				   Mesa c = datos.getListaComedor().get(rowIndex);
				   if(c.getReserva()==null||c.getReserva()==reserva||c.getReserva().getId()==-1) {
					    c.setOcupado(ocupado);
					    
					    if(ocupado==true) {
					    	c.setReserva(reserva);
					    }else {
					    	Reserva r = new Reserva();
					    	r.setId(-1);
					    	c.setReserva(r);
					    }
					    
					    if(seleccionDatos=="Base de datos") {
					    	try {
								bdManager.actualizarMesa(c);
							} catch (BDexception e) {
								logger.log(Level.SEVERE, "Error actualizando la mesa en la bd");
								e.printStackTrace();
							}
					    }
				  }else {
					  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta mesa ya esta ocupada por otra reserva o cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
				  }
				    
			   }
			    fireTableCellUpdated(rowIndex, 1);
			}

		}
		logger.info("Se ha creado el modelo de la tabla del comedor");
		tablaComedor = new JTable(new MiModeloComedor());
		tablaComedor.setRowHeight(50);
		tablaComedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollComedor = new JScrollPane(tablaComedor);
		
		//Creacion del arbol
		DefaultMutableTreeNode hotel = new DefaultMutableTreeNode("Hotel");		
		DefaultMutableTreeNode elemA = new DefaultMutableTreeNode("Comedor");		
		DefaultMutableTreeNode elemB = new DefaultMutableTreeNode("Planta A");		
		DefaultMutableTreeNode elemC = new DefaultMutableTreeNode("Planta B");		
		DefaultMutableTreeNode elemD = new DefaultMutableTreeNode("Planta C");		
		
		modeloArbol = new DefaultTreeModel(hotel);					
		modeloArbol.insertNodeInto(elemA, hotel, 0);					
		modeloArbol.insertNodeInto(elemB, hotel, 1);					
		modeloArbol.insertNodeInto(elemC, hotel, 2);					
		modeloArbol.insertNodeInto(elemD, hotel, 3);					
		
		arbol = new JTree(modeloArbol);
		pArbol.add(arbol);
		logger.info("Se ha creado el arbol");
		
		//Creacion de la JList
		modeloLista = new DefaultListModel<Habitacion>();
		listaReservas = new JList<Habitacion>(modeloLista);

	   
		getContentPane().add(pBotones, BorderLayout.EAST);
		getContentPane().add(pArbol, BorderLayout.WEST);
		JPanel pAuxiliar = new JPanel();
		pAuxiliar.add(botonReserva);
		pAuxiliar.add(botonCerrar);
		pAuxiliar.add(botonTerminarReserva);
		pBotones.setLayout(new BorderLayout());
		pBotones.add(pAuxiliar, BorderLayout.NORTH);
		pBotones.add(listaReservas,BorderLayout.CENTER);
		
		//ActionListeners de los botones
		botonCerrar.addActionListener((e) -> {
			dispose();
			logger.info("Se cierra la ventana del hotel");
		});
		
		botonReserva.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//Tabla A
		        MiModeloA modeloA = (MiModeloA) tablaA.getModel();
		        modeloA.actualizarEstado(tablaA.getSelectedRow(), true);
		        tablaA.repaint();
		        //Tabla B
		        MiModeloB modeloB = (MiModeloB) tablaB.getModel();
		        modeloB.actualizarEstado(tablaB.getSelectedRow(), true);
		        tablaB.repaint();
		        //Tabla C
		        MiModeloC modeloC = (MiModeloC) tablaC.getModel();
		        modeloC.actualizarEstado(tablaC.getSelectedRow(), true);
		        tablaC.repaint();
		        //Tabla Comedor
		        
		        if(tablaComedor.getSelectedRow()!=-1) {
		        	Mesa m = datos.getListaComedor().get(tablaComedor.getSelectedRow());
		        	if(m.getReserva().equals(reserva)&&m.isOcupado()==true) { 
		        		JOptionPane.showMessageDialog(VentanaHotel.this, "Esta mesa ya esta añadida", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        	}
		        }
		        
		        MiModeloComedor modeloComedor = (MiModeloComedor) tablaComedor.getModel();
		        modeloComedor.actualizarEstado(tablaComedor.getSelectedRow(), true);
		        tablaComedor.repaint();
		        //JList
		        if(tablaA.getSelectedRow()!=-1) {
		        	Habitacion h1 = datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow());	
		        	if(h1.getReserva()==reserva||reserva.getListaHabitacionesReservadas().contains(h1)) {
		        			reserva.getListaHabitacionesReservadas().add(h1);
					        if(modeloLista.contains(h1)) {
								  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta añadida", "Advertencia", JOptionPane.WARNING_MESSAGE);
					        }else {
					        	modeloLista.addElement(h1);
					        }
		        	}
		        }
		        if(tablaB.getSelectedRow()!=-1) {
		        	Habitacion h2 = datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow());
		        	if(h2.getReserva()==reserva||reserva.getListaHabitacionesReservadas().contains(h2)) {
				        reserva.getListaHabitacionesReservadas().add(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
				        if(modeloLista.contains(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()))) {
							  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta añadida", "Advertencia", JOptionPane.WARNING_MESSAGE);
				        }else {
				        	modeloLista.addElement(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
				        }
		        	}
		        }
		        if(tablaC.getSelectedRow()!=-1) {
		        	Habitacion h3 = datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow());
		        	if(h3.getReserva()==reserva||reserva.getListaHabitacionesReservadas().contains(h3)) {
				        reserva.getListaHabitacionesReservadas().add(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
				        if(modeloLista.contains(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()))) {
							  JOptionPane.showMessageDialog(VentanaHotel.this, "Esta habitacion ya esta añadida", "Advertencia", JOptionPane.WARNING_MESSAGE);
				        }else {
				        	modeloLista.addElement(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
				        }
		        	}
		        }
		        
		        
		        listaReservas.setModel(modeloLista);
		    }
		});
		
		botonTerminarReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Tabla A
				MiModeloA modeloA = (MiModeloA) tablaA.getModel();
		        modeloA.actualizarEstado(tablaA.getSelectedRow(), false);
		        tablaA.repaint();
		        //Tabla B
				MiModeloB modeloB = (MiModeloB) tablaB.getModel();
		        modeloB.actualizarEstado(tablaB.getSelectedRow(), false);
		        tablaB.repaint();
		        //Tabla C
				MiModeloC modeloC = (MiModeloC) tablaC.getModel();
		        modeloC.actualizarEstado(tablaC.getSelectedRow(), false);
		        tablaC.repaint();
		        //Tabla Comedor
				MiModeloComedor modeloComedor = (MiModeloComedor) tablaComedor.getModel();
		        modeloComedor.actualizarEstado(tablaComedor.getSelectedRow(), false);
		        tablaComedor.repaint();
		        //Jlist
		        
	       
		        if(tablaA.getSelectedRow()!=-1) {
		        	Habitacion h1 = datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow());
		        	 if(modeloLista.contains(h1)) {
				        reserva.getListaHabitacionesReservadas().remove(h1);
				        modeloLista.removeElement(h1);
				        
		        	 }
		        	 tablaA.clearSelection();
		        	 tablaB.clearSelection();
		        	 tablaC.clearSelection();
		        	 tablaComedor.clearSelection();
		        }
		        if(tablaB.getSelectedRow()!=-1) {		        	 
		        	Habitacion h2 = datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow());
		        	if(modeloLista.contains(h2)) {
				        reserva.getListaHabitacionesReservadas().remove(h2);
				        modeloLista.removeElement(h2);
				        
		        	}
		        	tablaA.clearSelection();
		        	tablaB.clearSelection();
		        	tablaC.clearSelection();
		        	tablaComedor.clearSelection();

		        }
		        if(tablaC.getSelectedRow()!=-1) {
		        	Habitacion h3 = datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow());
		        	if(modeloLista.contains(h3)) {
				        reserva.getListaHabitacionesReservadas().remove(h3);
				        modeloLista.removeElement(h3);
				        
		        	}
		        	tablaA.clearSelection();
		        	tablaB.clearSelection();
		        	tablaC.clearSelection();
		        	tablaComedor.clearSelection();

		        }
		        if(tablaComedor.getSelectedRow()!=-1) {
		        	   
		        	
		        	tablaA.clearSelection();
		        	tablaB.clearSelection();
		        	tablaC.clearSelection();
		        	tablaComedor.clearSelection();

		        }
		        listaReservas.setModel(modeloLista);
		        listaReservas.repaint();
			    
		        
			}
		});
		
		//Creacion de los render de cada tabla
		tablaA.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel(value.toString(), SwingConstants.CENTER);
		        
		        l.setOpaque(true);		       
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (tablaA.getModel().getValueAt(row, 0)=="Reservado") {
		            l.setBackground(Color.RED);
		        } else if (tablaA.getModel().getValueAt(row, 0)=="Libre"){
		            l.setBackground(Color.GREEN);
		        }else {
		        	 l.setBackground(Color.GRAY);
		        }
		        
		        return l;
		    }
		});
		tablaB.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel(value.toString(), SwingConstants.CENTER);
		        
		        l.setOpaque(true);		        
		       
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (tablaB.getModel().getValueAt(row, 0)=="Reservado") {
		            l.setBackground(Color.RED);
		        } else if (tablaB.getModel().getValueAt(row, 0)=="Libre"){
		            l.setBackground(Color.GREEN);
		        }else {
		        	 l.setBackground(Color.GRAY);
		        }
		        
		        return l;
		    }
		});
		tablaC.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel(value.toString(), SwingConstants.CENTER);
		        
		        l.setOpaque(true);		        
		        
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (tablaC.getModel().getValueAt(row, 0)=="Reservado") {
		            l.setBackground(Color.RED);
		        } else if (tablaC.getModel().getValueAt(row, 0)=="Libre"){
		            l.setBackground(Color.GREEN);
		        }else {
		        	 l.setBackground(Color.GRAY);
		        }
		        
		        return l;
		    }
		});
		tablaComedor.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel(value.toString(), SwingConstants.CENTER);
		        
		        l.setOpaque(true);
		        
		        
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (tablaComedor.getModel().getValueAt(row, 1)=="Reservado") {
		            l.setBackground(Color.RED);
		        } else if(tablaComedor.getModel().getValueAt(row, 1)=="Libre"){
		            l.setBackground(Color.GREEN);
		        }else {
		        	l.setBackground(Color.GRAY);
		        }
		        
		        return l;
		    }
		});
		
		
		
		
		//Funcionamiento del arbol
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
		    @Override
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();

		        if (nodoSeleccionado != null) {
		            String nodo = nodoSeleccionado.toString();

		            if (nodo.equals("Comedor")) {
		                getContentPane().add(scrollComedor, BorderLayout.CENTER);
		                getContentPane().remove(scrollA);
		            	getContentPane().remove(scrollB);
		            	getContentPane().remove(scrollC);
		            	
		            	 tablaA.clearSelection();
			        	 tablaB.clearSelection();
			        	 tablaC.clearSelection();
			        	 tablaComedor.clearSelection();
		            	
		            } else if (nodo.equals("Planta A")) {
		            	getContentPane().remove(scrollComedor);
		            	getContentPane().remove(scrollB);
		            	getContentPane().remove(scrollC);
		                getContentPane().add(scrollA, BorderLayout.CENTER);
		                
		                tablaA.clearSelection();
			        	 tablaB.clearSelection();
			        	 tablaC.clearSelection();
			        	 tablaComedor.clearSelection();

		            } else if (nodo.equals("Planta B")) {
		            	getContentPane().remove(scrollA);
		            	getContentPane().remove(scrollC);
		            	getContentPane().remove(scrollComedor);
		                getContentPane().add(scrollB, BorderLayout.CENTER);
		                
		                tablaA.clearSelection();
			        	 tablaB.clearSelection();
			        	 tablaC.clearSelection();
			        	 tablaComedor.clearSelection();

		            } else if (nodo.equals("Planta C")) {
		            	getContentPane().remove(scrollA);
		            	getContentPane().remove(scrollB);
		            	getContentPane().remove(scrollComedor);
		                getContentPane().add(scrollC, BorderLayout.CENTER);
		                
		                tablaA.clearSelection();
			        	 tablaB.clearSelection();
			        	 tablaC.clearSelection();
			        	 tablaComedor.clearSelection();

		            }
		            pack();
		    		setLocationRelativeTo(null);

		        }
		       
		    }
		    
		});
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}	
	
		
	
	
}
