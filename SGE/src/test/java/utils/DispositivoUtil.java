package utils;

import dispositivos.DispositivoInteligente;

public class DispositivoUtil {
	
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
