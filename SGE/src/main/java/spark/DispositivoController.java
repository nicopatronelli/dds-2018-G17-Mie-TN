package spark;

import static spark.RequestUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispositivoController {
	
	
    public static Route encender = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	
    	System.out.println("El id del dispositivo es " + request.queryParams("idDispositivo"));
    	System.out.println("El usuario actual es " + obtenerUsuarioActual(request));
    	
        return ViewUtil.render(request, model, "/velocity/dispositivos.html");
    };
    
	
    public static Route apagar = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	
    	System.out.println("El id del dispositivo es " + request.queryParams("idDispositivo"));
    	System.out.println("El usuario actual es " + obtenerUsuarioActual(request));
    	
        return ViewUtil.render(request, model, "/velocity/dispositivos.html");
    };
    
    public static Route ahorro = (Request request, Response response) -> {
    	
    	Map<String, Object> model = new HashMap<>();
    	
        return ViewUtil.render(request, model, "/velocity/dispositivos.html");
    };
    
    
    
    
}
