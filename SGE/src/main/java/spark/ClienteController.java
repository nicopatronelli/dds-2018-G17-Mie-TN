package spark;

import static spark.RequestUtil.*;
import static spark.ClienteUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dispositivos.Dispositivo;
import hibernate.RepositorioClientes;

public class ClienteController {
	
	static RepositorioClientes repoClientes = null;
	
    public static Route listarDispositivos = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("dispositivos", recuperarDispositivos(request));
    	request.session().attribute("currentUser", obtenerUsuarioActual(request));
        return ViewUtil.render(request, model, "/velocity/dispositivos.html");
    };
    
}
