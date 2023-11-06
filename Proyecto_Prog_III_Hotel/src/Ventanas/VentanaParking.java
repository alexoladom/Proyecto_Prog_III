package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.DateSelectionConstraint;

import Clases.Cliente;
import Clases.Datos;
import Clases.Parking;
import Clases.PlazaParking;
import Clases.Reserva;

public class VentanaParking extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JButton botonReserva, botonTerminarReserva;
	protected JPanel pBotones, pTabla;
	protected JTable tabla;
	protected Datos datos;
	protected JDatePicker datePicker;

	public VentanaParking(Datos datos, Reserva reserva, Cliente cliente) {
		
		this.datos=datos;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,220);
		setTitle("Parking");
		setLocationRelativeTo(null);
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
					GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
					ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
			        LocalDate fechaLocal = zonedDateTime.toLocalDate();
			        PlazaParking [][] distribucion =datos.getMapaParkingPorFecha().get(fechaLocal).getDistribucion();
					if ((boolean) aValue==true) {
						distribucion[rowIndex-1][columnIndex-1].setOcupada((boolean) aValue);
						reserva.getListaPlazasParking().add(distribucion[rowIndex-1][columnIndex-1]);
					}else if((boolean) aValue== false&& cliente.getListaReservasCliente().contains(reserva)){
						distribucion[rowIndex-1][columnIndex-1].setOcupada((boolean)aValue);
						reserva.getListaPlazasParking().remove(distribucion[rowIndex-1][columnIndex-1]);
					}
					
					
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
				boolean editable = false;
				if(rowIndex!=0) {
					if (columnIndex !=0) {
						GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
						if(calendar != null) {
							ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
					        LocalDate fechaLocal = zonedDateTime.toLocalDate();
					        Parking parking = datos.getMapaParkingPorFecha().get(fechaLocal);
					        editable =parking.comprobarPlazaDisponible(cliente, parking.getDistribucion()[rowIndex-1][columnIndex-1]);
						}else {
							editable=false;
						}
					}
				}
				return editable;
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
						GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
						if(calendar!=null) {
							ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
							LocalDate fechaLocal = zonedDateTime.toLocalDate();
							return datos.getMapaParkingPorFecha().get(fechaLocal).getDistribucion()[row-1][column-1].isOcupada();
						}else {
							return datos.getMapaParkingPorFecha().get(LocalDate.now()).getDistribucion()[row-1][column-1].isOcupada();
						}
					}
				}
				return null;
			}
        }      
      
        // Renderer de las celdas
        
        class MiRenderer extends JLabel implements TableCellRenderer{
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				setOpaque(true);
				
				
				
				if(row!=0) {
					if((boolean) value== true) {
							GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
							if(calendar!=null) {
								ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
								LocalDate fechaLocal = zonedDateTime.toLocalDate();
								Parking parking = datos.getMapaParkingPorFecha().get(fechaLocal);
								if (parking.comprobarPlazaDisponible(cliente, parking.getDistribucion()[row-1][column-1])) {
									setText("OCUPADO");
									setBackground(Color.red);
								}else {
									setText("NO DISPONIBLE");
									setBackground(Color.LIGHT_GRAY);
								}
							}
					}else if((boolean) value == false) {
						setText("LIBRE");
						setBackground(Color.green);
					}
				}else {
					setText(value.toString());
					setBackground(Color.LIGHT_GRAY);
				}
				return this;
			}  	
        }
        
        //Editor en forma de checkbox
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
				GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
				if(calendar!=null) {
					if (ocupado == true) {
						ocupado = false;
					}else {
						ocupado=true;
					}
					fireEditingStopped();
				}else {
					JOptionPane.showMessageDialog(tabla, "Primero seleccione una fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
					this.fireEditingCanceled();
				}
				
				
			}

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				box = new JCheckBox();
				box.addActionListener(this);
				return box;
			}
        	
        }
        
        // Creacion de la tabla y asignacion del modelo, renderer y editor
        
        MiModelo modelo = new MiModelo();
		botonReserva = new JButton("Empezar Reserva");
		botonReserva.setEnabled(false);
		botonReserva.setToolTipText("Haz click aqui para empezar a editar la tabla");
		botonTerminarReserva = new JButton("Terminar Reserva");
		botonTerminarReserva.setToolTipText("Haz click aqui para terminar de editar la tabla del parking");
		datePicker = new JDatePicker();
		LocalDate fechaDeHoy = LocalDate.now();
		datePicker.getModel().setDate(fechaDeHoy.getYear(), fechaDeHoy.getMonthValue(), fechaDeHoy.getDayOfMonth());
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
		pBotones.setBackground(Color.BLUE);
		pTabla = new JPanel();
		pTabla.setVisible(false);
		pTabla.setLayout(new BorderLayout());
		tabla.setEnabled(false);
		Border lineBorder = BorderFactory.createLineBorder(Color.BLUE);
		Border titledBorder = BorderFactory.createTitledBorder(lineBorder, "Distribucion del parking");
		pTabla.setBorder(titledBorder);
		
		pBotones.add(botonReserva);
		pBotones.add(datePicker);
		pBotones.add(botonTerminarReserva);
		
		ImageIcon imagenPregunta = new ImageIcon("src/Imagenes/preguntaAzul.png");
		Image pregunta = imagenPregunta.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionada = new ImageIcon(pregunta);
		JButton botonAyuda = new JButton(imagenRedimensionada);
		botonAyuda.setBackground(Color.red);
		botonAyuda.setBorder(BorderFactory.createEmptyBorder());
		botonAyuda.setOpaque(false);
		botonAyuda.setToolTipText("Ayuda");
		botonAyuda.addActionListener((e) -> {
			JOptionPane.showMessageDialog(pTabla, "Para empezar la reserva, seleccione una fecha y pulse 'Empezar reserva', para finalizar, "
				+ "pulse el boton 'Terminar reserva'. Las plazas en gris estan ocupadas por otros clientes. ");
		});
		pBotones.add(botonAyuda);
		
		botonReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabla.setEnabled(true);
				botonReserva.setEnabled(false);
			}
		});
		botonTerminarReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (PlazaParking string : reserva.getListaPlazasParking()) {
					System.out.println(string);
				}
				datos.guardarDatos();
				dispose();
			}
		});
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
				pTabla.setVisible(true);
				botonReserva.setEnabled(true);
				tabla.repaint();
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
        addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				datos.cargarDatos();
				tabla.repaint();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				datos.guardarDatos();
				
			}
		});
        ImageIcon icono = new ImageIcon("src/Imagenes/parkingIcono.png");
        setIconImage(icono.getImage());
		setVisible(true);
	}
	
	

}
