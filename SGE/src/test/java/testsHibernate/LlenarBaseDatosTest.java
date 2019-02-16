package testsHibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;

import static utils.ClienteUtil.crearClienteA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utils.AdministradorUtil.adminDePrueba;

import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import hibernate.RepositorioAdmins;
import hibernate.RepositorioClientes;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

public class LlenarBaseDatosTest {
	
	private Administrador admin;
	private Cliente cliente;
	
	@Before 
	public void init() throws CloneNotSupportedException {
		
		// Domicilio 
		Categoria categoriaR2 = Categoria.R2;
		Posicion posicion = new Posicion(250, 350);
		DomicilioServicio domicilioPrincipal = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR2, posicion);
		
		// Cliente
		cliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "123");
		cliente.agregarDomicilio(domicilioPrincipal);
		
		// Admin
		admin = new Administrador("Jim", "Beach", "Jimmy", "123", "FakeStreet 123", LocalDate.now());
		
		// Creamos un dispositivo inteligente 
		DispositivoInteligente dispositivoInteligenteA = admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteA);
		dispositivoInteligenteA.encender();
		
		// Creamos otro dispositivo inteligente 
		DispositivoInteligente dispositivoInteligenteB = admin.obtenerDispositivoInteligente("Aire 2200 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteB);
		dispositivoInteligenteB.encender();
		
		// Creamos un dispositivo estandar
		DispositivoEstandar dispositivoEstandarA = admin.obtenerDispositivoEstandar("Ventilador pie Estandar", 4);
		domicilioPrincipal.registrarDispositivo(dispositivoEstandarA);
		
		// Zonas y transformadores
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json"); 
		admin.asignarTransformador(cliente);
	}

	@Test
	public void test() {
		RepositorioAdmins repoAdmins = new RepositorioAdmins();
		RepositorioClientes repoClientes = new RepositorioClientes();
		repoAdmins.abrir();
		repoClientes.abrir();
		repoAdmins.guardar(admin);
		repoClientes.guardar(cliente);
		repoClientes.cerrar();
		repoAdmins.cerrar();
		//repoClientes.cerrar();
		//repoClientes.abrir();
		//Cliente mismoCliente = repoClientes.recuperarPorId(1L);
		//admin.asignarTransformador(mismoCliente);
		//repoClientes.actualizar(mismoCliente);
		//System.out.println("Los datos del cliente son " + mismoCliente.toString());
		//repoClientes.borrar(mismoCliente);

		Assert.assertTrue(true);
	}

}
