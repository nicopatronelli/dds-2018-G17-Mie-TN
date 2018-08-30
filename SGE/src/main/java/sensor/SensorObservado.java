package sensor;

import java.util.ArrayList;

import regla.ReglaObservador;

public abstract class SensorObservado {
	
	protected int magnitudMedida; // Cada sensor mide una unica magnitud física 
	protected ArrayList<ReglaObservador> listaObservadores; // El sensor tiene una lista de reglas
	
	protected SensorObservado(int magnitudMedida) {
		this.magnitudMedida = magnitudMedida;
		this.listaObservadores = new ArrayList<ReglaObservador>();
	}
	
	public void agregarObservador(ReglaObservador observador) {
		listaObservadores.add(observador);
	}
	
	public void quitarObservador(ReglaObservador observador) {
		listaObservadores.remove(observador);
	}
	
	public void notificarObservadores() {
		listaObservadores.forEach(observador->observador.revisarRegla(magnitudMedida));
	}
	
}
