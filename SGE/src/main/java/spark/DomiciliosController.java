package spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hibernate.RepositorioClientes;

public class DomiciliosController {
	
    public static Route serveDomiciliosPage = (Request request, Response response) -> {
        
    	RepositorioClientes repoClientes = new RepositorioClientes();
    	repoClientes.abrir();
    	Map<String, Object> model = new HashMap<>();
    	model.put("clientes", repoClientes.recuperarTodosLosClientes());
        return ViewUtil.render(request, model, "/velocity/consumo_domicilios.html");
    };
    
    public static Route serveAltaDispositivoPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
    
    public static Route serveEstadoDomicilioPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/estado_domicilio.html");
    };
    
    public static Route serveSimplexPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
    public static Route serveConsumoPeriodoPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/consumo_periodo.html");
    };
    
    public static Route serveMapaPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/mapa.html");
    };
    
}
