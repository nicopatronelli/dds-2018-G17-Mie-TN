package spark;

import static spark.RequestUtil.*;
import static spark.DispositivoUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dispositivos.Dispositivo;
import hibernate.RepositorioAdmins;
import hibernate.RepositorioDispositivos;
import usuarios.Administrador;
import usuarios.DispositivoDisponible;

import static spark.FlagNuevoDispositivoDisponible.*;

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
    	model.put("dispositivoCargado", false);
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
    
    public static Route altaNuevoDispositivo = (Request request, Response response) -> {

    	RepositorioAdmins repoAdmins = new RepositorioAdmins();
    	repoAdmins.abrir();
 
    	Administrador admin = repoAdmins.recuperarPorUsuario(obtenerUsuarioActual(request));
    	DispositivoDisponible nuevoDisponible = new DispositivoDisponible(
    			obtenerNombreDispositivo(request), Double.parseDouble(obtenerConsumoKwh(request)), 
    			Integer.parseInt(obtenerUsoMinimo(request)), Integer.parseInt(obtenerUsoMaximo(request)),
    			Boolean.parseBoolean(obtenerEsBajoConsumo(request)), Boolean.parseBoolean(obtenerEsInteligente(request)));
    	
    	admin.agregarDispositivoDisponible(nuevoDisponible);
    	repoAdmins.actualizar(admin);
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("dispositivoCargado", true);
    	
    	// Marcamos a true el flag de que se cargó un nuevo dispositivo disponible para instanciar 
    	//FlagNuevoDispositivoDisponible.setFlagTrue();
    	//FlagNuevoDispositivoDisponible.setUsuarioAdmin(obtenerUsuarioActual(request));
    	
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
}
