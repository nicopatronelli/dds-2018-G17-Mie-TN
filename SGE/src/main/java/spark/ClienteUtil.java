package spark;

import hibernate.RepositorioClientes;
import usuarios.Cliente;

import static spark.RequestUtil.obtenerUsuarioActual;

import java.util.List;

import dispositivos.Dispositivo;

public class ClienteUtil {
	
	public static double[] ejecutarSimplex(Request request) {

		// Obtenemos el nombre de usuario del cliente de la sesión actual
    	String usuarioActual = obtenerUsuarioActual(request);
    	
    	RepositorioClientes repoClientes = new RepositorioClientes();
    	repoClientes.abrir();
    	Cliente cliente = repoClientes.recuperarPorUsuario(usuarioActual);
    	
    	// Retornamos el resultado de la ejecución del Simplex 
    	return cliente.recomendacionConsumo(cliente.domicilioPrincipal());
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
