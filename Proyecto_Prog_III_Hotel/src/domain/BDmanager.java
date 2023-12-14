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
					+ " E-mail, Direccion, fNacimiento, Contraseña, Telefono,"
					+ " FotoPerfil, UltimoLogin FROM clientes");

			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("DNI"));
				cliente.setApellido1(rs.getString("Apellido"));
				cliente.setEmail(rs.getString("E-mail"));
				cliente.setDireccion(rs.getString("Direccion"));
				cliente.setfNacimiento(LocalDate.parse(rs.getString("fNacimiento")));
				cliente.setContraseña(rs.getString("Contraseña"));
				cliente.setTelefono(rs.getString("Telefono"));
				cliente.setFotoPerfil(rs.getString("FotoPerfil"));
				cliente.setUltimoLogin(LocalDate.parse(rs.getString("UltimoLogin")));
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
				+ " E-mail, Direccion, fNacimiento, Contraseña, Telefono,"
				+ " FotoPerfil, UltimoLogin FROM clientes WHERE DNI = ?")) {
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("DNI"));
				cliente.setApellido1(rs.getString("Apellido"));
				cliente.setEmail(rs.getString("E-mail"));
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
			throw new BDexception("Error obteniendo el usuario con DNI " + dni, e);
		}
	}
	
	
	//Metodo para guardar cliente en la base de datos
	
	public void guardarCliente(Cliente cliente) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO clientes (DNI, Nombre, Apellido,"
				+ " E-mail, Direccion, fNacimiento, Contraseña, Telefono,"
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
				+ ", E-mail=?, Direccion=?, fNacimiento=?, Contraseña=?, Telefono=?, "
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
	
	public void delete(Cliente cliente) throws BDexception {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM clientes WHERE DNI=?")) {
			stmt.setString(1,cliente.getDni());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new BDexception("No se pudo elimiar el usuario con id " + cliente.getDni(), e);
		}
	}
}
