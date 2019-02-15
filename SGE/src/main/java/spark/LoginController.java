package spark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import usuarios.Administrador;
import usuarios.Cliente;
import static spark.RequestUtil.*;
import static spark.LoginUtil.*;

public class LoginController {
	
	public static Route serveIndexPage = (Request request, Response response) -> {
        // En este caso el modelo esta vacío porque simplemente vamos a mostrar la pantalla de inicio
		Map<String, Object> model = new HashMap<>();
		return ViewUtil.render(request, model, "/velocity/index.vm");
	};
	
	// Muestro la pantalla de login para clientes o admins 
	
    public static Route serveLoginClientsPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", "Clientes");
        model.put("primerLogin", true);
        return ViewUtil.render(request, model, "/velocity/login.html");
    };
    
    public static Route serveLoginAdminsPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", "Administradores");
        model.put("primerLogin", true);
        return ViewUtil.render(request, model, "/velocity/login.html");
    };

    // Procesamos el login de los usuarios 
    
    public static Route handleLoginClientsPost = (Request request, Response response) -> {
    	// Verifico que el usuario y contraseña son correctas 
        Map<String, Object> model = new HashMap<>();
        if (autenticacionClienteEsCorrecta(request)) {
        	request.session().attribute("currentUser", obtenerParamUsuario(request));
        	return ViewUtil.render(request, model, "/velocity/cliente.html");
        }
        model.put("autenticacionExitosa", false);
        model.put("usuario", "Clientes");
        model.put("primerLogin", false);
    	return ViewUtil.render(request, model, "/velocity/login.html");
    };

    public static Route handleLoginAdminsPost = (Request request, Response response) -> {
    	// Verifico que el usuario y contraseña son correctas 
        Map<String, Object> model = new HashMap<>();
        if (autenticacionAdminEsCorrecta(request)) {
        	return ViewUtil.render(request, model, "/velocity/admin.html");
        }
        model.put("autenticacionExitosa", false);
        model.put("usuario", "Administradores");
        model.put("primerLogin", false);
    	return ViewUtil.render(request, model, "/velocity/login.html");
    };
    
} // FIN Clase LoginController
