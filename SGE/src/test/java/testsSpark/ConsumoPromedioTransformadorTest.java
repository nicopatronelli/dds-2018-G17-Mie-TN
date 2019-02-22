package testsSpark;

import org.junit.Before;
import org.junit.Test;

import commons.Fecha;
import hibernate.RepositorioAdmins;
import usuarios.Administrador;

public class ConsumoPromedioTransformadorTest {
	
	Administrador admin;
	
	@Before
	public void init() {
		RepositorioAdmins repoAdmins = new RepositorioAdmins();
		repoAdmins.abrir();
		admin = repoAdmins.recuperarAdminPrincipal();

	}
	
	@Test
	public void test() {
		//System.out.println("Los dias entre fechas son: " + Fecha.diasEntreFechas("2018-12-01", "2018-12-31"));
		System.out.println("Los dias entre fechas son: " + Fecha.diasEntreFechas("2018/05/02", "2018/05/08"));
		//System.out.println("El resultado es: " + admin.transformadores().get(0).consumoPromedioEntre("2018/12/05", "2018/12/31"));
		
	}
	
}
