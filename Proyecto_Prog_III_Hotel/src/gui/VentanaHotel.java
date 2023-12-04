package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import domain.Cliente;
import domain.Datos;
import domain.Habitacion;
import domain.HabitacionSimple;
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
	
	public VentanaHotel(Datos datos,Reserva reserva, Cliente cliente) {
		ImageIcon h = new ImageIcon("src/Imagenes/h.png");
		setIconImage(h.getImage());
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
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
				switch(columnIndex) {
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
					    h.setOcupado(ocupado);
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
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(1).get(rowIndex);
					    h.setOcupado(ocupado);
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
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
				if (rowIndex!=-1) {
					  Habitacion h = datos.getMapaHabitaciones().get(2).get(rowIndex);
					    h.setOcupado(ocupado);
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
					case 1: return c.isOcupado(); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
			   if(rowIndex!=-1) {
				   Mesa c = datos.getListaComedor().get(rowIndex);
				    c.setOcupado(ocupado);
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
		modeloLista.addElement(new HabitacionSimple());
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
		        MiModeloComedor modeloComedor = (MiModeloComedor) tablaComedor.getModel();
		        modeloComedor.actualizarEstado(tablaComedor.getSelectedRow(), true);
		        tablaComedor.repaint();
		        //JList
		        if(tablaA.getSelectedRow()!=-1) {
			        reserva.getListaHabitacionesReservadas().add(datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow()));
			        if(modeloLista.contains(datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow()))) {
			        	System.err.println("La reserva ya esta añadida en la Planta A");
			        }else {
			        	modeloLista.addElement(datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow()));
			        }
		        }
		        if(tablaB.getSelectedRow()!=-1) {
			        reserva.getListaHabitacionesReservadas().add(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
			        if(modeloLista.contains(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()))) {
			        	System.err.println("La reserva ya esta añadida en la Planta B");
			        }else {
			        	modeloLista.addElement(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
			        }
		        }
		        if(tablaC.getSelectedRow()!=-1) {
			        reserva.getListaHabitacionesReservadas().add(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
			        if(modeloLista.contains(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()))) {
			        	System.err.println("La reserva ya esta añadida en la Planta C");
			        }else {
			        	modeloLista.addElement(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
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
			        reserva.getListaHabitacionesReservadas().remove(datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow()));
			        modeloLista.removeElement(datos.getMapaHabitaciones().get(0).get(tablaA.getSelectedRow()));
		        }
		        if(tablaB.getSelectedRow()!=-1) {
			        reserva.getListaHabitacionesReservadas().remove(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
			        modeloLista.removeElement(datos.getMapaHabitaciones().get(1).get(tablaB.getSelectedRow()));
		        }
		        if(tablaC.getSelectedRow()!=-1) {
			        reserva.getListaHabitacionesReservadas().remove(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
			        modeloLista.removeElement(datos.getMapaHabitaciones().get(2).get(tablaC.getSelectedRow()));
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
				JLabel l = new JLabel();
		        
		        l.setOpaque(true);
		        l.setText(value.toString());
		        
		        MiModeloA modelo = (MiModeloA) table.getModel();
		        boolean ocupado = Boolean.parseBoolean(modelo.getValueAt(row, 0).toString());
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (ocupado) {
		            l.setBackground(Color.RED);
		        } else {
		            l.setBackground(Color.GREEN);
		        }
		        
		        return l;
		    }
		});
		tablaB.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel();
		        
		        l.setOpaque(true);
		        l.setText(value.toString());
		        
		        MiModeloB modelo = (MiModeloB) table.getModel();
		        boolean ocupado = Boolean.parseBoolean(modelo.getValueAt(row, 0).toString());
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (ocupado) {
		            l.setBackground(Color.RED);
		        } else {
		            l.setBackground(Color.GREEN);
		        }
		        
		        return l;
		    }
		});
		tablaC.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel();
		        
		        l.setOpaque(true);
		        l.setText(value.toString());
		        
		        MiModeloC modelo = (MiModeloC) table.getModel();
		        boolean ocupado = Boolean.parseBoolean(modelo.getValueAt(row, 0).toString());
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (ocupado) {
		            l.setBackground(Color.RED);
		        } else {
		            l.setBackground(Color.GREEN);
		        }
		        
		        return l;
		    }
		});
		tablaComedor.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel();
		        
		        l.setOpaque(true);
		        l.setText(value.toString());
		        
		        MiModeloComedor modelo = (MiModeloComedor) table.getModel();
		        boolean ocupado = Boolean.parseBoolean(modelo.getValueAt(row, 1).toString());
		        if (isSelected) {
		        	l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        }
		        if (ocupado) {
		            l.setBackground(Color.RED);
		        } else {
		            l.setBackground(Color.GREEN);
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
		            } else if (nodo.equals("Planta A")) {
		            	getContentPane().remove(scrollComedor);
		            	getContentPane().remove(scrollB);
		            	getContentPane().remove(scrollC);
		                getContentPane().add(scrollA, BorderLayout.CENTER);
		            } else if (nodo.equals("Planta B")) {
		            	getContentPane().remove(scrollA);
		            	getContentPane().remove(scrollC);
		            	getContentPane().remove(scrollComedor);
		                getContentPane().add(scrollB, BorderLayout.CENTER);
		            } else if (nodo.equals("Planta C")) {
		            	getContentPane().remove(scrollA);
		            	getContentPane().remove(scrollB);
		            	getContentPane().remove(scrollComedor);
		                getContentPane().add(scrollC, BorderLayout.CENTER);
		            }
		            pack();
		        }
		    }
		});
		pack();
		setVisible(true);
	}	
	
		
	
	
}
