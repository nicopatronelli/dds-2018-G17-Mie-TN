package reglas;

import javax.persistence.Entity;

import reglas.ReglaObservador;

@Entity
public class ReglaTemperaturaMayorA20Grados extends ReglaObservador {
		
	public ReglaTemperaturaMayorA20Grados(){
		super("Encender aire si la temperatura es mayor a 20C");
	}

	@Override
	protected boolean seCumpleRegla(double temperaturaMedida) {
		// Por ejemplo, definemos una regla para saber si la temperatura medida es mayor a 20 ºC
		return temperaturaMedida > 20;
	}
	
}
