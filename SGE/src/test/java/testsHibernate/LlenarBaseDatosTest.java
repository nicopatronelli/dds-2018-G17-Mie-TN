package testsHibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import actuadores.ActuadorEncender;
import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import hibernate.RepositorioAdmins;
import hibernate.RepositorioClientes;
import hibernate.RepositorioSensores;
import mocks.FabricanteSamsungMock;
import reglas.ReglaEncenderLuzDeNoche;
import reglas.ReglaObservador;
import reglas.ReglaTemperaturaMayorA20Grados;
import sensores.SensorObservado;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.DispositivoDisponible;

public class LlenarBaseDatosTest {
	
	private Administrador admin;
	private Cliente cliente, clienteB;
	private SensorObservado sensorDeTemperatura, sensorDeLuminosidadB;
	
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
		
		/*** INICIO - Cliente A ***/
		
		// Domicilio 
		Categoria categoriaR2 = Categoria.R2;
		Posicion posicion = new Posicion(-34.614810436, -58.450436775);
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
		
		// Creamos otro dispositivo inteligente 
		DispositivoInteligente dispositivoInteligenteC = admin.obtenerDispositivoInteligente("Lampara 15W Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipal.registrarDispositivo(dispositivoInteligenteC);
		
		// Añadimos un sensor, regla y actuador
		sensorDeTemperatura = new SensorObservado("Temperatura", 15);
		ReglaObservador regla = new ReglaTemperaturaMayorA20Grados();
		ActuadorEncender actuador = new ActuadorEncender(dispositivoInteligenteB);
		sensorDeTemperatura.agregarRegla(regla);
		regla.agregarActuador(actuador);
		
		// Añadimos un sensor, regla y actuador
		sensorDeLuminosidadB = new SensorObservado("Luminosidad", 80);
		ReglaObservador reglaB = new ReglaEncenderLuzDeNoche();
		ActuadorEncender actuadorB = new ActuadorEncender(dispositivoInteligenteC);
		sensorDeLuminosidadB.agregarRegla(reglaB);
		regla.agregarActuador(actuadorB);
		
		// Creamos un dispositivo estandar
		DispositivoEstandar dispositivoEstandarA = admin.obtenerDispositivoEstandar("Ventilador pie Estandar", 4);
		domicilioPrincipal.registrarDispositivo(dispositivoEstandarA);
		
		/*** FIN - Cliente A ***/
		
		/*** INICIO - Cliente B ***/
		
		// Domicilio 
		Categoria categoriaB = Categoria.R3;
		Posicion posicionB = new Posicion(-34.632960275, -58.376549522);
		DomicilioServicio domicilioPrincipalB = new DomicilioServicio("4585-1627", LocalDate.of(2018, 06, 12), categoriaB, posicionB);
		
		// Cliente
		clienteB = new Cliente("Brian", "May", "DNI", 11252789, "BrianMay", "queen123");
		clienteB.agregarDomicilio(domicilioPrincipalB);
		
		// Creamos un dispositivo inteligente 
		DispositivoInteligente dispositivoInteligenteB1 = admin.obtenerDispositivoInteligente("Lampara 20W Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipalB.registrarDispositivo(dispositivoInteligenteB1);
		dispositivoInteligenteB1.encender();
		
		// Creamos otro dispositivo inteligente 
		DispositivoInteligente dispositivoInteligenteB2 = admin.obtenerDispositivoInteligente("Aire 3500 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		domicilioPrincipalB.registrarDispositivo(dispositivoInteligenteB2);
		
		// Creamos un dispositivo estandar
		DispositivoEstandar dispositivoEstandarB1 = admin.obtenerDispositivoEstandar("TV 29 Estandar", 2);
		domicilioPrincipal.registrarDispositivo(dispositivoEstandarB1);
		
		/*** FIN - Cliente B ***/
		
		/*** INICIO - Zonas y transformadores ***/
		// Zonas y transformadores
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json"); 
		admin.asignarTransformador(cliente);
		admin.asignarTransformador(clienteB);
		
		/*** FIN - Zonas y transformadores ***/

	}
	
	@Test
	public void test() {
		RepositorioAdmins repoAdmins = new RepositorioAdmins();
		RepositorioClientes repoClientes = new RepositorioClientes();
		RepositorioSensores repoSensores = new RepositorioSensores();
		repoAdmins.abrir();
		repoClientes.abrir();
		repoSensores.abrir();
		repoAdmins.guardar(admin);
		repoClientes.guardar(cliente);
		repoClientes.guardar(clienteB);
		repoSensores.guardar(sensorDeTemperatura);
		repoSensores.guardar(sensorDeLuminosidadB);
		repoClientes.cerrar();
		repoAdmins.cerrar();
		repoSensores.cerrar();

		Assert.assertTrue(true);
	}

}
