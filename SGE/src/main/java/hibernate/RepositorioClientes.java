package hibernate;

import java.util.List;

import javax.persistence.Query;

import reglas.ReglaObservador;
import usuarios.Cliente;

public class RepositorioClientes {

	PersistEntity <Cliente> pe;
	
	// Constructor 
	public RepositorioClientes() {
		pe = new PersistEntity<Cliente>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(Cliente cliente) {
		pe.guardar(cliente);
	}
	
	public void borrar(Cliente cliente) {
		pe.borrar(cliente);
	}
	
	public void actualizar(Cliente cliente) {
		pe.actualizar(cliente);
	}
	
	public Cliente recuperarPorId(Long idCliente) {
		return pe.recuperar(idCliente, Cliente.class);
	}

	public Cliente recuperarPorUsuario(String nombreUsuario) {
		return pe.obtenerEntidadPorAtributo("usuario", nombreUsuario, Cliente.class);
	}
	
	public List<Cliente> recuperarTodosLosClientes(){
		String query = "FROM Cliente"; // Me traigo todos los clientes de la BD
		List<Cliente> clientes = pe.ejecutarQuery(query);
		return clientes;
	}
	
	public List<String> reglasActivasPorUsuario(String idUsuario){
		String querySQL = 
				"SELECT descripcion " + 
				"FROM reglas r " + 
				"	JOIN actuadores a " + 
				"		ON r.id_regla = a.id_regla " + 
				"	JOIN dispositivos d " + 
				"		ON a.id_dispositivo_inteligente = d.id_dispositivo " + 
				"	JOIN domicilios dom " + 
				"		ON d.id_domicilio = dom.id_domicilio " + 
				"	JOIN clientes c " +
				"		ON dom.id_cliente = c.id_cliente " +
				"WHERE c.usuario = :idCliente ";
		
		Query query = pe.manager().createNativeQuery(querySQL);
		query.setParameter("idCliente", idUsuario);
		return query.getResultList();
		//pe.ejecutarQuerySqlNativo(query);
	}
}
