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

import dispositivos.DispositivoInteligente;
import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

public class HogaresConsumosTest {
	
	@Before
	public void init() throws CloneNotSupportedException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		EntityManager manager = emf.createEntityManager();
		String query = "FROM DomicilioServicio";
		List<DomicilioServicio> domicilios = manager.createQuery(query).getResultList();
		DomicilioServicio domicilio = domicilios.get(1);
		System.out.println("La posición del domicilio es " + domicilio.getPosicion().getLongitud());
	}

	@Test
	public void test() {

	}

}
