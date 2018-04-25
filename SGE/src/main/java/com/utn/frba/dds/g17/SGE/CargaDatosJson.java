package com.utn.frba.dds.g17.SGE;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CargaDatosJson {
	
	public static List<Cliente> getClientesJson() throws IOException{
				
		JsonParser parser = new JsonParser();
		FileReader fr = new FileReader("..\\SGE\\RepositorioJson\\clientes.json");
		JsonElement archivoClientesJson = parser.parse(fr);
		
		return getClientes(archivoClientesJson);
		
	}
	
	private static List<Cliente> getClientes(JsonElement archivoClientesJson) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		JsonElement repoClienteJsonElement = archivoClientesJson.getAsJsonObject().get("clientes");
		JsonArray clientesJsonArray = repoClienteJsonElement.getAsJsonArray();
		java.util.Iterator<JsonElement> iter = clientesJsonArray.iterator();
		
		while (iter.hasNext()) {
			JsonElement clienteJsonElement = iter.next();
			Cliente cliente = generarCliente(clienteJsonElement);
			clientes.add(cliente);
		}
		
		return clientes;
		
	}
	
	private static Cliente generarCliente(JsonElement clienteJsonElement) {
		
		Cliente cliente = new Cliente();
		
		cliente.setApellido(clienteJsonElement.getAsJsonObject().get("apellido").toString());
		cliente.setNombre(clienteJsonElement.getAsJsonObject().get("nombre").toString());
		cliente.setNombreUsuario(clienteJsonElement.getAsJsonObject().get("nombreUsuario").toString());
		cliente.setpassword(clienteJsonElement.getAsJsonObject().get("password").toString());
		cliente.setTipoDocumento(clienteJsonElement.getAsJsonObject().get("tipoDocumento").toString());
		cliente.setNroDocumento(clienteJsonElement.getAsJsonObject().get("nroDocumento").getAsInt());
		cliente.setTelefono(clienteJsonElement.getAsJsonObject().get("telefono").toString());
		cliente.setDomicilioServicio(clienteJsonElement.getAsJsonObject().get("domicilioServicio").toString());
		String fechaAltaServicio = clienteJsonElement.getAsJsonObject().get("fechaAltaServicio").toString().replaceAll("\"", "");
		cliente.setFechaAltaServicio(new DateTime(fechaAltaServicio));

		Categoria categoria = generarCategoria(clienteJsonElement);
		cliente.setCategoria(categoria);
		
		List<Dispositivo> dispositivos = generarListaDispositivos(clienteJsonElement);
		cliente.setDispositivos(dispositivos);
		
		return cliente;
		
	}
	
	private static List<Dispositivo> generarListaDispositivos(JsonElement clienteJsonElement) {
		
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		
		JsonElement listaDispositivosClienteJsonElement = clienteJsonElement.getAsJsonObject().get("dispositivos");
		JsonArray dispositivosJsonArray = listaDispositivosClienteJsonElement.getAsJsonArray();
		java.util.Iterator<JsonElement> iter = dispositivosJsonArray.iterator();
		while (iter.hasNext()) {
			JsonElement dispositivoElement = iter.next();
			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setNombreGenerico(dispositivoElement.getAsJsonObject().get("nombreGenerico").toString());
			dispositivo.setConsumoPorHora(dispositivoElement.getAsJsonObject().get("consumoPorHora").getAsInt());
			dispositivo.setEncendido(dispositivoElement.getAsJsonObject().get("encendido").getAsBoolean());
			dispositivos.add(dispositivo);
		}
		
		return dispositivos;
		
	}

	private static Categoria generarCategoria(JsonElement clienteJsonElement) {
		Categoria categoria = new Categoria();
		JsonElement categoriaJsonElement = clienteJsonElement.getAsJsonObject().get("categoria");
		categoria.setCargoFijo(categoriaJsonElement.getAsJsonObject().get("cargoFijo").getAsDouble());
		categoria.setCargoVariable(categoriaJsonElement.getAsJsonObject().get("cargoVariable").getAsDouble());
		return categoria;
	}
	
	public static List<Administrador> getAdministradoresJson() throws IOException{
		
		JsonParser parser = new JsonParser();
		FileReader fr = new FileReader("..\\SGE\\RepositorioJson\\administradores.json");
		JsonElement archivoAdministradoresJson = parser.parse(fr);
		
		return getAdministradores(archivoAdministradoresJson);
		
	}

	private static List<Administrador> getAdministradores(JsonElement archivoAdministradoresJson) {
		
		List<Administrador> administradores = new ArrayList<Administrador>();
		
		JsonElement repoAdministradorJsonElement = archivoAdministradoresJson.getAsJsonObject().get("administradores");
		JsonArray administradoresJsonArray = repoAdministradorJsonElement.getAsJsonArray();
		java.util.Iterator<JsonElement> iter = administradoresJsonArray.iterator();
		
		while (iter.hasNext()) {
			JsonElement administradorJsonElement = iter.next();
			Administrador administrador = generarAdministrador(administradorJsonElement);
			administradores.add(administrador);
		}
		
		return administradores;
	}

	private static Administrador generarAdministrador(JsonElement administradorJsonElement) {
		
		Administrador administrador = new Administrador();
		
		administrador.setApellido(administradorJsonElement.getAsJsonObject().get("apellido").toString());
		administrador.setNombre(administradorJsonElement.getAsJsonObject().get("nombre").toString());
		administrador.setNombreUsuario(administradorJsonElement.getAsJsonObject().get("nombreUsuario").toString());
		administrador.setpassword(administradorJsonElement.getAsJsonObject().get("password").toString());
		administrador.setDomicilio(administradorJsonElement.getAsJsonObject().get("domicilio").toString());
		
		String fechaAltaUsuarioSistema = administradorJsonElement.getAsJsonObject().get("fechaAltaUsuarioSistema").toString().replaceAll("\"", "");
		administrador.setFechaAltaUsuarioSistema(new DateTime(fechaAltaUsuarioSistema));

		administrador.setNroIdentificacion(administradorJsonElement.getAsJsonObject().get("nroIdentificacion").getAsInt());
		
		return administrador;
		
	}

}
