package casosDePruebaMinimos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoInteligente;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

/*
 * CASO DE PRUEBA 2
 */
public class PruebaDispositivos {

	private static DispositivoInteligente dispositivoInteligente;
	private static Administrador admin;
	
	@BeforeClass
	public static void initalize() throws CloneNotSupportedException {
		admin = crearAdministrador();
		dispositivoInteligente = admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		simularFuncionamientoDispositivo();
		dispositivoInteligente.guardar();
	}

	@Test
	public void mostrarEstadosPorLosQuePasoUnDispositivoInteligente() {
		dispositivoInteligente = (DispositivoInteligente) dispositivoInteligente.recuperar(dispositivoInteligente.getId());
		System.out.println("Los intervalos que estuvo encendido el dispositivo inteligente son:" 
		+ dispositivoInteligente.intervalosEncendido().toString());
	}
	
	@Test
	public void modificarUnAtributoDeUnDispositivo() {
		System.out.println("El nombre actual del dispositivo es " + dispositivoInteligente.getNombre());
		dispositivoInteligente.cambiarNombre("Mi Dispositivo");
		dispositivoInteligente.guardar();
		dispositivoInteligente = (DispositivoInteligente) dispositivoInteligente.recuperar(dispositivoInteligente.getId());
		System.out.println("El nuevo nombre del dispositivo es " + dispositivoInteligente.getNombre());
	}
	
	
	/*
	 *  Métodos auxiliares
	 */
	
	private static Administrador crearAdministrador() throws CloneNotSupportedException {
		return new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
	}
	
	private static void simularFuncionamientoDispositivo() {
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.activarAhorroDeEnergia();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
		dispositivoInteligente.encender();
		dispositivoInteligente.apagar();
	}
	
}
