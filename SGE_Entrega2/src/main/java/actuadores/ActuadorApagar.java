package actuadores;

import dispositivos.Dispositivo;

public class ActuadorApagar extends Actuador {
	
	public ActuadorApagar(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.apagar();
	}
	
}
