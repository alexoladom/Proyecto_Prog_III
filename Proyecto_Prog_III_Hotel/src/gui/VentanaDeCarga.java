package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.logging.Logger;

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

import domain.Datos;

public class VentanaDeCarga extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	
	private static final long serialVersionUID = 1L;
	protected JButton botonCerrar, botonEntrar;
	protected JPanel panelAbajo, panelCentro, panelFoto;
	protected JProgressBar progressBar;
	protected JLabel lblImagenHotel;
	protected boolean seleccion = true; //true si es desde el fichero false si es desde los datos de prueba
	protected boolean flagAdvertencia = true;
	
	public VentanaDeCarga(Datos datos){
		ImageIcon h = new ImageIcon("src/Imagenes/h.png");
		setIconImage(h.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("HOTEL");
		JComboBox<String> combo = new JComboBox<>();
		combo.addItem("Fichero de datos");
		combo.addItem("Datos de prueba");
		
		
		botonCerrar = new JButton("CERRAR");
		botonEntrar = new JButton("ENTRAR");
		
		panelFoto = new JPanel();
		panelFoto.setLayout(new FlowLayout(FlowLayout.CENTER));
		ImageIcon imHotel = new ImageIcon("src\\Imagenes\\hotel.jpeg");
		Image imagen = imHotel.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(325, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
		lblImagenHotel = new JLabel(imagenRedimensionadaIcon);
        panelFoto.add(lblImagenHotel);
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonEntrar);
		
		progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Muestra el porcentaje en la barra
        panelCentro.add(progressBar);
        
        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        getContentPane().add(panelFoto, BorderLayout.NORTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
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
							if(seleccion) {
								datos.cargarDatos();
							}else {
								datos.inicializarDatos();
							}
							new VentanaSeleccion(datos,seleccion);
							logger.info("Se carga la Ventana de seleccion");
							
						}
					});
                    
                    dispose();
                }
            };

            worker.execute();
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
			if(combo.getSelectedItem()=="Fichero de datos") {
				seleccion=true;
			}else {
				seleccion= false;
			}
		}
	}
	

}
