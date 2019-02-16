package spark;

import hibernate.RepositorioClientes;
import usuarios.Cliente;

import static spark.RequestUtil.obtenerUsuarioActual;

import java.util.List;

import dispositivos.Dispositivo;

public class ClienteUtil {

	public static List<Dispositivo> recuperarDispositivos(Request request) {
		
    	RepositorioClientes repoClientes = null;
    	
    	try {
    		// Obtenemos el nombre de usuario del cliente de la sesión actual
        	String usuarioActual = obtenerUsuarioActual(request);
        	
        	repoClientes = new RepositorioClientes();
        	repoClientes.abrir();
        	Cliente cliente = repoClientes.recuperarPorUsuario(usuarioActual);
        	return cliente.domicilioPrincipal().dispositivos();
    	}
    	finally {
    		//repoClientes.cerrar();
    	}
    	
	}
	
}
