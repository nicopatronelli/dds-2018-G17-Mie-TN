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
	
	private static Dispositivo dispositivoEstandar;
	private static Administrador admin;
	
	@BeforeClass
	public static void initialize() throws CloneNotSupportedException {
		
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now(), null);
		dispositivoEstandar = (DispositivoEstandar) admin.obtenerDispositivoEstandar("TV 33 Estandar", 4);
		//dispositivoEstandar.guardar();

	}
	
	@Test
	public void unDispositivoEstandarPersistidoSinConvertirNoEstaAdaptado() {
	
		//dispositivoEstandar = dispositivoEstandar.recuperar(dispositivoEstandar.id());
		System.out.println("El dispositivo estandar está adaptado: " + dispositivoEstandar.estaAdaptado());
		Assert.assertFalse(dispositivoEstandar.estaAdaptado());
	}
	
	@Test
	public void sePersisteLaAdaptacionDeUnDispositivoEstandar() throws CloneNotSupportedException {
		
		dispositivoEstandar.adaptarDispositivo();
		//dispositivoEstandar.guardar();
		//dispositivoEstandar = dispositivoEstandar.recuperar(dispositivoEstandar.id());
		System.out.println("El dispositivo estandar está adaptado: " + dispositivoEstandar.estaAdaptado());
		Assert.assertTrue(dispositivoEstandar.estaAdaptado());
	}
	
}
