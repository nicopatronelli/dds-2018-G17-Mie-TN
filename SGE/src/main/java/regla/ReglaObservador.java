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
		if ( seCumpleRegla(magnitudMedida) ) // Template Method
			actuadores.forEach(actuador -> actuador.ejecutarActuador());
	} 
	
	abstract protected boolean seCumpleRegla(int magnitudMedida);
	
}
