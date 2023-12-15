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
import java.util.List;



//Clase para gestionar la base de datos

public class BDmanager {

	private Connection conn = null;
	
	//Metodos para gestionar el almacenamiento y la consulta de los clientes
	
	// Metodo para conectar con el driver y la base de datos
	
	public void connect (String dbPath) throws BDexception{
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
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
				clientes.add(cliente);
			}
			
			return clientes;
		} catch (SQLException | DateTimeParseException e) {
			throw new BDexception("Error obteniendo todos los clientes'", e);
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
				
				return reserva;
			} else {
				Reserva reserva = new Reserva();
				reserva.setId(-1);
				return reserva;
				
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
			stmt.setString(2, reserva.getCliente().getDni());
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
			stmt.setInt(1,reserva.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar la resera con id " + reserva.getId(), e);
		}
	}
	
	public void actualizarReserva(Reserva reserva) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE resrvas SET DNICliente=?, fInicio=?"
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
//	
//	
//	
//	
//	
//	
//	Metodos para gestionar la consulta o guardado de trabajadores
	
	public List<Trabajador> getTrabajadores() throws BDexception {
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT DNI, Nombre, Apellido,"
					+ " Email, Direccion, fNacimiento, Contraseña, Telefono,"
					+ " FotoPerfil, Salario, HorasTrabajadas FROM trabajadores");

			while(rs.next()) {
				Trabajador trabajador = new Trabajador();
				trabajador.setDni(rs.getString("DNI"));
				trabajador.setApellido1(rs.getString("Apellido"));
				trabajador.setEmail(rs.getString("Email"));
				trabajador.setDireccion(rs.getString("Direccion"));
				trabajador.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				trabajador.setContraseña(rs.getString("Contraseña"));
				trabajador.setTelefono(rs.getString("Telefono"));
				trabajador.setFotoPerfil(rs.getString("FotoPerfil"));
				trabajador.setSalario(rs.getDouble("Salario"));
				trabajador.setNumHorasTrabajadas(rs.getInt("HorasTrabajadas"));
				//TODO añadir las tareas a las listas
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
				trabajador.setApellido1(rs.getString("Apellido"));
				trabajador.setEmail(rs.getString("Email"));
				trabajador.setDireccion(rs.getString("Direccion"));
				trabajador.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				trabajador.setContraseña(rs.getString("Contraseña"));
				trabajador.setTelefono(rs.getString("Telefono"));
				trabajador.setFotoPerfil(rs.getString("FotoPerfil"));
				trabajador.setSalario(rs.getDouble("Salario"));
				trabajador.setNumHorasTrabajadas(rs.getInt("HorasTrabajadas"));
				
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
	
	public List<Habitacion> getHabitacionesSimples() throws BDexception {
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, ocupado, planta,"
					+ " numero FROM habitaciones WHERE tipo='Simple'");
			while(rs.next()) {
				HabitacionSimple habitacion = new HabitacionSimple();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
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
					+ " numero FROM habitaciones WHERE tipo='Doble'");
			while(rs.next()) {
				HabitacionDoble habitacion = new HabitacionDoble();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
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
					+ " numero FROM habitaciones WHERE tipo='Suite'");
			while(rs.next()) {
				HabitacionSuite habitacion = new HabitacionSuite();
				habitacion.setId(rs.getInt("id"));
				habitacion.setOcupado(rs.getBoolean("ocupado"));
				habitacion.setPlanta(rs.getInt("planta"));
				habitacion.setNumero(rs.getInt("numero"));
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
				+ " numero, tipo FROM habitaciones WHERE id = ?")) {
			
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
	
	
	//Metodo para guardar trabajador en la base de datos
	
	public void guardarHabitacion(Habitacion habitacion) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO habitaciones (id, ocupado, planta,"
				+ " numero, tipo) VALUES (?, ?, ?, ?, ?)");
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
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BDexception("No se pudo guardar la habitacion en la BD", e);
		}
	}
	
	
	//Metodo para actualizar trabajadores
	
	public void actualizarHabitacion(Habitacion habitacion) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE habitaciones SET ocupado=?, planta=?"
				+ ", numero=?, tipo=? WHERE id=?")) {
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
			
			stmt.setInt(5, habitacion.getId());
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
	public static void main(String[] args) {
		BDmanager manager = new BDmanager();

		try {
			manager.connect("bd/database.db");
			
			Trabajador t1 = new Trabajador("18087363T", "Mario", "Martinez","mario@gmail.com", "Calle Alfonso 2", LocalDate.now(), "123", "673821992", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
			manager.deleteTrabajador(t1);
			
			
			List<Habitacion> h = manager.getHabitaciones();
			
			System.out.println(h.size());
			for (Habitacion habitacion : h) {
				System.out.println(habitacion);
			}
			
			manager.disconnect();
		} catch (BDexception e) {
			System.err.println("Error conectando con la database");
			e.printStackTrace();
		}
	}
	
	
	
}
