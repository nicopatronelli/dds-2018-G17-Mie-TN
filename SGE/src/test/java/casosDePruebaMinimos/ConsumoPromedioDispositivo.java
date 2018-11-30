package casosDePruebaMinimos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.junit.Before;
import org.junit.Test;

import dispositivos.Dispositivo;
import dispositivos.DispositivoInteligente;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;

import static commons.TestUtil.*;

public class ConsumoPromedioDispositivo {
	
	private DispositivoInteligente dispIntel;
	
	@Before
	public void borrador() throws CloneNotSupportedException {
		Administrador admin = crearAdministrador();
		dispIntel = (DispositivoInteligente) admin.recuperarDispositivoPorId(DispositivoInteligente.class, 1L);
	}
	
	@Test
	public void consumoPromedioDispositivo() {
		Double consumo = dispIntel.consumoEntre("2018-10-18", "2018-10-26");
		System.out.println("El consumo promedio es " + consumo);
	}
	
}
