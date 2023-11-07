package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import Clases.Datos;
import Clases.Parking;

public class VentanaParking extends JFrame {
	protected JButton botonReserva, botonTerminarReserva;
	protected JPanel pBotones, pTabla;
	protected JTable tabla;
	protected Datos datos;
	protected JDatePicker datePicker;
	protected boolean reserva = false;

	public VentanaParking(Datos datos) {
		
		this.datos=datos;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,800);
		setTitle("Parking");
		//creo mi modelo de tabla
        
        class MiModelo extends AbstractTableModel{

			private static final long serialVersionUID = 1L;
			

			@Override
			public int getRowCount() {
				return 6;
			}	
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (columnIndex!=0&&rowIndex!=0) {
					datos.getParking().getParking()[rowIndex-1][columnIndex-1]=(boolean)aValue;
				}
				fireTableCellUpdated(rowIndex, columnIndex);
				tabla.repaint();
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnIndex>0 && rowIndex>0;
			}

			@Override
			public Object getValueAt(int row, int column) {
				switch(column) {
				case 0: 
					return row ;
				case 1,2,3,4,5:
					switch(row){
					case 0:
						return column;
					case 1,2,3,4,5:
						return datos.getParking().getParking()[row-1][column-1];

					}
				}
				return null;
			}
        }
        
        class MiRenderer extends JLabel implements TableCellRenderer{
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				setText(value.toString());
				setOpaque(true);
				if(row!=0) {
					if((boolean) value== true) {
						setBackground(Color.red);
					}else if((boolean) value == false) {
						setBackground(Color.green);
					
					}
				}else {
					setBackground(Color.LIGHT_GRAY);
				}
				return this;
			}  	
        }
        
        class MiCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{

			private static final long serialVersionUID = 1L;
			private boolean ocupado = false;
        	private JCheckBox box ;
			
        	@Override
			public Object getCellEditorValue() {
				return ocupado;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ocupado == true) {
					ocupado = false;
				}else {
					ocupado=true;
				}
				fireEditingStopped();
			}

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				box = new JCheckBox();
				box.addActionListener(this);
				return box;
			}
        	
        }
        
        MiModelo modelo = new MiModelo();
		botonReserva = new JButton("Empezar Reserva");
		botonTerminarReserva = new JButton("Terminar Reserva");
		datePicker = new JDatePicker();
		tabla = new JTable(modelo);
		tabla.getColumnModel().getColumn(1).setCellRenderer(new MiRenderer());
		tabla.getColumnModel().getColumn(2).setCellRenderer(new MiRenderer());
		tabla.getColumnModel().getColumn(3).setCellRenderer(new MiRenderer());
		tabla.getColumnModel().getColumn(4).setCellRenderer(new MiRenderer());
		tabla.getColumnModel().getColumn(5).setCellRenderer(new MiRenderer());
		
		tabla.getColumnModel().getColumn(1).setCellEditor(new MiCellEditor());
		tabla.getColumnModel().getColumn(2).setCellEditor(new MiCellEditor());
		tabla.getColumnModel().getColumn(3).setCellEditor(new MiCellEditor());
		tabla.getColumnModel().getColumn(4).setCellEditor(new MiCellEditor());
		tabla.getColumnModel().getColumn(5).setCellEditor(new MiCellEditor());
		


		pBotones = new JPanel();
		pTabla = new JPanel();
		pTabla.setLayout(new BorderLayout());

		pBotones.add(botonReserva);
		pBotones.add(datePicker);
		pBotones.add(botonTerminarReserva);
		
		pTabla.add(tabla,BorderLayout.NORTH);
		
		add(pBotones,BorderLayout.NORTH);
		add(pTabla, BorderLayout.CENTER);
		tabla.repaint();
		
		
		//Configuracion del datepicker
		
		datePicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
				ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
		        LocalDate fechaLocal = zonedDateTime.toLocalDate();
				if (fechaLocal.isBefore(LocalDate.now())|| fechaLocal.isAfter(LocalDate.now().plusWeeks(1))) {
					datePicker.getModel().setValue(null);
				}
			}
		});
        datePicker.addDateSelectionConstraint(new DateSelectionConstraint() {
			
			@Override
			public boolean isValidSelection(DateModel<?> arg0) {
				
				GregorianCalendar calendar = (GregorianCalendar) arg0.getValue();
		        if(calendar!=null) {
		        	ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
			        LocalDate fechaLocal = zonedDateTime.toLocalDate();
		        	if(fechaLocal.isBefore(LocalDate.now())||fechaLocal.isAfter(LocalDate.now().plusWeeks(1))) {
			        	 return false;
			         }else {
			        	 return true;
			         }
		        }else{
		        	return true;
		        }		         
			}
		});
        pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		
		new VentanaParking(new Datos());
	}

}
