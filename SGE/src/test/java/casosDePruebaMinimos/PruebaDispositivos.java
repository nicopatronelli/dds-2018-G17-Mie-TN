package casosDePruebaMinimos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import usuarios.Administrador;
import usuarios.Cliente;

/*
 * CASO DE PRUEBA 2
 */
public class PruebaDispositivos {

	EntityManagerFactory emf;
	EntityManager manager;
	Cliente cliente;
	Dispositivo dispositivoEstandar;
	Administrador admin;
	
	@Before
	public void initalize() throws CloneNotSupportedException {
		
		emf = Persistence.createEntityManagerFactory("SGE");
		manager = emf.createEntityManager();
		
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.of(2018, 3, 18));
		dispositivoEstandar = (DispositivoEstandar) admin.obtenerDispositivoEstandar("LCD 40 Estandar", 2);
		
		dispositivoEstandar.adaptarDispositivo();
		
		manager.getTransaction().begin();
		manager.persist(dispositivoEstandar);
		manager.getTransaction().commit();
		
	}
	
	@Test
	public void elDispositivoEstandarNoEstaAdaptado() {
		
		//manager.createQuery("FROM dispositivos_estandares WHERE )
		
	}
	
	
}
