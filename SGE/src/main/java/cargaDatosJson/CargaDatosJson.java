package cargaDatosJson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import spark.DispositivoArchivo;
import usuarios.Cliente;
import usuarios.DispositivoDisponible;

public class CargaDatosJson {
		
	public static String archivoAString(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}
		
	public static Cliente[] cargarClientes(String pathJsonClientes) {
		
		String jsonClientes = null;
		try {
			jsonClientes = CargaDatosJson.archivoAString(pathJsonClientes);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		Cliente[] clientes = gson.fromJson(jsonClientes, Cliente[].class);
		
		return clientes;
	}
	
	public static Transformador[] cargarTransformadores(String pathJsonTransformadores) {
		
		String jsonTransformadores = null;
		try {
			jsonTransformadores = CargaDatosJson.archivoAString(pathJsonTransformadores);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		Transformador[] transformadores = gson.fromJson(jsonTransformadores, Transformador[].class);
		
		return transformadores;
	}	
	
	public static Zona[] cargarZonas(String pathJsonZonas) {
		
		String jsonZonas = null;
		try {
			jsonZonas = CargaDatosJson.archivoAString(pathJsonZonas);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		Zona[] zonas = gson.fromJson(jsonZonas, Zona[].class);
		
		return zonas;
	}
	
	public static DispositivoInteligente[] cargarDispositivosInteligentes(String pathJsonDispInt) {
		
		String jsonDispInt = null;
		try {
			jsonDispInt = CargaDatosJson.archivoAString(pathJsonDispInt);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		DispositivoInteligente[] dispositivosInteligentes = gson.fromJson(jsonDispInt, DispositivoInteligente[].class);
		
		return dispositivosInteligentes;
	}
	
	public static DispositivoEstandar[] cargarDispositivosEstandares(String pathJsonDispEst) {
		
		String jsonDispEst = null;
		try {
			jsonDispEst = CargaDatosJson.archivoAString(pathJsonDispEst);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		DispositivoEstandar[] dispositivosEstandares = gson.fromJson(jsonDispEst, DispositivoEstandar[].class);
		
		return dispositivosEstandares;
	}
	
	public static DispositivoDisponible[] cargarDispositivosDisponibles(String pathJsonDisp) {
		
		String jsonDisp = null;
		try {
			jsonDisp = CargaDatosJson.archivoAString(pathJsonDisp);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el archivo JSON");
		}
		
		Gson gson = new Gson();
		DispositivoDisponible[] dispositivosDisponibles = gson.fromJson(jsonDisp, DispositivoDisponible[].class);
		
		return dispositivosDisponibles;
	}

}
