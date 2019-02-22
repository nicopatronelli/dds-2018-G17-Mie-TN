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
import hibernate.RepositorioAdmins;
import hibernate.RepositorioClientes;
import hibernate.RepositorioTransformadores;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;
import geoposicionamiento.Transformador;

public class AdminController {
	
	public static Route servePageReporteConsumoTotalPorDomicilio = (Request request, Response response) ->{
    	Map<String, Object> model = new HashMap<>();
    	model.put("reporteGenerado", false);
    	//request.session().attribute("currentUser", obtenerUsuarioActual(request));
        return ViewUtil.render(request, model, "/velocity/reporte_consumo_total_por_hogar.html");
	};
	
    public static Route generarReporteConsumoTotalPorDomicilio = (Request request, Response response) -> {
    	
    	RepositorioClientes repoClientes = new RepositorioClientes();
    	repoClientes.abrir();
    	List<Cliente> clientes = repoClientes.recuperarTodosLosClientes();

    	String fechaDesde = obtenerFechaDesde(request);
    	String fechaHasta = obtenerFechaHasta(request);
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("reporteGenerado", true);
    	model.put("clientes", clientes);
    	model.put("fechaDesde", fechaDesde);
    	model.put("fechaHasta", fechaHasta);
    	
    	//request.session().attribute("currentUser", obtenerUsuarioActual(request));
    	
        return ViewUtil.render(request, model, "/velocity/reporte_consumo_total_por_hogar.html");
    };
    
	public static Route servePageReporteConsumoPromedioPorTransformador = (Request request, Response response) ->{
    	Map<String, Object> model = new HashMap<>();
    	model.put("reporteGenerado", false);
    	//request.session().attribute("currentUser", obtenerUsuarioActual(request));
        return ViewUtil.render(request, model, "/velocity/reporte_consumo_promedio_por_transformador.html");
	};
	
    public static Route generarReporteConsumoPromedioPorTransformador = (Request request, Response response) -> {
    	
    	RepositorioAdmins repoAdmins = new RepositorioAdmins();
    	repoAdmins.abrir();
    	Administrador admin = repoAdmins.recuperarPorUsuario(obtenerUsuarioActual(request));

    	String fechaDesde = obtenerFechaDesde(request);
    	String fechaHasta = obtenerFechaHasta(request);
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("transformadores", admin.transformadores());
    	model.put("fechaDesde", fechaDesde);
    	model.put("fechaHasta", fechaHasta);
    	model.put("reporteGenerado", true);
    	//request.session().attribute("currentUser", obtenerUsuarioActual(request));
    	
        return ViewUtil.render(request, model, "/velocity/reporte_consumo_promedio_por_transformador.html");
    };
    
    public static Route serveMapaPage = (Request request, Response response) -> {

/*    	RepositorioTransformadores repoTrans = new RepositorioTransformadores();
    	repoTrans.abrir();
    	ArrayList<Transformador> transformadores = (ArrayList<Transformador>) repoTrans.recuperarTransformadores();
    	
    	Map<String, Object> model = new HashMap<>();
    	//transformadores.forEach(transformador -> System.out.println("Datos de cada transformador: " + transformador));
    	System.out.println("La cantidad de transformadores es: " + transformadores.size());
    	*/
    	
    	RepositorioAdmins repoAdmins = new RepositorioAdmins();
    	repoAdmins.abrir();
    	Administrador admin = repoAdmins.recuperarAdminPrincipal();
    	Map<String, Object> model = new HashMap<>();
    	model.put("transformador", admin.transformadores().get(1));
    	//model.put("transformadores", admin.transformadores());
    	//model.put("consumo", 4);
    	ArrayList<Double> lista = new ArrayList<Double>();
    	//lista.add(-34.598494);
    	//lista.add(-58.420186);
    	//lista.add(12.5);
    	//model.put("lista", lista);
    	//model.put("latitud", -34.598494);
    	//model.put("longitud", -58.420186);
    	//model.put("consumo", 12.5);
    	System.out.println("El consumo instantaneo del traffo es: " + admin.transformadores().get(1).consumoInstantaneo());
    	
        return ViewUtil.render(request, model, "/velocity/mapa.html");
    };
    
}
