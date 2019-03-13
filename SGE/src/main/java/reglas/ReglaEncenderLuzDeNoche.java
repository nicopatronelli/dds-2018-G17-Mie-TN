package reglas;

import javax.persistence.Entity;

import reglas.ReglaObservador;

@Entity
public class ReglaEncenderLuzDeNoche extends ReglaObservador {
		
	public ReglaEncenderLuzDeNoche(){
		super("Encender lámparas del patio cuando la luminosidad sea menor al 50%");
	}

	@Override
	protected boolean seCumpleRegla(double luminosidad) {
		return luminosidad < 50;
	}
	
}
