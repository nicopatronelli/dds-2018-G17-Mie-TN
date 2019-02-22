package actuadores;

import javax.persistence.Entity;

import dispositivos.Dispositivo;

@Entity
public class ActuadorAhorroDeEnergia extends Actuador {
		
	public ActuadorAhorroDeEnergia() {
		// Constructor vacío para Hibernate
	}
	
	public ActuadorAhorroDeEnergia(Dispositivo dispositivoInteligente) {
		super(dispositivoInteligente);
	}
	
	public void ejecutarActuador() {
		dispositivoInteligente.activarAhorroDeEnergia();
	}
	
}
