package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Clases.Datos;

public class VentanaHotel extends JFrame{
	protected JButton botonReserva, botonTerminarReserva, botonCerrar, botonSeleccionar;
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
	
	public VentanaHotel(Datos datos) {
		this.datos=datos;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,800);
		setTitle("Hotel");
		
		//Creo los botones
		botonReserva = new JButton ("Reserva");
		botonTerminarReserva = new JButton ("Acabar reserva");
		botonCerrar = new JButton ("Cerrar");
		botonSeleccionar = new JButton("Seleccionar");
		pBotones = new JPanel();
		pArbol = new JPanel();
		
		//Creación de la tabla A
		String [] titulos = {"nº Habitacion","Estado ocupación","Esta limpia"};
		
		class MiModeloA extends AbstractTableModel{

			@Override
			public int getRowCount() {
				if(listaHabitacionesA == null) {
					return 0;
				}else {
					return listaHabitacionesA.size();
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
				Habitacion h = listaHabitacionesA.get(rowIndex);
				switch(columnIndex) {
					case 0: return h.getNumeroHabitacion(); 
					case 1: return h.getEstadoOcupacion(); 
					case 2: return h.getEstadoLimpieza(); 
					default: return null;
				}
			}
		}
		tablaA = new JTable(new MiModeloA());
		tablaA.setRowHeight(50);
		tablaA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollA = new JScrollPane(tablaA);
		
		//Creación de la tabla B
		class MiModeloB extends AbstractTableModel{

			@Override
			public int getRowCount() {
				if(listaHabitacionesB == null) {
					return 0;
				}else {
					return listaHabitacionesB.size();
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
				Habitacion h = listaHabitacionesB.get(rowIndex);
				switch(columnIndex) {
					case 0: return h.getNumeroHabitacion(); 
					case 1: return h.getEstadoOcupacion(); 
					case 2: return h.getEstadoLimpieza(); 
					default: return null;
				}
			}
		}
		tablaB = new JTable(new MiModeloB());
		tablaB.setRowHeight(50);
		tablaB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollB = new JScrollPane(tablaB);
		
		//Creación de la tabla C
		class MiModeloC extends AbstractTableModel{

			@Override
			public int getRowCount() {
				if(listaHabitacionesC == null) {
					return 0;
				}else {
					return listaHabitacionesC.size();
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
				Habitacion h = listaHabitacionesC.get(rowIndex);
				switch(columnIndex) {
					case 0: return h.getNumeroHabitacion(); 
					case 1: return h.getEstadoOcupacion(); 
					case 2: return h.getEstadoLimpieza(); 
					default: return null;
				}
			}
		}
		tablaC = new JTable(new MiModeloC());
		tablaC.setRowHeight(50);
		tablaC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollC = new JScrollPane(tablaC);
		
		//Creación de la tabla Comedor
		Object [] titulos1 = {"nº Mesas","Estado"};
		class MiModeloComedor extends AbstractTableModel{

			@Override
			public int getRowCount() {
				if(listaComedor == null) {
					return 0;
				}else {
					return listaComedor.size();
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
				Comedor c = listaComedor.get(rowIndex);
				switch(columnIndex) {
					case 0: return c.getNumeroMesa(); 
					case 1: return c.getEstadoOcupacion(); 
					default: return null;
				}
			}
		}
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
		
		getContentPane().add(scrollA, BorderLayout.CENTER);
//		getContentPane().add(scrollB, BorderLayout.CENTER);
//		getContentPane().add(scrollC, BorderLayout.CENTER);
//		getContentPane().add(scrollComedor, BorderLayout.CENTER);
		getContentPane().add(pBotones, BorderLayout.EAST);
		getContentPane().add(pArbol, BorderLayout.WEST);
		
		pBotones.add(botonReserva);
		pBotones.add(botonTerminarReserva);
		pBotones.add(botonCerrar);
		pBotones.add(botonSeleccionar);
		
		//ActionListeners de los botones
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		botonReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaA.setEnabled(true);
				tablaB.setEnabled(true);
				tablaC.setEnabled(true);
				tablaComedor.setEnabled(true);
				botonReserva.setEnabled(false);
			}
		});
//		botonTerminarReserva.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (PlazaHotel string : estado.getListaHabitacionesHotel()) {
//					System.out.println(string);
//				}
//				datos.guardarDatos();
//				dispose();
//			}
//		});
		botonSeleccionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
//		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//			
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//					int row, int column) {
//				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//				boolean libre = modelo.getValueAt(row, 2).toString();//Sin hacer
//				if(libre == true) {
//					c.setForeground(Color.GREEN);
//				}else {
//					c.setForeground(Color.RED);
//				}
//					
//				return c;
//			}
//		});

		pack();
		setVisible(true);
	}	
}
