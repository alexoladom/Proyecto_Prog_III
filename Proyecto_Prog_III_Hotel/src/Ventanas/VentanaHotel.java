package Ventanas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
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
	private DefaultTableModel modeloComedor, modeloA, modeloB, modeloC;
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
		Object [] titulos = {"nº Habitacion","Estado","Esta limpia"};
		modeloA = new DefaultTableModel();
		modeloA.setColumnIdentifiers(titulos);
		tablaA = new JTable(modeloA);
		tablaA.setRowHeight(50);
		tablaA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollA = new JScrollPane(tablaA);
		
		//Creación de la tabla B
		modeloB = new DefaultTableModel();
		modeloB.setColumnIdentifiers(titulos);
		tablaB = new JTable(modeloB);
		tablaB.setRowHeight(50);
		tablaB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollB = new JScrollPane(tablaB);
		
		//Creación de la tabla C
		modeloC = new DefaultTableModel();
		modeloC.setColumnIdentifiers(titulos);
		tablaC = new JTable(modeloC);
		tablaC.setRowHeight(50);
		tablaC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollC = new JScrollPane(tablaC);
		
		//Creación de la tabla Comedor
		Object [] titulos1 = {"nº Mesas","Estado"};
		modeloComedor = new DefaultTableModel();
		modeloComedor.setColumnIdentifiers(titulos1);
		tablaComedor = new JTable(modeloComedor);
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
