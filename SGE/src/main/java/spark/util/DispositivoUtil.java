package spark.util;

import hibernate.RepositorioClientes;
import hibernate.RepositorioDispositivos;
import spark.Request;
import usuarios.Cliente;

import static spark.util.RequestUtil.obtenerUsuarioActual;

import java.util.List;

import dispositivos.Dispositivo;

public class DispositivoUtil {

	public static void encenderDispositivo(Request request) {	
    	String idDispositivo = request.queryParams("idDispositivo");
    	RepositorioDispositivos repoDispositivos = new RepositorioDispositivos();
    	repoDispositivos.abrir();
    	Dispositivo dispositivo = repoDispositivos.recuperarPorId(idDispositivo);
    	dispositivo.encender();
    	repoDispositivos.actualizar(dispositivo);
	}
	
	public static void apagarDispositivo(Request request) {	
    	String idDispositivo = request.queryParams("idDispositivo");
    	RepositorioDispositivos repoDispositivos = new RepositorioDispositivos();
    	repoDispositivos.abrir();
    	Dispositivo dispositivo = repoDispositivos.recuperarPorId(idDispositivo);
    	dispositivo.apagar();
    	repoDispositivos.actualizar(dispositivo);
	}
	
	public static void activarAhorroDeEnergiaDispositivo(Request request) {	
    	String idDispositivo = request.queryParams("idDispositivo");
    	RepositorioDispositivos repoDispositivos = new RepositorioDispositivos();
    	repoDispositivos.abrir();
    	Dispositivo dispositivo = repoDispositivos.recuperarPorId(idDispositivo);
    	dispositivo.activarAhorroDeEnergia();
    	repoDispositivos.actualizar(dispositivo);
	}
	
}
