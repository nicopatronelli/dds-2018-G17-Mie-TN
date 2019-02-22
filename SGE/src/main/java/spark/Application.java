package spark;

import static spark.RequestUtil.obtenerUsuarioActual;
import static spark.Spark.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.MultipartConfigElement;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import hibernate.RepositorioClientes;
import mocks.FabricanteSamsungMock;
import spark.template.velocity.VelocityTemplateEngine;
import usuarios.Administrador;
import usuarios.Cliente;

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
		
		staticFiles.location("/public");
        staticFiles.externalLocation("upload");
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
		
		// Ejecución de simplex (CLIENTE)
		get("/simplex", ClienteController.serveSimplexPage);
		get("/simplex/ejecutar", ClienteController.ejecutarSimplex);
		get("/simplex/eficiente", ClienteController.hogarEficiente);
		
		// Carga de archivo de dispositivos (CLIENTE)
		get("/dispositivo/archivo", ClienteController.serveCargaArchivoDispositivosPage);
		post("/dispositivo/archivo", ClienteController.cargarArchivoDispositivos);

		// Listado de reglas activas (CLIENTE)
		get("/reglas", ClienteController.serveReglasActivasPage);
		
		// Listar hogares y consumos (ADMIN)
		get("/admin/domicilios", DomiciliosController.serveDomiciliosPage);

		// Alta nuevo dispositivo (ADMIN)
		get("/dispositivo/alta", DispositivoController.serveAltaDispositivoPage);
		post("/dispositivo/alta", DispositivoController.altaNuevoDispositivo);
		
		// Reportes (ADMIN)
		get("/reporte/consumoTotalPorhogar", AdminController.servePageReporteConsumoTotalPorDomicilio);		
		get("/reporte/consumoTotalPorhogarResultado", AdminController.generarReporteConsumoTotalPorDomicilio);
		
		get("/reporte/consumoPromedioPorTransformador", AdminController.servePageReporteConsumoPromedioPorTransformador);		
		get("/reporte/consumoPromedioPorTransformadorResultado", AdminController.generarReporteConsumoPromedioPorTransformador);
		
		// Mapa público (acceso libre sin necesidad de loguearse)
		get("/mapa", AdminController.serveMapaPage);
		
		// Página no encontrada (Not found)
        get("*", UtilController.serveNotFoundPage);
		
		/*** ESTOS NO ***/
		
		get("/domicilio/estado", DomiciliosController.serveEstadoDomicilioPage);
		
		get("/consumo/periodo", DomiciliosController.serveConsumoPeriodoPage);
		
	}
	
}
