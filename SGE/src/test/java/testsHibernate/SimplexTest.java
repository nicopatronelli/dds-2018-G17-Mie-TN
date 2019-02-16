package testsHibernate;

import org.junit.Before;
import org.junit.Test;

import hibernate.RepositorioClientes;
import usuarios.Cliente;

public class SimplexTest {
	
	RepositorioClientes repoClientes;
	Cliente cliente;
	double[] resultados;
	
	@Before
	public void init() {
		repoClientes = new RepositorioClientes();
		repoClientes.abrir();
		cliente = repoClientes.recuperarPorUsuario("Johnny");
		resultados = cliente.recomendacionConsumo(cliente.domicilioPrincipal());
	}
	@Test
	public void test() {
		
		for(double resultado : resultados) {
			System.out.println("El resultado es " + resultado);
		}
		
	}
}
