package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.prefs.Preferences;

public class VentanaValoracion extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel tableModel;
    
    private Logger logger = java.util.logging.Logger.getLogger("Logger");

    @SuppressWarnings("serial")
	public VentanaValoracion() {
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
    	
    	logger.info("Se abre la ventana");
        setTitle("Valoraciones");
        setSize(680, 420);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Valoración");

        cargarValoraciones();

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnBien = new JButton("Bien");
        btnBien.setForeground(Color.GREEN);
        btnBien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarValoracion("Bien");
            }
        });

        JButton btnRegular = new JButton("Regular");
        btnRegular.setForeground(Color.YELLOW);
        btnRegular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarValoracion("Regular");
            }
        });

        JButton btnMal = new JButton("Mal");
        btnMal.setForeground(Color.RED);
        btnMal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarValoracion("Mal");
            }
        });

        JButton btnAgregar = new JButton("Agregar Valoración libre");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaValoracion = JOptionPane.showInputDialog("Introduce una valoración:");
                if (nuevaValoracion != null && !nuevaValoracion.isEmpty()) {
                    agregarValoracion(nuevaValoracion);
                }
            }
        });

        JButton btnBorrar = new JButton("Borrar Valoración");
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarValoracion();
            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarValoraciones();
                dispose();
                logger.info("Se cierra la ventana");
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBien);
        panelBotones.add(btnRegular);
        panelBotones.add(btnMal);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnCerrar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    private void agregarValoracion(String valoracion) {
        tableModel.addRow(new Object[]{valoracion});
    }

    private void borrarValoracion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
    }

    private void guardarValoraciones() {
        Preferences prefs = Preferences.userNodeForPackage(VentanaValoracion.class);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String valoracion = (String) tableModel.getValueAt(i, 0);
            prefs.put("valoracion_" + i, valoracion);
        }

        prefs.putInt("cantidad_valoraciones", tableModel.getRowCount());
    }

    private void cargarValoraciones() {
        Preferences prefs = Preferences.userNodeForPackage(VentanaValoracion.class);

        int cantidadValoraciones = prefs.getInt("cantidad_valoraciones", 0);

        for (int i = 0; i < cantidadValoraciones; i++) {
            String valoracion = prefs.get("valoracion_" + i, "");
            agregarValoracion(valoracion);
        }
    }
}