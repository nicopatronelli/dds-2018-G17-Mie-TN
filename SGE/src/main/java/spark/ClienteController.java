package spark;

import static spark.RequestUtil.*;
import static spark.Spark.staticFiles;
import static spark.ClienteUtil.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import hibernate.RepositorioClientes;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

public class ClienteController {
	
	static RepositorioClientes repoClientes = null;
	
    public static Route listarDispositivos = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("dispositivos", recuperarDispositivos(request));
    	request.session().attribute("currentUser", obtenerUsuarioActual(request));
        return ViewUtil.render(request, model, "/velocity/dispositivos.html");
    };
    
    // Simplemente mostramos la pantalla de Simplex 
    public static Route serveSimplexPage = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("ejecutarSimplex", false);
    	model.put("hogarEficiente", false);
    	request.session().attribute("currentUser", obtenerUsuarioActual(request));
        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
    // Mostramos los resultados de ejecución del Simplex en la pantalla de Simplex 
    public static Route ejecutarSimplex = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("ejecutarSimplex", true);
    	model.put("hogarEficiente", false);
    	model.put("horasRecomendadas", ejecutarSimplex(request));
    	model.put("dispositivos", recuperarDispositivos(request));
    	request.session().attribute("currentUser", obtenerUsuarioActual(request));

        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
    // Mostramos los resultados del hogar eficiente en la pantalla de Simplex 
    public static Route hogarEficiente = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("ejecutarSimplex", false);
    	model.put("hogarEficiente", true);
    	model.put("resultadoHogarEficiente", hogarEficiente(request));
    	request.session().attribute("currentUser", obtenerUsuarioActual(request));

        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
    public static Route serveCargaArchivoDispositivosPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
    	model.put("botonCargaPresionado", false);
    	model.put("cargaExitosa", false);
        return ViewUtil.render(request, model, "/velocity/carga_archivo_dispositivos.html");
    };    
    
    public static Route cargarArchivoDispositivos = (Request request, Response response) -> {
    	
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist
    	
        Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        
        try (InputStream input = request.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        
        // El archivo subido está en tempFile
        DispositivoArchivo[] dispositivosCargados = CargaDatosJson.cargarDispositivos(tempFile.toString());
        RepositorioClientes repoClientes = new RepositorioClientes();
        repoClientes.abrir();
        Cliente cliente = repoClientes.recuperarPorUsuario(obtenerUsuarioActual(request));
        Administrador admin = new Administrador("Administrador temporal");
        // Obtenemos los dispositivos y los registramos en el domicilio principal del cliente
        for(int i = 0; i < dispositivosCargados.length; i++) {
        	if (dispositivosCargados[i].esInteligente()) {
        		cliente.domicilioPrincipal().registrarDispositivo(
        				admin.obtenerDispositivoInteligente(dispositivosCargados[i].nombreGenerico(), new FabricanteSamsungMock("SAMSUNG-JD256")));
        	}else { // Sino, es estándar
        		cliente.domicilioPrincipal().registrarDispositivo(
        				admin.obtenerDispositivoEstandar(dispositivosCargados[i].nombreGenerico(), dispositivosCargados[i].horasDeUsoDiarias()));
        	}
        } // FIN for 
        
        // Por último, persistimos los cambios en el cliente
        repoClientes.actualizar(cliente);
    	Map<String, Object> model = new HashMap<>();
    	model.put("botonCargaPresionado", true);
    	model.put("cargaExitosa", true);
        return ViewUtil.render(request, model, "/velocity/carga_archivo_dispositivos.html");
    	
    };  
}
