package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import domain.BDexception;
import domain.BDmanager;
import domain.Datos;
import domain.Reserva;
import domain.Tarea;

public class VentanaDeCarga extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	
	
	private static final long serialVersionUID = 1L;
	protected JButton botonCerrar, botonEntrar, botonAyuda, botonValoracion;
	protected JPanel panelAbajo, panelCentro, panelFoto;
	protected JProgressBar progressBar;
	protected JLabel lblImagenHotel;
	protected String seleccion; //true si es desde el fichero false si es desde los datos de prueba
	protected boolean flagAdvertencia = true;
	protected BDmanager bdManager = new BDmanager();
	
	public VentanaDeCarga(Datos datos){
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
		
		ImageIcon h = new ImageIcon("src/Imagenes/h.png");
		setIconImage(h.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("HOTEL");
		setLocationRelativeTo(null);
		
		JComboBox<String> combo = new JComboBox<>();
		combo.addItem("Fichero de datos");
		combo.addItem("Datos de prueba");
		combo.addItem("Base de datos");
		
		try {
			bdManager.connect("bd/database.db");
		} catch (BDexception e1) {
			e1.printStackTrace();
		}
		
		botonCerrar = new JButton("CERRAR");
		botonEntrar = new JButton("ENTRAR");
		
		//Añadido
		botonAyuda = new JButton("Necesitas Ayuda?");
		botonAyuda.setForeground(Color.GREEN);
		botonAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInfo();
			}
		});
		botonValoracion = new JButton("Valoranos");
		botonValoracion.setForeground(Color.YELLOW);
		botonValoracion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaValoracion();
			}
		});
		
		panelFoto = new JPanel();
		panelFoto.setLayout(new FlowLayout(FlowLayout.CENTER));
		ImageIcon imHotel = new ImageIcon("src/Imagenes/hotel.jpeg");
		Image imagen = imHotel.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(325, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
		lblImagenHotel = new JLabel(imagenRedimensionadaIcon);
        panelFoto.add(lblImagenHotel);
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonEntrar);
		panelAbajo.add(botonAyuda);
		panelAbajo.add(botonValoracion);
		
		progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Muestra el porcentaje en la barra
        panelCentro.add(progressBar);
        
        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        getContentPane().add(panelFoto, BorderLayout.NORTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
			 try {
					bdManager.disconnect();
				} catch (BDexception ex) {
					logger.log(Level.SEVERE, "Error desconectando la BD");
					ex.printStackTrace();
				}
			logger.info("Se cierra la ventana");
		});
		
		botonEntrar.addActionListener((e) -> {
            botonEntrar.setEnabled(false); // Desactivar el botón mientras se carga
            progressBar.setValue(0);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(40);
                        progressBar.setValue(i);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    botonEntrar.setEnabled(true); // Habilitar el botón nuevamente
                    SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							if(seleccion=="Fichero de datos") {
							//	datos.inicializarDatos();
								datos.cargarDatos();
								datos.cargarDatos();
								int idR =0;
								for (Reserva r : datos.getListaReservas()) {
									if(r.getId()>idR) {
										idR= r.getId();
									}
								}
								Reserva.setNumId(idR);
								int idT =0;
								for (Tarea t : datos.getListaTareas()) {
									if(t.getId()>idT) {
										idT= t.getId();
									}
								}
								Tarea.setNumId(idT);
								try {
									bdManager.disconnect();
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error desconectando la BD");
									e.printStackTrace();
								}
							}else if(seleccion =="Datos de prueba"){
								datos.inicializarDatos();
								try {
									bdManager.disconnect();
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error desconectando la BD");
									e.printStackTrace();
								}
							}else {
								//Conectar con la base de datos
								
								try {
									
									bdManager.rellenarDatos(datos);
								} catch (BDexception e) {
									logger.log(Level.SEVERE, "Error rellenando datos a la BD");
									e.printStackTrace();
								}
							}
							new VentanaSeleccion(datos,seleccion,bdManager);
							logger.info("Se carga la Ventana de seleccion");		
						}
					});
           
                    dispose();
                   
                }
            };

            worker.execute();
        });

		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 try {
						bdManager.disconnect();
					} catch (BDexception ex) {
						logger.log(Level.SEVERE, "Error desconectando la BD");
						ex.printStackTrace();
					}
			}
			
		});
		setVisible(true);
		setLocationRelativeTo(null);
		ImageIcon imagenDatos = new ImageIcon("src/Imagenes/imagen_datos.png");
		Image imagenDatosEscala = imagenDatos.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH);
		combo.addActionListener((e)->{
			if(combo.getSelectedItem()=="Datos de prueba"&&flagAdvertencia) {
				JOptionPane.showMessageDialog(this, "ADVERTENCIA: LOS DATOS DE PRUEBA "
						+ "NO SE GUARDARÁN \n                  DESPUES DE USAR LA APLICACIÓN");
				flagAdvertencia = false;		
			}
		});
		int seleccionDatos = JOptionPane.showConfirmDialog(this,combo,"Elija el origen de los datos", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE,new ImageIcon(imagenDatosEscala));
		if (seleccionDatos==JOptionPane.YES_OPTION) {
			seleccion =(String) combo.getSelectedItem();
		}
		logger.log(Level.INFO, "Ventana de carga cargada");
	}
	

}
