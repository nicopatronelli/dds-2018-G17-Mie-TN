package testsHibernate;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoInteligente;
import hibernate.RepositorioAdmins;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

public class GestorDispositivosBDTest {
	
	RepositorioAdmins repoAdmins;
	Administrador admin;
	
	@Before
	public void init() {
		repoAdmins = new RepositorioAdmins();
		repoAdmins.abrir();
		admin = repoAdmins.recuperarPorUsuario("Jimmy");
	}
	
	@Test
	public void test() throws CloneNotSupportedException {
		
		System.out.println("El nombre y apellido del admin son: " + admin.getNombreApellido());
		System.out.println("El nombre de un dispositivo es: " + admin.dispositivosDisponibles().get(1).nombre());
		admin.gestor().setDispositivosDisponibles(admin.dispositivosDisponibles());
		
		DispositivoInteligente di = admin.obtenerDispositivoInteligente("Plancha pelo Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		
		System.out.println("El nombre del dispositivo inteligente es: " + di.nombre());
		
	}
	
}
