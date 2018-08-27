package regla;

import java.util.ArrayList;
import java.util.List;

import actuadores.Actuador;

public abstract class ReglaObservador {
	
	private List<Actuador> actuadores;
	
	protected ReglaObservador(){
		this.actuadores = new ArrayList<Actuador>();
	}
	
	public void agregarActuador(Actuador actuador) {
		actuadores.add(actuador);
	}
	
	public void revisarRegla(int magnitudMedida) {
		
		// Template Method
		if ( seCumpleRegla(magnitudMedida) ) {
			for (Actuador actuador : actuadores) {
				actuador.ejecutarActuador();
			}
		}

	} // fin revisarRegla()
	
	abstract protected boolean seCumpleRegla(int magnitudMedida);
	
}
