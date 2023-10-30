package Ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Clases.Datos;
import Clases.Parking;

public class VentanaParking extends JFrame {
	protected JButton botonReserva, botonCancelarReserva;
	protected JPanel pBotones;
	protected JLabel lblParking;
	protected Datos datos;
	protected boolean reserva = false;

	// Para mostrarle al usuario las plazas libres podemos hacerlo
	// por el numero de plazas libres o mostrandole un menu

	public VentanaParking(Datos datos) {
		
		this.datos=datos;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		botonReserva = new JButton("Reserva");
		botonCancelarReserva = new JButton("Cancelar Reserva");

		lblParking = new JLabel("Parking");

		pBotones = new JPanel();

		pBotones.add(lblParking);
		pBotones.add(botonReserva);
		pBotones.add(botonCancelarReserva);

		botonReserva.addActionListener((e) -> {
			if (reserva == false) {
				if (datos.getParking().isCompleto()) {
					// el parking esta lleno
				} else {
					// el parking esta libre y se haria la reserva
					// se ocuparia una de las 100 plazas quedando 99
					// mirar si solo dejamos hacer solo una reserva
					// por ahora lo he hecho solo permitiendo una reserva
					reserva = true;
				}
			}
		});
		botonCancelarReserva.addActionListener((e) -> {
			if (reserva == true) {
				if (datos.getParking().getNumPlazasDisponibles() < 100) {
					// hay que sumarle 1 solo si antes se habia hecho una reserva
					// hay que mirar si permitimos hacer mas de una reserva, se puede hacer con un
					// bool de reserva
					reserva = false;// volvemos a permitir que se haga una reserva
				} else {
					// no hay ninguna plaza ocupada
				}
			} else {
				// no se ha hecho ninguna reserva
			}
		});
		setVisible(true);
	}

}
