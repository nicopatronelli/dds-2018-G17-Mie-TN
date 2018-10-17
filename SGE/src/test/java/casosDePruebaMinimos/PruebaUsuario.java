package casosDePruebaMinimos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import hibernate.Manager;
import usuarios.Cliente;

/*
 * CASO DE PRUEBA 1 
 */
public class PruebaUsuario {
	
	EntityManagerFactory emf;
	EntityManager manager;
	Posicion nuevaPosicion;
	Cliente cliente;
	
	@Before
	public void initialize() {
		
		emf = Persistence.createEntityManagerFactory("SGE");
		manager = emf.createEntityManager();
		
		// Creo un nuevo cliente y lo persisto
		cliente = crearCliente();
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		// Recupero el cliente recién persistido
		cliente = manager.find(Cliente.class, cliente.getId());
		
		// Cambio la posicion de un domicilio 
		nuevaPosicion = new Posicion(450, 880);
		cliente.domicilios().get(0).nuevaPosicion(nuevaPosicion);
		
		// Grabo los cambios
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();
		
	}
	
	@Test
	public void cambioDeGeoposicionamientoCorrecto() {
		cliente = manager.find(Cliente.class, cliente.getId());
		System.out.println("El cliente es " + cliente.toString());
		Assert.assertTrue(cliente.domicilios().get(0).getPosicion().equals(nuevaPosicion));
	}
	
	@After
	public void after() {
		manager.close();
	}
	
	// Métodos auxiliares
	
	private Cliente crearCliente() {
		Categoria categoriaR1 = new Categoria(18.7, 0.644);
		Posicion geoposicionamiento = new Posicion(250, 350);
		DomicilioServicio domicilio = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR1, geoposicionamiento);
		Cliente nuevoCliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "queen123");
		nuevoCliente.agregarDomicilio(domicilio);
		return nuevoCliente;
	}
	
}