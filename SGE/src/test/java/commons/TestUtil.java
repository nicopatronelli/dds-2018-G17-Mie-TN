package commons;

import java.time.LocalDate;

import dispositivos.DispositivoInteligente;
import usuarios.Administrador;

public class TestUtil {
	
	public static Administrador crearAdministrador() throws CloneNotSupportedException {
		return new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
	}
	
	public static void simularFuncionamientoDispositivo(DispositivoInteligente dispositivoInteligente) {
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
