package casosDePruebaMinimos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import hibernate.Manager;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

public class PruebaTransformadores {
	
	Administrador admin;
	EntityManagerFactory emf;
	EntityManager manager;
	
	@Before
	public void persistirTransformadores() throws CloneNotSupportedException {
			
		emf = Persistence.createEntityManagerFactory("SGE");
		manager = emf.createEntityManager();
		
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.of(2018, 3, 18));
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		// Cargamos los transformadores activos que nos envia el ENRE en un json y los distribuimos en sus respectivas zonas
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json"); 
		
		// Los persistimos en la base de datos 
		manager.getTransaction().begin();
		manager.persist(admin);
		manager.getTransaction().commit();

	}
	
	@Test
	public void cantidadTransformadores() {
		
	}
	
}
