package spark;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.template.velocity.VelocityTemplateEngine;

public class Server {
	
	public static void main(String[] args) {
		
		Spark.staticFiles.location("/public");
		port(8080);
		
		// Pantalla de inicio (elección de tipo de usuario: cliente o administrador)
		get("/index", LoginController.serveIndexPage);
		
		// Mostramos la pantalla de login para ingresar con usuario y contraseña
		get("/login/clientes", LoginController.serveLoginClientsPage);
		get("/login/admins", LoginController.serveLoginAdminsPage);
		
		// Procesamos el usuario y contraseña ingresados para ver si son correctos 
		post("/login/clientes", LoginController.handleLoginClientsPost);
		post("/login/admins", LoginController.handleLoginAdminsPost);
		
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
