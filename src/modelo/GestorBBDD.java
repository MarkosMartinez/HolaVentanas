package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorBBDD extends Conector{
	Conector conector = new Conector();
	
	//Clientes ----------------------------------------------------
	public void altaCliente(Cliente cliente) throws SQLException{
		conector.conectar();
		PreparedStatement alta = conector.getCon().prepareStatement("INSERT INTO clientes (dni, nombre, apellidos, direccion, localidad) VALUES (?,?,?,?,?);");
		alta.setString(1, cliente.getDni());
		alta.setString(2, cliente.getNombre());
		alta.setString(3, cliente.getApellidos());
		alta.setString(4, cliente.getDireccion());
		alta.setString(5, cliente.getLocalidad());
		alta.execute();
		conector.cerrar();	
	}
	
	public  Cliente getCliente(String dni) throws SQLException {
		Cliente cliente = new Cliente();
		conector.conectar();
		PreparedStatement gettear =conector.getCon().prepareStatement("SELECT * FROM clientes WHERE dni =?");
		gettear.setString(1, dni);
		ResultSet resultado=gettear.executeQuery();
		if(resultado.next()) {
		cliente.setDni(resultado.getString("dni"));
		cliente.setNombre(resultado.getString("nombre"));
		cliente.setApellidos(resultado.getString("apellidos"));
		cliente.setDireccion(resultado.getString("direccion"));
		cliente.setLocalidad(resultado.getString("localidad"));

		}else {
			cliente.setDni("-1");
		}
		
		conector.cerrar();
		return cliente;
		
	}
	
	public ArrayList<Cliente> getClientes() throws SQLException {
		ArrayList<Cliente> clientes = new ArrayList<>();
		conector.conectar();
		PreparedStatement gettearClientes = conector.getCon().prepareStatement("SELECT * FROM clientes");
		ResultSet resultado = gettearClientes.executeQuery();
		while(resultado.next()) {
			Cliente cliente = new Cliente();
			cliente.setDni(resultado.getString("dni"));
			cliente.setNombre(resultado.getString("nombre"));
			cliente.setApellidos(resultado.getString("apellidos"));
			cliente.setDireccion(resultado.getString("direccion"));
			cliente.setLocalidad(resultado.getString("direccion"));

			clientes.add(cliente);
		}
		conector.cerrar();
		return clientes;
	}
	
	public void bajaCliente(Cliente cliente) throws SQLException {
		conector.conectar();
		PreparedStatement pstDelete = conector.getCon().prepareStatement("DELETE FROM clientes WHERE dni =?");
		pstDelete.setString(1, cliente.getDni());
		pstDelete.execute();
		conector.cerrar();
		
	}
	
	public void modificarCliente(Cliente nuevosDatosCliente) throws SQLException {
		conector.conectar();
		PreparedStatement pstModificar = conector.getCon().prepareStatement("UPDATE clientes SET nombre= ?, apellidos= ?, direccion= ?, localidad=? WHERE dni = ?;");
		pstModificar.setString(1, nuevosDatosCliente.getNombre());
		pstModificar.setString(2, nuevosDatosCliente.getApellidos());
		pstModificar.setString(3, nuevosDatosCliente.getDireccion());
		pstModificar.setString(4, nuevosDatosCliente.getLocalidad());
		pstModificar.setString(5, nuevosDatosCliente.getDni());
		pstModificar.execute();
		conector.cerrar();
		
	}
	
/*---------------------A PARTIR DE AQUI ES PARA HOTELES */
	public void registrarHotel(Hotel hotel) throws SQLException{
		conector.conectar();
		PreparedStatement registrarHotel = conector.getCon().prepareStatement("INSERT INTO hoteles (cif, nombre, gerente, estrellas, compania) VALUES (?,?,?,?,?);");
		registrarHotel.setString(1,hotel.getCif());
		registrarHotel.setString(2, hotel.getNombre());
		registrarHotel.setString(3, hotel.getGerente());
		registrarHotel.setInt(4, hotel.getEstrellas());
		registrarHotel.setString(5, hotel.getCompania());

		registrarHotel.execute();
		conector.cerrar();	
	}
	
	
	
	public  Hotel getHoteles(int id) throws SQLException {
		Hotel hotel= new Hotel();
		conector.conectar();
		PreparedStatement gettear = conector.getCon().prepareStatement("SELECT * FROM hoteles WHERE id = ?");
		gettear.setInt(1, id);
		ResultSet resultado=gettear.executeQuery();
		if(resultado.next()) {
		hotel.setId(resultado.getInt("id"));
		hotel.setCif(resultado.getString("cif"));
		hotel.setNombre(resultado.getString("nombre"));
		hotel.setGerente(resultado.getString("gerente"));
		hotel.setEstrellas(resultado.getInt("estrellas"));

		}else {
			hotel.setId(-1);
		}
		
		conector.cerrar();
		return hotel;
		
	}

	//-------------------------HABITACIONES-------------------------------------------------
	public void insertarHabitacion(Habitacion datosHabitacion) throws SQLException {
		conector.conectar();
		PreparedStatement registrarHabitacionl = conector.getCon().prepareStatement("INSERT INTO habitaciones (id, id_hotel, numero, descripcion, precio) VALUES (?,?,?,?,?);");
		registrarHabitacionl.setInt(1,datosHabitacion.getId());
		registrarHabitacionl.setInt(2,datosHabitacion.getId_hotel());
		registrarHabitacionl.setInt(3,datosHabitacion.getNumero());
		registrarHabitacionl.setString(4, datosHabitacion.getDescripcion());
		registrarHabitacionl.setDouble(5, datosHabitacion.getPrecio());
		registrarHabitacionl.execute();
		conector.cerrar();
	}
	
	public ArrayList<Habitacion> getHabitaciones(int id_hotel) throws SQLException {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		conector.conectar();
		PreparedStatement gettearHabitacion = conector.getCon().prepareStatement("SELECT * FROM habitaciones WHERE id_hotel = ?");
		gettearHabitacion.setInt(1, id_hotel);
		ResultSet resultado = gettearHabitacion.executeQuery();
		while(resultado.next()) {
			Habitacion habitacion = new Habitacion();
			habitacion.setId(resultado.getInt("id"));
			habitacion.setId_hotel(resultado.getInt("id_hotel"));
			habitacion.setNumero(resultado.getInt("numero"));
			habitacion.setDescripcion(resultado.getString("descripcion"));
			habitacion.setPrecio(resultado.getDouble("precio"));
			habitaciones.add(habitacion);
		}
		conector.cerrar();
		return habitaciones;
	}
	
	public  Habitacion getHabitacion(int id, int id_hotel) throws SQLException {
		Habitacion habitacion = new Habitacion();
		conector.conectar();
		PreparedStatement getHabitacion =  conector.getCon().prepareStatement("SELECT * FROM habitaciones WHERE id =? AND id_hotel = ?");
		getHabitacion.setInt(1, id);
		getHabitacion.setInt(2, id_hotel);
		ResultSet resultado = getHabitacion.executeQuery();
		if(resultado.next()) {
		habitacion.setId(resultado.getInt("id"));
		habitacion.setId_hotel(resultado.getInt("id_hotel"));
		habitacion.setNumero(resultado.getInt("numero"));
		habitacion.setDescripcion(resultado.getString("descripcion"));
		habitacion.setPrecio(resultado.getDouble("precio"));

		}else {
			habitacion.setId(-1);
		}
		
		conector.cerrar();
		return habitacion;
		
	}

	//-----------------------------------RESERVA-----------------------------------------
	public void realizarReserva(Reserva reserva) throws SQLException {
		conector.conectar();
		PreparedStatement registrarReserva = conector.getCon().prepareStatement("INSERT INTO reservas ( id_habitacion,dni,desde,hasta) VALUES (?,?,?,?);");
		Date desde = new Date(reserva.getDesde().getTime());
		Date hasta = new Date(reserva.getHasta().getTime());
		
		int compararFecha=desde.compareTo(hasta);
		if (compararFecha<0) {
			registrarReserva.setInt(1,reserva.getId_habitacion());
			registrarReserva.setString(2,reserva.getDni());
			registrarReserva.setDate(3,desde);
			registrarReserva.setDate(4,hasta);
			registrarReserva.execute();
		}else {
		}
		conector.cerrar();
	}
	
	
	public void anularReserva(int idReserva) throws SQLException {
			conector.conectar();
			PreparedStatement pstDelete = conector.getCon().prepareStatement("DELETE FROM reservas WHERE id =?");
			pstDelete.setInt(1, idReserva);
			pstDelete.execute();
			conector.cerrar();
		
	}

	public Reserva getReserva(int idEliminar) throws SQLException {
		Reserva reserva = new Reserva();
		conector.conectar();
		PreparedStatement getReserva =  conector.getCon().prepareStatement("SELECT * FROM reservas WHERE id =?");
		getReserva.setInt(1, idEliminar);
		ResultSet resultado = getReserva.executeQuery();
		if(resultado.next()) {
		reserva.setId(resultado.getInt("id"));
		reserva.setId_habitacion(resultado.getInt("id_habitacion"));
		reserva.setDni(resultado.getString("dni"));
		reserva.setDesde(resultado.getDate("desde"));
		reserva.setHasta(resultado.getDate("hasta"));

		}else {
			reserva.setId(-1);
		}
		
		conector.cerrar();
		return reserva;
		
	}
	
	public ArrayList<Reserva> getReservas() throws SQLException {
		ArrayList<Reserva> reservas = new ArrayList<>();
		conector.conectar();
		PreparedStatement gettearReservas = conector.getCon().prepareStatement("SELECT * FROM reservas");
		ResultSet resultado = gettearReservas.executeQuery();
		while(resultado.next()) {
			Reserva reserva = new Reserva();
			reserva.setId(resultado.getInt("id"));
			reserva.setId_habitacion(resultado.getInt("id_habitacion"));
			reserva.setDni(resultado.getString("dni"));
			reserva.setDesde(resultado.getDate("desde"));
			reserva.setHasta(resultado.getDate("hasta"));
			reservas.add(reserva);
		}
		conector.cerrar();
		return reservas;
	}
	
	
	
}
