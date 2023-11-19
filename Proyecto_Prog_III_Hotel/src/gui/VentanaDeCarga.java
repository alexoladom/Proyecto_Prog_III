package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import domain.Datos;

public class VentanaDeCarga extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected JButton botonCerrar, botonEntrar;
	protected JPanel panelAbajo, panelCentro, panelFoto;
	protected JProgressBar progressBar;
	protected JLabel lblImagenHotel;
	
	public VentanaDeCarga(Datos datos){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("Aplicación de Gestion para un Hotel");
		
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
							new VentanaSeleccion(datos);
							
						}
					});
                    
                    dispose();
                }
            };

            worker.execute();
        });

		setVisible(true);
	}
	

}
