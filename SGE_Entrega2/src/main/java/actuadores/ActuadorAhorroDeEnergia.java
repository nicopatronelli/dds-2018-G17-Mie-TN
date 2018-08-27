package actuadores;

import dispositivos.Dispositivo;

public class ActuadorAhorroDeEnergia extends Actuador {
		
	public ActuadorAhorroDeEnergia(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.activarAhorroDeEnergia();
	}
	
}
