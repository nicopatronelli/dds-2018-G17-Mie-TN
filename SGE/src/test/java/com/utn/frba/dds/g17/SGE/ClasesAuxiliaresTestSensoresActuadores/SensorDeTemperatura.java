package com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores;

import java.util.ArrayList;

import com.utn.frba.dds.g17.SGE.ActuadorObservador;
import com.utn.frba.dds.g17.SGE.SensorObservado;

public class SensorDeTemperatura extends SensorObservado {
	
	int temperaturaActual; 
	
	public SensorDeTemperatura(int temperaturaActual) {
		this.listaObservadores = new ArrayList<ActuadorObservador>();
		this.temperaturaActual = temperaturaActual; 
	}
	
	public void revisarTemperatura(int temperaturaAmbiente) {
		
		if (temperaturaAmbiente != this.temperaturaActual) {
			this.temperaturaActual = temperaturaAmbiente;
			super.notificarObservadores();
		}
		
	}
	
	// Getters y Setters
	
	public int getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}
	
}