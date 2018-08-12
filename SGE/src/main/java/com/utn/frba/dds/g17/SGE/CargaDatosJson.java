package com.utn.frba.dds.g17.SGE;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class CargaDatosJson {
	
	private static String archivoAString(String path) throws IOException {
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
	
}
