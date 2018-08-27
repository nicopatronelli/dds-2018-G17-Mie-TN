package actuadores;

import dispositivos.Dispositivo;

// Los actuadores son los comandos (patrón command) 
public abstract class Actuador {
	
	protected Dispositivo dispositivoInteligente;
	
	protected Actuador(Dispositivo dispositivoInteligente){
		this.dispositivoInteligente = dispositivoInteligente;
	}
	
	abstract public void ejecutarActuador();
	
}
