package casosDePruebaMinimos;

import static utils.AdministradorUtil.adminDePrueba;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoInteligente;
import usuarios.Administrador;

public class ConsumoPromedioDispositivo {
	
	/* @Nota: Antes de ejecutar este test se debe cargar el script XXX en la BD
	 * 
	 */
	
	private DispositivoInteligente dispIntel;
	
	@Before
	public void borrador() throws CloneNotSupportedException {
		/* Ya cargamos un dispositivo inteligente en la base de datos con Id = 1 y simulamos
		 * su funcionamiento a lo largo de un período (un mes) cargando estados en la tabla 
		 * estados_dispositivos_inteligentes
		 */
		Administrador admin = adminDePrueba();
		dispIntel = (DispositivoInteligente) admin.recuperarDispositivoPorId(DispositivoInteligente.class, 1L);
		dispIntel.inicializarEntityManager();
	}
	
	@Test
	public void consumoPromedioDispositivo() {
		Double consumo = dispIntel.consumoEntre("2018-10-18", "2018-11-18");
		System.out.println("El consumo promedio del " + dispIntel.getNombre() + " es " + consumo/30 + " kwh");
	}
	
}
