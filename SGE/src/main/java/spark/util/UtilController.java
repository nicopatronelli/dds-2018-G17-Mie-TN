package spark.util;

import static spark.util.ClienteUtil.recuperarDispositivos;
import static spark.util.RequestUtil.obtenerUsuarioActual;

import java.util.HashMap;
import java.util.Map;

import hibernate.RepositorioClientes;
import spark.Request;
import spark.Response;
import spark.Route;

public class UtilController {
	
	static RepositorioClientes repoClientes = null;
	
    public static Route serveNotFoundPage = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/not_found.html");
    };
	
}