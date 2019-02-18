package testsHibernate;

import org.junit.Before;
import org.junit.Test;

import dispositivos.Dispositivo;
import hibernate.RepositorioClientes;
import usuarios.Cliente;

public class SimplexTest {
	
	RepositorioClientes repoClientes;
	Cliente cliente;
	double resultado;
	
	@Before
	public void init() {
		repoClientes = new RepositorioClientes();
		repoClientes.abrir();
		cliente = repoClientes.recuperarPorUsuario("Johnny");
		resultado = cliente.recomendacionHorasConsumo(cliente.domicilioPrincipal());
	}
	@Test
	public void test() {
		
		System.out.println("El resultado es " + resultado);
		for(Dispositivo dispositivo : cliente.domicilioPrincipal().dispositivos()) {
			System.out.println("Id: " + dispositivo.id() + " Nombre: " + dispositivo.nombre() + " Horas recomendadas: " + dispositivo.horasDeUsoRecomendadas());
		}
		
	}
}
