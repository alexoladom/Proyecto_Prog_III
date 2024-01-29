package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;



//Clase para gestionar la base de datos

public class BDmanager {

	private Connection conn = null;
	
	//Metodos para gestionar el almacenamiento y la consulta de los clientes
	
	// Metodo para conectar con el driver y la base de datos
	
	public void connect (String dbPath) throws BDexception{
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new BDexception("Error cargando el driver de la BD", e);
		} catch (SQLException e) {
			throw new BDexception("Error conectando a la BD", e);
		}
	}
	
	//Metodo para desconectar la BD 
	
	public void disconnect() throws BDexception {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new BDexception("Error cerrando la conexión con la BD", e);
		}
	}
	
	//Metodo de Unai modificado que retorna la lista de clientes almacenada en la bd
	
	public List<Cliente> getClientes() throws BDexception {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT DNI, Nombre, Apellido,"
					+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
					+ " FotoPerfil, UltimoLogin FROM clientes");

			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("DNI"));
				cliente.setNombre(rs.getString("Nombre"));
				cliente.setApellido1(rs.getString("Apellido"));
				cliente.setEmail(rs.getString("Email"));
				cliente.setDireccion(rs.getString("Direccion"));
				cliente.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				cliente.setContraseña(rs.getString("Contraseña"));
				cliente.setTelefono(rs.getString("Telefono"));
				cliente.setFotoPerfil(rs.getString("FotoPerfil"));
				cliente.setUltimoLogin(LocalDate.parse(rs.getString("UltimoLogin")));
				List<Reserva> reservas = new ArrayList<>();
				reservas.addAll(getReservasDeCliente(cliente));
				cliente.setListaReservasCliente(reservas);
				clientes.add(cliente);
			}
			
			return clientes;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos los clientes'", e);
		}
	}
	
	//Metodos añadidos a la BD
	public Map<Cliente, List<Habitacion>> getHabitacionesReservadasPorCliente() throws BDexception {
	    Map<Cliente, List<Habitacion>> habitacionesPorCliente = new HashMap<>();
	    try (Statement stmt = conn.createStatement()) {
	        ResultSet rs = stmt.executeQuery("SELECT id, DNICliente, idHabitacion FROM habitaciones_reservadas");

	        while (rs.next()) {
	            Cliente cliente = getCliente(rs.getString("DNICliente"));
	            Habitacion habitacion = getHabitacion(rs.getInt("idHabitacion"));

	            habitacionesPorCliente.computeIfAbsent(cliente, k -> new ArrayList<>()).add(habitacion);
	        }

	        return habitacionesPorCliente;
	    } catch (SQLException e) {
	        throw new BDexception("Error obteniendo habitaciones reservadas por cliente", e);
	    }
	}
	//Metodo para obtener un cliente en concreto
	
	public Cliente getCliente(String dni) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT DNI, Nombre, Apellido,"
				+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
				+ " FotoPerfil, UltimoLogin FROM clientes WHERE DNI = ?")) {
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("DNI"));
				cliente.setApellido1(rs.getString("Apellido"));
				cliente.setEmail(rs.getString("Email"));
				cliente.setDireccion(rs.getString("Direccion"));
				cliente.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				cliente.setContraseña(rs.getString("Contraseña"));
				cliente.setTelefono(rs.getString("Telefono"));
				cliente.setFotoPerfil(rs.getString("FotoPerfil"));
				cliente.setUltimoLogin(LocalDate.parse(rs.getString("UltimoLogin")));
				
				return cliente;
			} else {
				Cliente cliente = new Cliente();
				cliente.setDni("-1");
				return cliente;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo el cliente con DNI " + dni, e);
		}
	}
	
	
	//Metodo para guardar cliente en la base de datos
	
	public void guardarCliente(Cliente cliente) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO clientes (DNI, Nombre, Apellido,"
				+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
				+" FotoPerfil, UltimoLogin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, cliente.getDni());
			stmt.setString(2, cliente.getNombre());
			stmt.setString(3, cliente.getApellido1());
			stmt.setString(4,cliente.getEmail());
			stmt.setString(5, cliente.getDireccion());
			stmt.setString(6, cliente.getfNacimiento().toString());
			stmt.setString(7, cliente.getContraseña());
			stmt.setString(8, cliente.getTelefono());
			stmt.setString(9, cliente.getFotoPerfil());
			stmt.setString(10, cliente.getUltimoLogin().toString());
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar el cliete en la BD", e);
		}
	}
	
	
	//Metodo para actualizar clientes
	
	public void actualizarCliente(Cliente cliente) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE clientes SET Nombre=?, Apellido=?"
				+ ", Email=?, Direccion=?, fNacimiento=?, Contraseña=?, Telefono=?, "
				+ "FotoPerfil=?, UltimoLogin=? WHERE DNI=?")) {
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido1());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getDireccion());
			stmt.setString(5, cliente.getfNacimiento().toString());
			stmt.setString(6, cliente.getContraseña());
			stmt.setString(7, cliente.getTelefono());
			stmt.setString(8, cliente.getFotoPerfil());
			stmt.setString(9, cliente.getUltimoLogin().toString());
			stmt.setString(10, cliente.getDni());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar el usuario en la BD", e);
		}
	}
	
	//Metodo para borrar clientes
	
	public void deleteCliente(Cliente cliente) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM clientes WHERE DNI=?")) {
			stmt.setString(1,cliente.getDni());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar el cliente con DNI " + cliente.getDni(), e);
		}
	}
	
//	
//	
//
//	
//	
//	
//	
//	
//	
//	
//	
	//Metodos para gestionar el almacenamiento o la consulta de las reservas
	
	
	public List<Reserva> getReservas() throws BDexception {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, DNICliente, fInicio,"
					+ " fFinal, estaPagado FROM reservas");

			while(rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setCliente(getCliente(rs.getString("DNICliente")));
				reserva.setFechaInicio(LocalDate.parse(rs.getString("fInicio")));
				reserva.setFechaFinal(LocalDate.parse(rs.getString("fFinal")));
				reserva.setEstaPagado(rs.getBoolean("estaPagado"));
				List<Habitacion> listaHabitaciones =new ArrayList<>();
				listaHabitaciones.addAll(getHabitacionesDeReserva(reserva));
				reserva.setListaHabitacionesReservadas(listaHabitaciones);
				reservas.add(reserva);
			}
			
			return reservas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las reservas'", e);
		}
	}
	
	public List<Reserva> getReservasDeCliente(Cliente cliente) throws BDexception {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, DNICliente, fInicio,"
				+ " fFinal, estaPagado FROM reservas WHERE DNICliente=?")) {
			stmt.setString(1, cliente.getDni());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setCliente(getCliente(rs.getString("DNICliente")));
				reserva.setFechaInicio(LocalDate.parse(rs.getString("fInicio")));
				reserva.setFechaFinal(LocalDate.parse(rs.getString("fFinal")));
				reserva.setEstaPagado(rs.getBoolean("estaPagado"));
				List<Habitacion> listaHabitaciones =new ArrayList<>();
				listaHabitaciones.addAll(getHabitacionesDeReserva(reserva));
				reserva.setListaHabitacionesReservadas(listaHabitaciones);
				reserva.setCliente(cliente);
				reservas.add(reserva);
			}
			
			return reservas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las reservas'", e);
		}
	}
	
	
	
	public Reserva getReserva(int id) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, DNICliente, fInicio,"
				+ " fFinal, estaPagado FROM reservas WHERE id=?")) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setCliente(getCliente(rs.getString("DNICliente")));
				reserva.setFechaInicio(LocalDate.parse(rs.getString("fInicio")));
				reserva.setFechaFinal(LocalDate.parse(rs.getString("fFinal")));
				reserva.setEstaPagado(rs.getBoolean("estaPagado"));
				List<Habitacion> listaHabitaciones =new ArrayList<>();
				listaHabitaciones.addAll(getHabitacionesDeReserva(reserva));
				reserva.setListaHabitacionesReservadas(listaHabitaciones);
				return reserva;
			} else {
				Reserva reserva2 = new Reserva();
				reserva2.setId(-1);
				return reserva2;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo la reserva con el id: " + id, e);
		}
	}
	
	public void guardarReserva(Reserva reserva) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservas (id, DNICliente, fInicio,"
				+ " fFinal, estaPagado) VALUES (?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setInt(1, reserva.getId());
			if(reserva.getCliente()!=null) {
				stmt.setString(2, reserva.getCliente().getDni());
			}else {
				stmt.setString(2, "-1");
			}
			stmt.setString(3, reserva.getFechaInicio().toString());
			stmt.setString(4,reserva.getFechaFinal().toString());
			stmt.setBoolean(5, reserva.isEstaPagado());
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la reserva en la BD", e);
		}
	}
	
	public void deleteReserva(Reserva reserva) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM reservas WHERE id=?")) {
			
			List<Habitacion> listaHabitaciones =reserva.getListaHabitacionesReservadas();
			List<PlazaParking> listaPlazas = reserva.getListaPlazasParking();
			
			
			for (Habitacion habitacion : listaHabitaciones) {
				habitacion.setReserva(null);
				habitacion.setOcupado(false);
				actualizarHabitacion(habitacion);
			}
			for (PlazaParking plazaParking : listaPlazas) {
				plazaParking.setReserva(null);
				plazaParking.setOcupada(false);
				actualizarPlazaparking(plazaParking);
			}
			
			stmt.setInt(1,reserva.getId());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la resera con id " + reserva.getId(), e);
		}
	}
	
	public void actualizarReserva(Reserva reserva) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE reservas SET DNICliente=?, fInicio=?"
				+ ", fFinal=?, estaPagado=? WHERE id=?")) {
			stmt.setString(1, reserva.getCliente().getDni());
			stmt.setString(2, reserva.getFechaInicio().toString());
			stmt.setString(3, reserva.getFechaFinal().toString());
			stmt.setBoolean(4, reserva.isEstaPagado());
			stmt.setInt(5, reserva.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la reserva en la BD", e);
		}
	}
	
//	
//	
//	
//	
//	
//	Metodos para gestionar la consulta o guardado de trabajadores
//	
//	
//	
//	
//	
//	
//	
//	
//	
	
	public List<Trabajador> getTrabajadores() throws BDexception {
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT DNI, Nombre, Apellido,"
					+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
					+ " FotoPerfil, Salario, HorasTrabajadas FROM trabajadores");

			while(rs.next()) {
				Trabajador trabajador = new Trabajador();
				trabajador.setDni(rs.getString("DNI"));
				trabajador.setNombre(rs.getString("Nombre"));
				trabajador.setApellido1(rs.getString("Apellido"));
				trabajador.setEmail(rs.getString("Email"));
				trabajador.setDireccion(rs.getString("Direccion"));
				trabajador.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				trabajador.setContraseña(rs.getString("Contraseña"));
				trabajador.setTelefono(rs.getString("Telefono"));
				trabajador.setFotoPerfil(rs.getString("FotoPerfil"));
				trabajador.setSalario(rs.getDouble("Salario"));
				trabajador.setNumHorasTrabajadas(rs.getInt("HorasTrabajadas"));
				List<Tarea> listaTareas = new ArrayList<>();
				listaTareas.addAll(getTareasTrabajador(trabajador));
				trabajador.setListaTareasHechas(listaTareas);
				trabajadores.add(trabajador);
				
			}
			
			return trabajadores;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos los trabajadores'", e);
		}
	}
	
	
	//Metodo para obtener un trabajador en concreto
	
	public Trabajador getTrabajador(String dni) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT DNI, Nombre, Apellido,"
				+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
				+ " FotoPerfil, Salario, HorasTrabajadas FROM trabajadores WHERE DNI = ?")) {
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Trabajador trabajador = new Trabajador();
				trabajador.setDni(rs.getString("DNI"));
				trabajador.setNombre(rs.getString("Nombre"));
				trabajador.setApellido1(rs.getString("Apellido"));
				trabajador.setEmail(rs.getString("Email"));
				trabajador.setDireccion(rs.getString("Direccion"));
				trabajador.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				trabajador.setContraseña(rs.getString("Contraseña"));
				trabajador.setTelefono(rs.getString("Telefono"));
				trabajador.setFotoPerfil(rs.getString("FotoPerfil"));
				trabajador.setSalario(rs.getDouble("Salario"));
				trabajador.setNumHorasTrabajadas(rs.getInt("HorasTrabajadas"));
				List<Tarea> listaTareas = new ArrayList<>();
				listaTareas.addAll(getTareasTrabajador(trabajador));
				trabajador.setListaTareasHechas(listaTareas);
				
				return trabajador;
			} else {
				Trabajador trabajador = new Trabajador();
				trabajador.setDni("-1");
				return trabajador;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo el trabajador con DNI " + dni, e);
		}
	}
	
	
	//Metodo para guardar trabajador en la base de datos
	
	public void guardarTrabajador(Trabajador trabajador) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO trabajadores (DNI, Nombre, Apellido,"
				+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
				+" FotoPerfil, Salario, HorasTrabajadas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, trabajador.getDni());
			stmt.setString(2, trabajador.getNombre());
			stmt.setString(3, trabajador.getApellido1());
			stmt.setString(4,trabajador.getEmail());
			stmt.setString(5, trabajador.getDireccion());
			stmt.setString(6, trabajador.getfNacimiento().toString());
			stmt.setString(7, trabajador.getContraseña());
			stmt.setString(8, trabajador.getTelefono());
			stmt.setString(9, trabajador.getFotoPerfil());
			stmt.setDouble(10, trabajador.getSalario());
			stmt.setInt(11, trabajador.getNumHorasTrabajadas());
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar el trabajador en la BD", e);
		}
	}
	
	
	//Metodo para actualizar trabajadores
	
	public void actualizarTrabajador(Trabajador trabajador) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE trabajadores SET Nombre=?, Apellido=?"
				+ ", Email=?, Direccion=?, fNacimiento=?, Contraseña=?, Telefono=?, "
				+ "FotoPerfil=?, Salario=?, HorasTrabajadas=? WHERE DNI=?")) {
			stmt.setString(1, trabajador.getNombre());
			stmt.setString(2, trabajador.getApellido1());
			stmt.setString(3, trabajador.getEmail());
			stmt.setString(4, trabajador.getDireccion());
			stmt.setString(5, trabajador.getfNacimiento().toString());
			stmt.setString(6, trabajador.getContraseña());
			stmt.setString(7, trabajador.getTelefono());
			stmt.setString(8, trabajador.getFotoPerfil());
			stmt.setDouble(9, trabajador.getSalario());
			stmt.setInt(10, trabajador.getNumHorasTrabajadas());
			stmt.setString(11, trabajador.getDni());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar el trabajador en la BD", e);
		}
	}
	
	//Metodo para borrar trabajadores
	
	public void deleteTrabajador(Trabajador trabajador) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM trabajadores WHERE DNI=?")) {
			stmt.setString(1,trabajador.getDni());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar el trabajador con DNI " + trabajador.getDni(), e);
		}
	}
	
	
	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	Metodos para gestionar el almacenamiento y consulta de las habitaciones
//	
//	
//	
//	
//	
//	
//	
	
	//Metodos para obtener habitaciones de distinto tipo
	
	public List<Habitacion> getHabitacionesSimples() throws BDexception {
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, ocupado, planta,"
					+ " numero, idReserva FROM habitaciones WHERE tipo='Simple'");
			while(rs.next()) {
				HabitacionSimple habitacion = new HabitacionSimple();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
				habitacion.setReserva(getReserva(rs.getInt("idReserva")));
				habitaciones.add(habitacion);
				
			}
			return habitaciones;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las habitaciones simples'", e);
		}
	}
	
	public List<Habitacion> getHabitacionesDobles() throws BDexception {
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, ocupado, planta,"
					+ " numero, idReserva FROM habitaciones WHERE tipo='Doble'");
			while(rs.next()) {
				HabitacionDoble habitacion = new HabitacionDoble();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
				habitacion.setReserva(getReserva(rs.getInt("idReserva")));
				habitaciones.add(habitacion);
				
			}
			return habitaciones;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las habitaciones dobles'", e);
		}
	}
	public List<Habitacion> getHabitacionesSuites() throws BDexception {
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, ocupado, planta,"
					+ " numero, idReserva FROM habitaciones WHERE tipo='Suite'");
			while(rs.next()) {
				HabitacionSuite habitacion = new HabitacionSuite();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
				habitacion.setReserva(getReserva(rs.getInt("idReserva")));
				habitaciones.add(habitacion);
				
			}
			return habitaciones;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las habitaciones suites", e);
		}
	}
	public List<Habitacion> getHabitacionesDeReserva(Reserva reserva) throws BDexception {
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, ocupado, planta,"
				+ " numero, idReserva FROM habitaciones WHERE idReserva=?")){
			stmt.setInt(1, reserva.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HabitacionSuite habitacion = new HabitacionSuite();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
				habitacion.setReserva(reserva);
				habitaciones.add(habitacion);
				
			}
			return habitaciones;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las habitaciones suites", e);
		}
	}
	
	
	
	public List<Habitacion> getHabitaciones(){
		List<Habitacion> lista = new ArrayList<>();
		
		try {
			lista.addAll(getHabitacionesSimples());
			lista.addAll(getHabitacionesDobles());
			lista.addAll(getHabitacionesSuites());
		} catch (BDexception e) {
			System.err.println("Error en la funcion getHabitaicones (no se pudo obtener alguna de las tres listas");
			e.printStackTrace();
		}
		
		
		return lista;
	}
	
	//Metodo para obtener una habitacion en concreto
	
	public Habitacion getHabitacion(int id) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, ocupado, planta,"
				+ " numero, tipo, idReserva FROM habitaciones WHERE id = ?")) {
			stmt.setInt(1, id);
			Habitacion habitacion;
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				if(rs.getString("tipo")=="Simple") {
					habitacion = new HabitacionSimple();
				}else if(rs.getString("tipo")=="Doble") {
					habitacion = new HabitacionDoble();
				}else {
					habitacion = new HabitacionSuite();
				}
				
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
				if(rs.getInt("idReserva")==-1) {
					Reserva reserva = new Reserva();
					reserva.setId(-1);
					habitacion.setReserva(reserva);
				}else {
					habitacion.setReserva(getReserva(rs.getInt("idReserva")));
				}
				
				
				
				return habitacion;
			} else {
				habitacion = new HabitacionSimple();
				habitacion.setId(-1);
				return habitacion;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo la habitacion con id " + id, e);
		}
	}
	
	
	//Metodo para guardar habitaciones en la base de datos
	
	public void guardarHabitacion(Habitacion habitacion) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO habitaciones (id, ocupado, planta,"
				+ " numero, tipo, idReserva) VALUES (?, ?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setInt(1, habitacion.getId());
			stmt.setBoolean(2, habitacion.isOcupado());
			stmt.setInt(3, habitacion.getPlanta());
			stmt.setInt(4,habitacion.getNumero());
			
			if(habitacion instanceof HabitacionSimple) {
				stmt.setString(5, "Simple");
			}else if(habitacion instanceof HabitacionDoble) {
				stmt.setString(5, "Doble");
			}else {
				stmt.setString(5, "Suite");
			}
			if(habitacion.getReserva()!=null) {
				stmt.setInt(6, habitacion.getReserva().getId());
			}else {
				stmt.setInt(6, -1);
			}
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la habitacion en la BD", e);
		}
	}
	
	
	//Metodo para actualizar habitaciones
	
	public void actualizarHabitacion(Habitacion habitacion) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE habitaciones SET ocupado=?, planta=?"
				+ ", numero=?, tipo=?, idReserva=? WHERE id=?")) {
			stmt.setBoolean(1, habitacion.isOcupado());
			stmt.setInt(2, habitacion.getPlanta());
			stmt.setInt(3, habitacion.getNumero());
			if(habitacion instanceof HabitacionSimple) {
				stmt.setString(4, "Simple");
			}else if(habitacion instanceof HabitacionDoble) {
				stmt.setString(4, "Doble");
			}else {
				stmt.setString(4, "Suite");
			}
			if(habitacion.getReserva()!=null) {
				stmt.setInt(5, habitacion.getReserva().getId());
			}else {
				stmt.setInt(5, -1);
			}
			stmt.setInt(6, habitacion.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la habitacion en la BD", e);
		}
	}
	
	//Metodo para borrar trabajadores
	
	public void deleteHabitacion(Habitacion habitacion) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM habitaciones WHERE id=?")) {
			stmt.setInt(1,habitacion.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la habitacion con id " + habitacion.getId(), e);
		}
	}
//	
//	
//	
//	
//	
//	
//	Metodos para gestionar el almacenamiento y consulta de las tareas
//	
//	
//	
//	
//	
	public List<Tarea> getTareas() throws BDexception {
		List<Tarea> tareas = new ArrayList<Tarea>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, rol, numHoras,"
					+ " estaCompletada, descripcion, DNITrabajador FROM tareas");

			while(rs.next()) {
				Tarea tarea = new Tarea();
				tarea.setId(rs.getInt("id"));
				tarea.setRol(Rol.valueOf(rs.getString("rol")));
				tarea.setNumHoras(rs.getInt("numHoras"));
				tarea.setEstaCompletada(rs.getBoolean("estaCompletada"));
				tarea.setDescripcion(rs.getString("descripcion"));
				tarea.setCompletadaPor(getTrabajador(rs.getString("DNITrabajador")));
				tareas.add(tarea);
				
			}
			
			return tareas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos las tareas'", e);
		}
	}
	public List<Tarea> getTareasTrabajador(Trabajador trabajador) throws BDexception {
		List<Tarea> tareas = new ArrayList<Tarea>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, rol, numHoras,"
				+ " estaCompletada, descripcion, DNITrabajador FROM tareas WHERE DNITrabajador=?")) {
			stmt.setString(1, trabajador.getDni());
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Tarea tarea = new Tarea();
				tarea.setId(rs.getInt("id"));
				tarea.setRol(Rol.valueOf(rs.getString("rol")));
				tarea.setNumHoras(rs.getInt("numHoras"));
				tarea.setEstaCompletada(rs.getBoolean("estaCompletada"));
				tarea.setDescripcion(rs.getString("descripcion"));
				tarea.setCompletadaPor(trabajador);
				tareas.add(tarea);
				
			}
			
			return tareas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las tareas del trabajador'", e);
		}
	}
	
	
	//Metodo para obtener una tarea en concreto
	
	public Tarea getTarea(int id) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, rol, numHoras,"
				+" estaCompletada, descripcion, DNITrabajador FROM tareas WHERE id = ?")) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Tarea tarea = new Tarea();
				tarea.setId(rs.getInt("id"));
				tarea.setRol(Rol.valueOf(rs.getString("rol")));
				tarea.setNumHoras(rs.getInt("numHoras"));
				tarea.setEstaCompletada(rs.getBoolean("estaCompletada"));
				tarea.setDescripcion(rs.getString("descripcion"));
				tarea.setCompletadaPor(getTrabajador(rs.getString("DNITrabajador")));
				
				return tarea;
			} else {
				Tarea tarea = new Tarea();
				tarea.setId(-1);
				return tarea;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo el trabajador con DNI " + id, e);
		}
	}
	
	
	//Metodo para guardar tareas en la base de datos
	
	public void guardarTarea(Tarea tarea) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO tareas (id, rol, numHoras,"
				+ " estaCompletada, descripcion, DNITrabajador) VALUES (?, ?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setInt(1, tarea.getId());
			stmt.setString(2, tarea.getRol().toString());
			stmt.setInt(3, tarea.getNumHoras());
			stmt.setBoolean(4,tarea.isEstaCompletada());
			stmt.setString(5, tarea.getDescripcion());
			if(tarea.getCompletadaPor()==null) {
				stmt.setString(6, "-1");
			}else {
				stmt.setString(6, tarea.getCompletadaPor().getDni());

			}
			
			
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la tarea en la BD", e);
		}
	}
	
	
	//Metodo para actualizar tareas
	
	public void actualizarTarea(Tarea tarea) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE tareas SET rol=?, numHoras=?"
				+ ", estaCompletada=?, descripcion=?, DNITrabajador=? WHERE id=?")) {
			stmt.setString(1, tarea.getRol().toString());
			stmt.setInt(2, tarea.getNumHoras());
			stmt.setBoolean(3, tarea.isEstaCompletada());
			stmt.setString(4, tarea.getDescripcion());
			if(tarea.getCompletadaPor()==null) {
				stmt.setString(5, "-1");
			}else {
				stmt.setString(5, tarea.getCompletadaPor().getDni());

			}
			stmt.setInt(6, tarea.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo actualizar la tarea en la BD", e);
		}
	}
	
	//Metodo para borrar tareas
	
	public void deleteTarea(Tarea tarea) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM tareas WHERE id=?")) {
			stmt.setInt(1,tarea.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la tarea con ID " + tarea.getId(), e);
		}
	}
	
//	
//	
//	
//	
//	
//	
//	
//	Metodos para gestionar el almacenamiento y la consulta de las plazas de parking;
//	
//	
//	
//	
//	
//	
	public List<PlazaParking> getPlazasParkingTodas() throws BDexception {
		List<PlazaParking> plazas = new ArrayList<PlazaParking>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, row, column,"
					+ " ocupada, idReserva, fechaParking FROM plazaparking");

			while(rs.next()) {
				PlazaParking plaza = new PlazaParking();
				plaza.setId(rs.getInt("id"));
				plaza.setRow(rs.getInt("row"));
				plaza.setColumn(rs.getInt("column"));
				plaza.setOcupada(rs.getBoolean("ocupada"));
				Reserva r = getReserva(rs.getInt("idReserva"));
				plaza.setReserva(r);
				Parking p = getParking(LocalDate.parse(rs.getString("fechaParking")));
				plaza.setParking(p);
				plazas.add(plaza);
				
			}
			
			return plazas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las plazas de parking'", e);
		}
	}
	public List<PlazaParking> getPlazasDeReserva(Reserva reserva) throws BDexception {
		List<PlazaParking> plazas = new ArrayList<PlazaParking>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, row, column,"
				+ " ocupada, idReserva, fechaParking FROM plazaparking WHERE idReserva=?")) {
			stmt.setInt(1, reserva.getId());
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				PlazaParking plaza = new PlazaParking();
				plaza.setId(rs.getInt("id"));
				plaza.setRow(rs.getInt("row"));
				plaza.setColumn(rs.getInt("column"));
				plaza.setOcupada(rs.getBoolean("ocupada"));
				plaza.setReserva(reserva);
				Parking p = getParking(LocalDate.parse(rs.getString("fechaParking")));
				plaza.setParking(p);
				plazas.add(plaza);
				
			}
			
			return plazas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las plazas de parking de la Reserva'"+ reserva.getId(), e);
		}
	}
	
	public PlazaParking [][] getPlazasDeParking(Parking parking) throws BDexception {
		PlazaParking [][] plazas = new PlazaParking [5][5];
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, row, column,"
				+ " ocupada, idReserva, fechaParking FROM plazaparking WHERE fechaParking=?")) {
			stmt.setString(1, parking.getFecha().toString());
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				PlazaParking plaza = new PlazaParking();
				plaza.setId(rs.getInt("id"));
				plaza.setRow(rs.getInt("row"));
				plaza.setColumn(rs.getInt("column"));
				plaza.setOcupada(rs.getBoolean("ocupada"));
				Reserva r = getReserva(rs.getInt("idReserva"));
				plaza.setReserva(r);
				plaza.setParking(parking);
				plazas[plaza.getRow()][plaza.getColumn()]= plaza;
				
			}
			
			return plazas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todas las plazas de parking de la Reserva'"+ parking.getFecha(), e);
		}
	}
	
	
	//Metodo para obtener una plaza en concreto
	
	public PlazaParking getPlazaParking(int id) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, row, column,"
				+" ocupada, idReserva, fechaParking FROM plazaparking WHERE id = ?")) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				PlazaParking plaza = new PlazaParking();
				plaza.setId(rs.getInt("id"));
				plaza.setRow(rs.getInt("row"));
				plaza.setColumn(rs.getInt("column"));
				plaza.setOcupada(rs.getBoolean("ocupada"));
				Reserva r = getReserva(rs.getInt("idReserva"));
				plaza.setReserva(r);
				Parking p = getParking(LocalDate.parse(rs.getString("fechaParking")));
				plaza.setParking(p);
				
				
				return plaza;
			} else {
				PlazaParking plaza = new PlazaParking();
				plaza.setId(-1);
				return plaza;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo la plaza de parking con id " + id, e);
		}
	}
	
	
	//Metodo para guardar plazas en la base de datos
	
	public void guardarPlaza(PlazaParking plaza) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO plazaparking (id, row, column,"
				+ " ocupada, idReserva, fechaParking) VALUES (?, ?, ?, ?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setInt(1, plaza.getId());
			stmt.setInt(2, plaza.getRow());
			stmt.setInt(3, plaza.getColumn());
			stmt.setBoolean(4,plaza.isOcupada());
			if(plaza.getReserva()!=null) {
				stmt.setInt(5, plaza.getReserva().getId());
			}else {
				stmt.setInt(5,-1);
			}
			stmt.setString(6, plaza.getParking().getFecha().toString());
			
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la plaza en la BD", e);
		}
	}
	
	
	//Metodo para actualizar plazas
	
	public void actualizarPlazaparking(PlazaParking plaza) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE plazaparking SET row=?, column=?"
				+ ", ocupada=?, idReserva=?, fechaParking=? WHERE id=?")) {
			stmt.setInt(1, plaza.getRow());
			stmt.setInt(2, plaza.getColumn());
			stmt.setBoolean(3, plaza.isOcupada());
			if(plaza.getReserva()!=null) {
				stmt.setInt(4, plaza.getReserva().getId());
			}else {
				stmt.setInt(4, -1);
			}
			stmt.setString(5, plaza.getParking().getFecha().toString());
			stmt.setInt(6, plaza.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo actualizar la plaza en la BD", e);
		}
	}
	
	//Metodo para borrar plazas
	
	public void deletePlaza(PlazaParking plaza) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM plazaparking WHERE id=?")) {
			stmt.setInt(1,plaza.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la plaza con ID " + plaza.getId(), e);
		}
	}
	
//	
//	
//	
//	
//	
//	
//	
//	Metodos para gestionar el almacenamiento y la consulta de los Parkings
//	
//	
//	
//	
//	
//	
//	
	
	public List<Parking> getParkings() throws BDexception {
		List<Parking> parkings = new ArrayList<Parking>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT fecha, completo, numPlazasDisponibles"
					+ "  FROM parkings");

			while(rs.next()) {
				Parking parking = new Parking();
				parking.setFecha(LocalDate.parse(rs.getString("fecha")));
				parking.setCompleto(rs.getBoolean("completo"));
				parking.setNumPlazasDisponibles(rs.getInt("numPlazasDisponibles"));
				parking.setDistribucion(getPlazasDeParking(parking));
				parkings.add(parking);
				
			}
			
			return parkings;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos los parkings de parking'", e);
		}
	}
		
	//Metodo para obtener un parking en concreto
	
	public Parking getParking(LocalDate date) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT fecha, completo, numPlazasDisponibles"
				+"  FROM parkings WHERE fecha = ?")) {
			stmt.setString(1, date.toString());
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Parking parking = new Parking();
				parking.setFecha(LocalDate.parse(rs.getString("fecha")));
				parking.setCompleto(rs.getBoolean("completo"));
				parking.setNumPlazasDisponibles(rs.getInt("numPlazasDisponibles"));	
				parking.setDistribucion(getPlazasDeParking(parking));
				
				return parking;
			} else {
				Parking parking = new Parking();
				parking.setFecha(LocalDate.MIN);
				return parking;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo el parking con fecha " + date, e);
		}
	}
	
	
	//Metodo para guardar parkings en la base de datos
	
	public void guardarParking(Parking parking) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO parkings (fecha, completo, numPlazasDisponibles"
				+ " ) VALUES (?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, parking.getFecha().toString());
			stmt.setBoolean(2, parking.isCompleto());
			stmt.setInt(3, parking.getNumPlazasDisponibles());
				
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar el parking en la BD", e);
		}
	}
	
	
	//Metodo para actualizar parkings
	
	public void actualizarParking(Parking parking) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE parkings SET completo=?, numPlazasDisponibles=?"
				+ " WHERE fecha=?")) {
			stmt.setBoolean(1, parking.isCompleto());
			stmt.setInt(2, parking.getNumPlazasDisponibles());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo actualizar el parking en la BD", e);
		}
	}
	
	//Metodo para borrar parkings
	
	public void deleteParking(Parking parking) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM parkings WHERE fecha=?")) {
			stmt.setString(1,parking.getFecha().toString());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar el Parking" + parking.getFecha(), e);
		}
	}
	

//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//			Metodos para gestionar las mesas
//	
//	
//	
//	
//	
//	
//	
//	
//	
	
	public List<Mesa> getMesas() throws BDexception {
		List<Mesa> mesas = new ArrayList<Mesa>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM mesas ORDER BY id");

			while(rs.next()) {
				Mesa mesa = new Mesa();
				mesa.setId(rs.getInt("id"));
				mesa.setNumero(rs.getInt("numero"));
				mesa.setOcupado(rs.getBoolean("ocupada"));
				Reserva r = getReserva(rs.getInt("idReserva"));
				mesa.setReserva(r);
				mesas.add(mesa);
			}
			
			return mesas;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos las mesas'", e);
		}
	}
	
	public Mesa getMesa(int id) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mesas WHERE id = ?")) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Mesa mesa = new Mesa();
				mesa.setId(rs.getInt("id"));
				mesa.setNumero(rs.getInt("numero"));
				mesa.setOcupado(rs.getBoolean("ocupada"));
				mesa.setReserva(getReserva(rs.getInt("idReserva")));
				
				return mesa;
			} else {
				Mesa mesa = new Mesa();
				mesa.setId(-1);
				return mesa;
				
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo el mesa con id= " + id, e);
		}
	}
	public void guardarMesa(Mesa mesa) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO mesas (id, numero, ocupada, idReserva"
				+ " ) VALUES (?, ?, ?,?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setInt(1, mesa.getId());
			stmt.setInt(2, mesa.getNumero());
			stmt.setBoolean(3, mesa.isOcupado());
			if (mesa.getReserva()==null) {
				stmt.setInt(4, -1);
			}else {
				stmt.setInt(4, mesa.getReserva().getId());
			}
			
				
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la mesa en la BD", e);
		}
	}
	
	
	public void actualizarMesa(Mesa mesa) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE mesas SET numero=?, ocupada=?, idReserva=? WHERE id=?")){
			stmt.setInt(1, mesa.getNumero());
			stmt.setBoolean(2, mesa.isOcupado());
			stmt.setInt(3,mesa.getReserva().getId());
			stmt.setInt(4, mesa.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo actualizar la mesa en la BD", e);
		}
	}
	
	public void deleteMesa(Mesa mesa) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM mesas WHERE id=?")) {
			stmt.setInt(1,mesa.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la mesa" + mesa.getId(), e);
		}
	}
	
//	
//	
//	
//	
//	
//	
//	
//	Metodo principal para rellenar los datos con los datos de la bd
//	
//	
//	
//	
//	
//	
//	
//	
	public void rellenarDatos(Datos datos) throws BDexception {
		datos.listaTrabajadores.addAll(getTrabajadores());
		
		datos.listaClientes.addAll(getClientes());
		
		datos.listaReservas.addAll(getReservas());
		
		datos.listaTareas.addAll(getTareas());
		
		datos.listaComedor.addAll(getMesas());
		
		
		for (Trabajador trabajador : datos.getListaTrabajadores()) {
			datos.getMapaTrabajadoresPorDNI().putIfAbsent(trabajador.getDni(), trabajador);
		}
		
		for (Cliente cliente : datos.getListaClientes()) {
			datos.getMapaClientesPorDNI().putIfAbsent(cliente.getDni(), cliente);
		}
		
		
		List<Parking> listaParkings = new ArrayList<>();
		getParkings().forEach((p)->{
			if(p.getFecha().isAfter(LocalDate.now().minusDays(1))) {
				listaParkings.add(p);
				
			}else {
				try {
					deleteParking(p);
				} catch (BDexception e) {
					System.err.println("Error borrando el parking");
					e.printStackTrace();
				}
			}
		});
		
		
		
		Stream<LocalDate> stream = LocalDate.now().datesUntil(LocalDate.now().plusDays(8));
		
		List<LocalDate> listaFechasQueFaltan = stream.toList();
	
		
		for (Parking parking : listaParkings) {
			datos.getMapaParkingPorFecha().putIfAbsent(parking.getFecha(), parking);
		}
		
		if(!listaFechasQueFaltan.isEmpty()) {
			listaFechasQueFaltan.forEach((f)->{
				if(!datos.getMapaParkingPorFecha().keySet().contains(f)) {
					Parking p = new Parking(f,false,25);
					p.distribucion = new PlazaParking [5][5];
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							PlazaParking pl =new PlazaParking(i,j,false,null,p);
							p.distribucion[i][j]= pl;
							try {
								guardarPlaza(pl);
							} catch (BDexception e) {
								e.printStackTrace();
							}
						}
					}
					listaParkings.add(p);
					try {
						guardarParking(p);
					} catch (BDexception e) {
						System.err.println("Error guardando el parking");
						e.printStackTrace();
					}
				}
			});
		}
		
		for (Parking parking : listaParkings) {
			datos.getMapaParkingPorFecha().putIfAbsent(parking.getFecha(), parking);
		}
	
		
		for (Habitacion habitacion : getHabitaciones()) {
			datos.getMapaHabitaciones().putIfAbsent(habitacion.getPlanta(), new ArrayList<>());
			datos.getMapaHabitaciones().get(habitacion.getPlanta()).add(habitacion);
		}
		
		
			
	}
	
	
	
	
	
}
