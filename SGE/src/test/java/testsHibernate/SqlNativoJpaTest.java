package testsHibernate;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import antlr.collections.List;
import hibernate.RepositorioClientes;

public class SqlNativoJpaTest {
	
	ArrayList<String> lista;
	
	@Before
	public void init() {
		RepositorioClientes repoClientes = new RepositorioClientes();
		repoClientes.abrir();
		lista = (ArrayList<String>) repoClientes.reglasActivasPorUsuario("Johnny");
	}
	
	@Test
	public void test() {
		System.out.println("Las reglas activas son: " + lista.get(0));
	}
	
}
