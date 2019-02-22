package spark;

import static spark.RequestUtil.obtenerParamUsuario;
import static spark.RequestUtil.obtenerUsuarioActual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hibernate.RepositorioAdmins;
import hibernate.RepositorioClientes;
import usuarios.Administrador;

public class DomiciliosController {
	
    public static Route serveDomiciliosPage = (Request request, Response response) -> {
        
    	RepositorioClientes repoClientes = new RepositorioClientes();
    	repoClientes.abrir();
    	Map<String, Object> model = new HashMap<>();
    	model.put("clientes", repoClientes.recuperarTodosLosClientes());
        return ViewUtil.render(request, model, "/velocity/consumo_domicilios.html");
    };
    
    public static Route serveSimplexPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
}
