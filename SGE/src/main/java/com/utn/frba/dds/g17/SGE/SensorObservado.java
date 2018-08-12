package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;

public abstract class SensorObservado {
	
	protected ArrayList<ActuadorObservador> listaObservadores;
	
	public void agregarObservador(ActuadorObservador observador) {
		listaObservadores.add(observador);
	}
	
	public void quitarObservador(ActuadorObservador observador) {
		listaObservadores.remove(observador);
	}
	
	public void notificarObservadores() {
		for(ActuadorObservador observador : listaObservadores) {
			observador.ejecutarActuador();
		}
	}
	
}
