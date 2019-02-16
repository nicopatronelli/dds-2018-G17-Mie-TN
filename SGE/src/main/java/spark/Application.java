package spark;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.template.velocity.VelocityTemplateEngine;

public class Application {
	
	// Para Heroku
    /*static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }*/
	
	public static void main(String[] args) {
		
		Spark.staticFiles.location("/public");
		port(8080);
		
		// Pantalla de inicio (elección de tipo de usuario: cliente o administrador)
		get("/index", LoginController.serveIndexPage);
		
		// Mostramos la pantalla de login para ingresar con usuario y contraseña
		get("/login/cliente", LoginController.serveLoginClientsPage);
		get("/login/admin", LoginController.serveLoginAdminsPage);
		
		// Procesamos el usuario y contraseña ingresados para ver si son correctos 
		post("/login/cliente", LoginController.handleLoginClientsPost);
		post("/login/admin", LoginController.handleLoginAdminsPost);
		
		// Listamos los dispositivos del cliente actual 
		get("/cliente/dispositivos", ClienteController.listarDispositivos);
		
		/* Brindamos la opción de encender/apagar/ahorro a los dispositivos del 
		 *  cliente. Como es una acción que tiene efecto usamos post. 
		 */
		post("/dispositivo/encender", DispositivoController.encender);
		post("/dispositivo/apagar", DispositivoController.apagar);
		post("/dispositivo/ahorro", DispositivoController.ahorro);
		
		// Ejecución de simplex
		get("/cliente/simplex", ClienteController.ejecutarSimplex);
		
		/*** ESTOS NO ***/
		get("/admin/domicilios", DomiciliosController.serveDomiciliosPage);
		
		get("/dispositivo/alta", DomiciliosController.serveAltaDispositivoPage);
		
		get("/domicilio/estado", DomiciliosController.serveEstadoDomicilioPage);
		
		get("/dispositivo/archivo", DomiciliosController.serveCargaArchivoDispositivosPage);
		
		get("/simplex", DomiciliosController.serveSimplexPage);

		get("/consumo/periodo", DomiciliosController.serveConsumoPeriodoPage);
		
		get("/mapa", DomiciliosController.serveMapaPage);
		
        //get("/login", (req, res) -> new ModelAndView(model, "/velocity/login.hbs"), new VelocityTemplateEngine());
        //req.queryParams("username");
		//post("/login", (req, res) -> new ModelAndView(model, "/velocity/login.hbs"), new VelocityTemplateEngine());
	}
	
}
