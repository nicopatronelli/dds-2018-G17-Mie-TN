package actuadores;

import dispositivos.Dispositivo;

public class ActuadorEncender extends Actuador {
	
	public ActuadorEncender(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.encender();
	}
	
}
