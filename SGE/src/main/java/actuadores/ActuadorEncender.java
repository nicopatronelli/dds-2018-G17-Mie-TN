package actuadores;

import javax.persistence.Entity;

import dispositivos.Dispositivo;

@Entity
public class ActuadorEncender extends Actuador {
	
	public ActuadorEncender() {
		// Constructor vacío para Hibernate
	}
	
	public ActuadorEncender(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.encender();
	}
	
}
