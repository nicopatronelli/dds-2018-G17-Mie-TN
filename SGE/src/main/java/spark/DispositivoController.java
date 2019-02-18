package spark;

import static spark.RequestUtil.*;
import static spark.DispositivoUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dispositivos.Dispositivo;
import hibernate.RepositorioDispositivos;

public class DispositivoController {
	
    public static Route encender = (Request request, Response response) -> {
    
    	//System.out.println("El usuario actual es " + obtenerUsuarioActual(request));
    	encenderDispositivo(request);
    	response.redirect("/cliente/dispositivos");
    	return null;
    };
    
    public static Route apagar = (Request request, Response response) -> {
    	
    	apagarDispositivo(request);
    	response.redirect("/cliente/dispositivos");
    	return null;
    };
    
    public static Route ahorro = (Request request, Response response) -> {
    	
    	activarAhorroDeEnergiaDispositivo(request);
    	response.redirect("/cliente/dispositivos");
    	return null;
    };
    
    public static Route serveAltaDispositivoPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
    
    public static Route altaNuevoDispositivo = (Request request, Response response) -> {
    	
    	System.out.println("Los datos del nuevo dispositivo son: " + request.queryParams("nombre") 
    	+ " " + request.queryParams("kwh"));
    	
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
}
