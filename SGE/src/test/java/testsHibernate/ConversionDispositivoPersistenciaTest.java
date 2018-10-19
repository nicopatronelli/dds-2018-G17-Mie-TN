package testsHibernate;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import usuarios.Administrador;

public class ConversionDispositivoPersistenciaTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager manager;
	private static Dispositivo dispositivoEstandar;
	private static Administrador admin;
	
	@BeforeClass
	public static void initialize() throws CloneNotSupportedException {
		
		emf = Persistence.createEntityManagerFactory("SGE");
		manager = emf.createEntityManager();
		
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
		dispositivoEstandar = (DispositivoEstandar) admin.obtenerDispositivoEstandar("TV 33 Estandar", 4);
		
		manager.getTransaction().begin();
		manager.persist(dispositivoEstandar);
		manager.getTransaction().commit();

	}
	
	@Test
	public void unDispositivoEstandarPersistidoSinConvertirNoEstaAdaptado() {
	
		dispositivoEstandar = manager.find(DispositivoEstandar.class, dispositivoEstandar.getId());
		//System.out.println("El dispositivo estandar está adaptado: " + dispositivoEstandar.estaAdaptado());
		Assert.assertFalse(dispositivoEstandar.estaAdaptado());
	}
	
	@Test
	public void sePersisteLaAdaptacionDeUnDispositivoEstandar() {
		
		dispositivoEstandar.adaptarDispositivo();
		dispositivoEstandar = manager.find(DispositivoEstandar.class, dispositivoEstandar.getId());
		//System.out.println("El dispositivo estandar está adaptado: " + dispositivoEstandar.estaAdaptado());
		Assert.assertTrue(dispositivoEstandar.estaAdaptado());
	}
	
	@AfterClass
	public static void after() {
		manager.close();
	}
	
}
