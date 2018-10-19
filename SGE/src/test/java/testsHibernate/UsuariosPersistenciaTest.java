package testsHibernate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hibernate.Manager;
import usuarios.Administrador;
import usuarios.Cliente;

public class UsuariosPersistenciaTest {
	
	private static Manager manager; // Para Hibernate
	private static Administrador admin;
	private static Cliente clientes[];
	private static List<Cliente> clientesDeLaBase;
	
	@BeforeClass
	public static void persistirYRecuperarClientes() {
		
		// Cargamos un administrador y 2 clientes (de Clientes.json)
		admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.of(2018, 3, 18));
		clientes = admin.cargarClientes();
		
		// Los persistimos en la base de datos 
		manager = new Manager();
		manager.iniciarTransaccion();
		manager.persistir(admin); // Persisto el administrador 
		manager.persistirLista(Arrays.asList(clientes)); // Persisto los clientes
		manager.cerrarTransaccion();
		
		// Recuperamos los clientes de la base de datos e imprimimos sus datos por pantalla
		clientesDeLaBase = (List<Cliente>) manager.ejecutarQuery("FROM Cliente").getResultList();
		mostrarClientesDeLaBase(clientesDeLaBase);
		//manager.cerrar();
		
	}

	@Test
	public void unClienteCoincide() {
		Assert.assertTrue(clientesDeLaBase.get(1).equals(clientes[1]));
	}
	
	@Test
	public void cantidadDeClientesCoincide() {
		Assert.assertTrue(clientesDeLaBase.size() == clientes.length);
	}	
	
	@Test
	public void recuperarClientePorPK() {
		Cliente cliente1 = manager.find(Cliente.class, clientes[0].getId());
		System.out.println("El cliente cuya PK es " + clientes[0].getId() + " es el " + cliente1.toString());
		Assert.assertTrue(cliente1.equals(clientes[0])); 
	}
	
	@AfterClass
	public static void after() {
		manager.cerrar();
	}
	
	// Métodos auxiliares 
	
	private static void mostrarClientesDeLaBase(List<Cliente> clientesDeLaBase) {
		System.out.println("****************************************");
		System.out.println("Los clientes en la base de datos son:");
		for(Cliente cliente : clientesDeLaBase) {
			System.out.println(cliente.toString());
		}
		System.out.println("****************************************");
	}
	
}
