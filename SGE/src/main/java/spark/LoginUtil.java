package spark;

import static spark.RequestUtil.obtenerParamPassword;
import static spark.RequestUtil.obtenerParamUsuario;

import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

public class LoginUtil {
	
    public static boolean autenticacionClienteEsCorrecta(Request request) {
		// Obtengo el usuario y contraseña ingresados en los campos input
    	String usuario = obtenerParamUsuario(request);
    	String password = obtenerParamPassword(request);
    	
    	Cliente cliente = new Cliente();
    	cliente.inicializarEntityManager();
    	cliente = (Cliente) cliente.obtenerEntidadPorAtributo("usuario", usuario);
    	
    	if ( cliente == null )  // No encontró el usuario en la BD
    		return false;
    	
    	return cliente.getPassword().equals(password);
    }
    
    public static boolean autenticacionAdminEsCorrecta(Request request) {
		// Obtengo el usuario y contraseña ingresados en los campos input
    	String usuario = obtenerParamUsuario(request);
    	String password = obtenerParamPassword(request);
    	
    	Administrador admin = new Administrador();
    	admin.inicializarEntityManager();
    	admin = (Administrador) admin.obtenerEntidadPorAtributo("usuario", usuario);
    	
    	if ( admin == null )  // No encontró el usuario en la BD
    		return false;
    	
    	return admin.getPassword().equals(password);
    }
}
