package testsHibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;

import static utils.ClienteUtil.crearClienteA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import usuarios.DispositivoDisponible;

public class LlenarBaseDatosTest {
	
	private Administrador admin;
	private Cliente cliente;
	
	@Before 
	public void init() throws CloneNotSupportedException {
		
		/***** INICIO - Admin *****/
		// Cargamos los dispositivos disponibles desde los JSON 
		Map<String, DispositivoDisponible> dispositivosDisponibles = new HashMap<String, DispositivoDisponible>();
		DispositivoDisponible[] dispositivosInteligentes = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosInteligentes.json");
		
		for( int i = 0; i < dispositivosInteligentes.length; i++ ) {
			dispositivosDisponibles.put(dispositivosInteligentes[i].nombre(), dispositivosInteligentes[i]);
		}
		
		DispositivoDisponible[] dispositivosEstandares = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosEstandares.json");
		
		for( int i = 0; i < dispositivosEstandares.length; i++ ) {
			dispositivosDisponibles.put(dispositivosEstandares[i].nombre(), dispositivosEstandares[i]);
		}
		// En este punto, el map dispositivosDisponibles está cargado con los dispositivos disponibles y sus keys
		
		ArrayList<DispositivoDisponible> listaDisponibles = new ArrayList<DispositivoDisponible>(dispositivosDisponibles.values());
		admin = new Administrador("Jim", "Beach", "Jimmy", "123", "FakeStreet 123", LocalDate.now(), listaDisponibles);
		admin.inicializarGestorDispositivos();
		
		/***** FIN - Admin *****/
		
		// Domicilio 
		Categoria categoriaR2 = Categoria.R2;
		Posicion posicion = new Posicion(250, 350);
		DomicilioServicio domicilioPrincipal = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR2, posicion);
		
		// Cliente
		cliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "123");
		cliente.agregarDomicilio(domicilioPrincipal);
		
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

		Assert.assertTrue(true);
	}

}
