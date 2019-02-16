package utils;

import java.time.LocalDate;

import dispositivos.DispositivoInteligente;
import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

import static utils.AdministradorUtil.adminDePrueba;

public class ClienteUtil {
	
	public static Cliente crearClienteA() throws CloneNotSupportedException {
		Categoria categoriaR2 = Categoria.R2;
		Posicion posicion = new Posicion(250, 350);
		DomicilioServicio domicilioPrincipal = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR2, posicion);
		Cliente nuevoCliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "queen123");
		nuevoCliente.agregarDomicilio(domicilioPrincipal);
		Transformador transformador = new Transformador(100, 200, 1);
		//domicilioPrincipal.setTransformador(transformador);
		
		// Creamos algunos dispositivos
		Administrador admin = adminDePrueba();
		DispositivoInteligente dispositivoInteligenteA = admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
/*		DispositivoInteligente dispositivoInteligenteB = admin.obtenerDispositivoInteligente("Heladera con freezer Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		DispositivoInteligente dispositivoInteligenteC = admin.obtenerDispositivoInteligente("Lampara 11W Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));*/
		
		// Añadimos los dispositivos al domicilio principal
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteA);
/*		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteB);
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteC);*/
		
		return nuevoCliente;
	}
	
}
