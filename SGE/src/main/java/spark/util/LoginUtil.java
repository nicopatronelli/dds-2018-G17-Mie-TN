package spark.util;

import static spark.util.RequestUtil.obtenerParamPassword;
import static spark.util.RequestUtil.obtenerParamUsuario;

import hibernate.RepositorioAdmins;
import hibernate.RepositorioClientes;
import spark.Request;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

public class LoginUtil {
	
    public static boolean autenticacionClienteEsCorrecta(Request request) {
		
    	RepositorioClientes repoClientes = null;
    	
    	// Obtengo el usuario y contraseña ingresados en los campos input
    	try {
        	String usuario = obtenerParamUsuario(request);
        	String password = obtenerParamPassword(request);
        	
        	repoClientes = new RepositorioClientes();
        	repoClientes.abrir();
        	Cliente cliente = repoClientes.recuperarPorUsuario(usuario);

        	if ( cliente == null )  // No encontró el usuario en la BD
        		return false;
        	
        	return cliente.getPassword().equals(password);
    	}
    	finally {
    		//repoClientes.cerrar();
    	}
    } // Fin autenticacionClienteEsCorrecta()
    
    public static boolean autenticacionAdminEsCorrecta(Request request) {
		
    	RepositorioAdmins repoAdmins = null;
    	
    	// Obtengo el usuario y contraseña ingresados en los campos input
    	try {
        	String usuario = request.queryParams("username");
        	String password = request.queryParams("pass");
        	
        	repoAdmins = new RepositorioAdmins();
        	repoAdmins.abrir();
        	Administrador admin = repoAdmins.recuperarPorUsuario(usuario);
        	
        	if ( admin == null )  // No encontró el usuario en la BD
        		return false;
        	
        	return admin.getPassword().equals(password);
    	}
    	finally {
    		//repoAdmins.cerrar();
    	}
    	
    } // Fin autenticacionAdminEsCorrecta()
}
