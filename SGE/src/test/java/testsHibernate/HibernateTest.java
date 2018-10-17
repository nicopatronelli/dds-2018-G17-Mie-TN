package testsHibernate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hibernate.Manager;
import usuarios.Administrador;
import usuarios.Cliente;

public class HibernateTest {
	
	private Manager manager; // Para Hibernate
	Administrador admin;
	Cliente clientes[];
	
	@Before
	public void persistirClientesyAdministradores() {
		
		admin = new Administrador("Eduardo", "Coudet", "Chacho", "ChachoAkd", "Rosario 123", LocalDate.of(2018, 3, 18));
		clientes = admin.cargarClientes();
		
		manager = new Manager();
		
		manager.iniciarTransaccion();
		manager.persistir(admin); // Persisto el administrador 
		manager.persistirLista(Arrays.asList(clientes)); // Persisto los clientes
		manager.cerrarTransaccion();
				
	}
	
	@Test
	public void recuperarClientes() {
		
		List<Cliente> clientesDeLaBase = (List<Cliente>) manager.ejecutarQuery("FROM Cliente").getResultList();
		System.out.println("****************************************");
		System.out.println("Los clientes en la base de datos son:");
		for(Cliente cliente : clientesDeLaBase) {
			System.out.println(cliente.toString());
		}
		System.out.println("****************************************");
		
		
	}
	
	@Test
	public void recuperarAdministradores() {
		
		List<Administrador> administradoresDeLaBase = (List<Administrador>) manager.ejecutarQuery("FROM Administrador").getResultList();
		System.out.println("****************************************");
		System.out.println("Los administradores en la base de datos son:");
		for(Administrador admin : administradoresDeLaBase) {
			System.out.println(admin.toString());
		}
		System.out.println("****************************************");
	
	}
	
/*	@After
	public void cerrarManager() {
		manager.cerrar();
	}*/
	
}
