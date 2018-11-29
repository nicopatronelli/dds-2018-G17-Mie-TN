package spark;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.template.velocity.VelocityTemplateEngine;

public class Server {
	
	public static void main(String[] args) {
		
		Spark.staticFiles.location("/public");
		
		get("/index", LoginController.serveIndexPage);
		// Mostramos la pantalla de login para ingresar con usuario y contraseña
		get("/login/usuarios", LoginController.serveLoginUsersPage);
		get("/login/admins", LoginController.serveLoginAdminsPage);
		
		// Procesamos el usuario y contraseña ingresados para ver si son correctos 
		post("/login/usuarios", LoginController.handleLoginClientsPost);
		post("/login/admins", LoginController.handleLoginAdminsPost);
		
		get("/domicilios", DomiciliosController.serveDomiciliosPage);
        //get("/login", (req, res) -> new ModelAndView(model, "/velocity/login.hbs"), new VelocityTemplateEngine());
        //req.queryParams("username");
		//post("/login", (req, res) -> new ModelAndView(model, "/velocity/login.hbs"), new VelocityTemplateEngine());
	}
	
}
