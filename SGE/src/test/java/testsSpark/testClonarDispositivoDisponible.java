package testsSpark;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

public class testClonarDispositivoDisponible {
	
	Administrador admin;
	DispositivoEstandar de;
	DispositivoInteligente di;
	
	@Before
	public void init() throws CloneNotSupportedException {
		admin = new Administrador("Jim", "Beach", "Jimmy", "123", "FakeStreet 123", LocalDate.now(), null);
		de = admin.obtenerDispositivoEstandar("TV 21 Estandar", 2);
		di = admin.obtenerDispositivoInteligente("Lampara 15W Inteligente", new FabricanteSamsungMock("SAMSUNG-JD256"));
	}
	
	@Test
	public void test() {
		System.out.println("Datos del dispositivo estandar:" + de.horasDeUsoDiarias());
		System.out.println("Datos del dispositivo inteligente:" + di.nombre());
	}
	
}
