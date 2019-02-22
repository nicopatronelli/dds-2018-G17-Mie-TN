package actuadores;

import javax.persistence.Entity;

import dispositivos.Dispositivo;

@Entity
public class ActuadorApagar extends Actuador {
	
	public ActuadorApagar() {
		// Constructor vacío para Hibernate
	}
	
	public ActuadorApagar(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.apagar();
	}
	
}
