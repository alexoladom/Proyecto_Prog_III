package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaInfo extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaInfo() {
        setTitle("Ayuda");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        String texto = "Read Me Programacion 3\n"
                + "Proyecto de aplicación de gestión de un Hotel\n"
                + "Nuestra aplicación comienza con una ventana que se activa y te ofrece si ejecutar la aplicación o cerrarla en su lugar junto a otra que se antepone a esta y te da como opción cargar los datos desde ficheros, desde la base de datos o desde los datos de prueba.\n"
                + "Una vez escoges una de estas tres opciones y carga la aplicación te aparecerá de forma automática una nueva ventana donde deberás seleccionar si eres un trabajador perteneciente al Hotel, o si eres uno de sus clientes.\n"
                + "En función de la forma de cargar los datos que vayas escogiendo deberás ir registrándote en las diferentes interfaces que aparecen en las ventanas y se irá guardando tu información en un lado o en otro. \n"
                + "No obstante, si escoges la opción de cargar los datos desde datos de prueba, podrás entrar a la ventana Trabajador sin la necesidad de registrarte introduciendo los datos: 18087363T y el pin: 123, también podrás hacerlo en Cliente con los datos 123456789A y el pin: contraseña123.\n"
                + "Una vez has introducido las claves proporcionadas o te has registrado correctamente con tus datos, podrás acceder al resto de la aplicación, en el caso de la ventana Trabajador solo podrás acceder con los datos proporcionados, ya que no tendría sentido que un cliente se registre como un trabajador.\n"
                + "Una vez accedes a la Ventana Cliente: Podrás observar que aparece otra ventana donde se pueden editar tus datos en el caso de que sea necesario, y guardarlos después mediante otro botón en el caso de que se efectúe algún cambio. Ver tu perfil, pulsando su icono, donde podrás cambiar la foto de perfil, cerrar sesión, o ver tus datos. Podrás ver las reservas que hayas hecho tanto en la ventana Hotel como en la de Parking mediante el botón Ver Reservas, podrás verlas, editarlas y ordenarlas por fecha, precio o pagado. Por último, nos queda el botón Reserva Ahora el cual nos mandará a un apartado donde podemos hacer una reserva seleccionando las fechas que precisemos en ese momento, también podremos elegir si reservar una habitación de hotel, una plaza de Parking o ambas.\n"
                + "Después de todo esto podrás guardar y confirmar la reserva o cancelarla.\n"
                + "En los apartados de reservar una habitación te llevará a una nueva ventana donde aparecerá un árbol que puede desglosarse y elegir ahí la habitación que precises siempre y cuando este libre.\n"
                + "En el apartado de reservar una plaza de Parking te llevará a otra ventana donde deberás elegir primero la fecha en la que deseas reservarla y después podrás escoger tu plaza libremente a través de una tabla siempre y cuando esté libre.\n"
                + "Una vez accedes a la Ventana Trabajador: Podrás ver que aparecen tres opciones, una a través de un icono que funciona igual que en la ventana Cliente donde puedes ver tus datos, poner o cambiar tu foto del perfil o cerrar sesión. Otra con la que puedes acceder a la información de los clientes pulsando el icono con una imagen de tres trabajadores y dos paréntesis, una vez lo hagas te saldrá una tabla con la información de estos, luego también se verá un icono con una imagen de una carpeta con tareas donde podremos hacer click y nos proporcionará una tabla con todas las tareas y por ultimo el botón de la izquierda que lo que haces es ejecutar una tabla con la información de los clientes que funciona igual que la de los trabajadores pero con otros datos.";

        JButton botonVolver = new JButton("Volver Atrás");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(botonVolver);

        String[] columnas = { "Información" };
        Object[][] datos = { { texto } };
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane);

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}