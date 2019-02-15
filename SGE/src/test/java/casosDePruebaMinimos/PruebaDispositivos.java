package casosDePruebaMinimos;

import static utils.AdministradorUtil.adminDePrueba;
import static utils.DispositivoUtil.simularFuncionamientoDispositivo;

import org.junit.BeforeClass;
import org.junit.Test;

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
		admin = adminDePrueba();
		dispositivoInteligente = admin.obtenerDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		simularFuncionamientoDispositivo(dispositivoInteligente);
		dispositivoInteligente.guardar();
	}

	@Test
	public void mostrarEstadosPorLosQuePasoUnDispositivoInteligente() {
		dispositivoInteligente = (DispositivoInteligente) dispositivoInteligente.recuperar(dispositivoInteligente.id());
		System.out.println("Los intervalos que estuvo encendido el dispositivo inteligente son:" 
		+ dispositivoInteligente.intervalosEncendido().toString());
	}
	
	@Test
	public void modificarUnAtributoDeUnDispositivo() {
		System.out.println("El nombre actual del dispositivo es " + dispositivoInteligente.nombre());
		dispositivoInteligente.cambiarNombre("Mi Dispositivo");
		dispositivoInteligente.guardar();
		dispositivoInteligente = (DispositivoInteligente) dispositivoInteligente.recuperar(dispositivoInteligente.id());
		System.out.println("El nuevo nombre del dispositivo es " + dispositivoInteligente.nombre());
	}
		
}
