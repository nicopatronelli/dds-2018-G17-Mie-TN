package casosDePruebaMinimos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoInteligente;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

/*
 * CASO DE PRUEBA 2
 */
public class PruebaDispositivos {

	private static EntityManagerFactory emf;
	private static EntityManager manager;
	private static Dispositivo dispositivoInteligente;
	private static Administrador admin;
	
	@BeforeClass
	public static void initalize() throws CloneNotSupportedException {
		
		emf = Persistence.createEntityManagerFactory("SGE");
		manager = emf.createEntityManager();
		
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
		dispositivoInteligente = (DispositivoInteligente) admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		
		simularFuncionamientoDispositivo();
		
		manager.getTransaction().begin();
		manager.persist(dispositivoInteligente);
		manager.getTransaction().commit();
		
	}

	@Test
	public void mostrarEstadosPorLosQuePasoUnDispositivoInteligente() {
		
		dispositivoInteligente = manager.find(DispositivoInteligente.class, dispositivoInteligente.getId());
		System.out.println("Los estados por los que paso el dispositivo inteligente son:" + dispositivoInteligente.getHistorial().toString());
		
	}
	
	@Test
	public void modificarUnAtributoDeUnDispositivo() {
		
		System.out.println("El nombre actual del dispositivo es " + dispositivoInteligente.getNombre());
		dispositivoInteligente.cambiarNombre("Mi Dispositivo");
		manager.getTransaction().begin();
		manager.persist(dispositivoInteligente);
		manager.getTransaction().commit();
		dispositivoInteligente = manager.find(DispositivoInteligente.class, dispositivoInteligente.getId());
		System.out.println("El nuevo nombre del dispositivo es " + dispositivoInteligente.getNombre());
		
	}
	
	@AfterClass
	public static void liberarRecursos() {
		manager.close();
	}
	
	// Métodos auxiliares
	
	private static void simularFuncionamientoDispositivo() {
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.activarAhorroDeEnergia();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
	}
	
}
