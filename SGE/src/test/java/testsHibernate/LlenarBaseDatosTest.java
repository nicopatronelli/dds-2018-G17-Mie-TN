package testsHibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		cliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "queen123");
		cliente.agregarDomicilio(domicilioPrincipal);
		
		// Admin
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
		
		// Creamos algunos dispositivos
		DispositivoInteligente dispositivoInteligenteA = admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteA);
		
		// Zonas y transformadores
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json"); 
		//admin.asignarTransformador(cliente);
		//domicilioPrincipal.setTransformador(admin.getTransformadores().get(0));
	}

	@Test
	public void test() {
		RepositorioAdmins repoAdmins = new RepositorioAdmins();
		RepositorioClientes repoClientes = new RepositorioClientes();
		repoAdmins.abrir();
		repoAdmins.guardar(admin);
		repoAdmins.cerrar();
		repoClientes.abrir();
		repoClientes.guardar(cliente);
		repoClientes.cerrar();
		repoClientes.abrir();
		Cliente mismoCliente = repoClientes.recuperarPorId(1L);
		//System.out.println("Los datos del cliente son " + mismoCliente.toString());
		repoClientes.borrar(mismoCliente);
		repoClientes.cerrar();
		Assert.assertTrue(true);
	}

}
