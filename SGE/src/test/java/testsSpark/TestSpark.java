package testsSpark;

import static spark.Spark.*;

import java.time.*;

public class TestSpark {
	
	public static void main(String[] args) {
		//port(8080); // Cambia el puerto por defecto en el que escucha Jetty
		
		// 127.0.0.1 = localhost 
		
		// http://localhost:4567/horaActual
		get("/horaActual", (req, res) -> LocalDateTime.now());
		
		// http://localhost:4567/saludar?nombre=Nicolas
		get("/saludar", (req, res) -> "Hola! " + req.queryParams("nombre"));
		
		// named parameters - http://localhost:4567/otroSaludo/nico
		get("/otroSaludo/:nombre", (req, res) -> {return "Este es otro saludo para " 
				+ req.params(":nombre");
		});
		
		// http://localhost:4567/say/it/to/Marge
		get("/say/*/to/*", (request, response) -> {
		    return "Number of splat parameters: " + request.splat().length;
		});
		
		//notFound("<html><body><h1>Recuro NO encontrado</h1></body></html>");
		notFound((req, res) -> {
		    res.type("application/json");
		    return "{\"message\":\"Custom 404\"}";
		});
		
		
		// Devolvemos html
		get("/sge", (req, res) -> {return ""
				+ "<html>"
				+ "<head>"
				+ "<title>SGE</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>SGE</h1>"
				+ "<h2>Sistema de Gestión Energética</h2>"
				+ "</body>"
				+ "</html>";});
	
	}
	
}
