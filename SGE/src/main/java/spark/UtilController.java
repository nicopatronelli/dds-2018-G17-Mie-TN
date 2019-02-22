package spark;

import static spark.ClienteUtil.recuperarDispositivos;
import static spark.RequestUtil.obtenerUsuarioActual;

import java.util.HashMap;
import java.util.Map;

import hibernate.RepositorioClientes;

public class UtilController {
	
	static RepositorioClientes repoClientes = null;
	
    public static Route serveNotFoundPage = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/not_found.html");
    };
	
}
