package testsHibernate;

import static utils.AdministradorUtil.adminDePrueba;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoInteligente;
import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import hibernate.RepositorioClientes;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

public class HogaresConsumosTest {
	
	RepositorioClientes repoClientes;
	Cliente cliente;
	
	@Before
	public void init() {
		repoClientes = new RepositorioClientes();
		repoClientes.abrir();
		cliente = repoClientes.recuperarPorUsuario("Johnny");
	}
	@Test
	public void test() {
		
		System.out.println("El resultado es " + cliente.domicilioPrincipal().consumoInteligenteDomicilio());

	}

}
