package mocks;

import reglas.ReglaObservador;

public class ReglaTemperaturaMayor20Grados extends ReglaObservador {
	
	public ReglaTemperaturaMayor20Grados(){
		super();
	}

	@Override
	protected boolean seCumpleRegla(double temperaturaMedida) {
		// Por ejemplo, definemos una regla para saber si la temperatura medida es mayor a 20 ºC
		return temperaturaMedida > 20;
	}
	
}
