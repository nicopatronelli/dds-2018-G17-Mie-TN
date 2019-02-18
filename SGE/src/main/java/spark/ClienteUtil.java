package spark;

import hibernate.RepositorioClientes;
import usuarios.Cliente;

import static spark.RequestUtil.obtenerUsuarioActual;

import java.util.List;

import dispositivos.Dispositivo;

public class ClienteUtil {
	
	public static double ejecutarSimplex(Request request) {
		
		RepositorioClientes repoClientes = null;
		Cliente cliente = null; 
		
		try {
			// Obtenemos el nombre de usuario del cliente de la sesión actual
	    	String usuarioActual = obtenerUsuarioActual(request);
	    	
	    	repoClientes = new RepositorioClientes();
	    	repoClientes.abrir();
	    	cliente = repoClientes.recuperarPorUsuario(usuarioActual);
	    	
	    	// Retornamos el resultado de la ejecución del Simplex 
	    	return cliente.recomendacionHorasConsumo(cliente.domicilioPrincipal());
		}
		finally{
			repoClientes.guardar(cliente);
		}	
	}
	
	public static double hogarEficiente(Request request) {
		
		RepositorioClientes repoClientes = new RepositorioClientes();

		// Obtenemos el nombre de usuario del cliente de la sesión actual
    	String usuarioActual = obtenerUsuarioActual(request);
    	
    	repoClientes.abrir();
    	Cliente cliente = repoClientes.recuperarPorUsuario(usuarioActual);
    	
    	// Retornamos el valor de consumo del último período para el domicilio principal del cliente
    	return cliente.domicilioPrincipal().consumoUltimoPeriodo();
	}
	
	public static List<Dispositivo> recuperarDispositivos(Request request) {

		// Obtenemos el nombre de usuario del cliente de la sesión actual
    	String usuarioActual = obtenerUsuarioActual(request);
    	
    	RepositorioClientes repoClientes = new RepositorioClientes();
    	repoClientes.abrir();
    	Cliente cliente = repoClientes.recuperarPorUsuario(usuarioActual);
    	return cliente.domicilioPrincipal().dispositivos();
	}
	
}
