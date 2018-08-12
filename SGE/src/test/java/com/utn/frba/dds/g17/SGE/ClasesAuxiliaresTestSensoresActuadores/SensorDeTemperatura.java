package com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores;

import java.util.ArrayList;

import com.utn.frba.dds.g17.SGE.ActuadorObservador;
import com.utn.frba.dds.g17.SGE.SensorObservado;

public class SensorDeTemperatura extends SensorObservado {
	
	int temperaturaActual; 
	
	public SensorDeTemperatura() {
		this.listaObservadores = new ArrayList<ActuadorObservador>();
		this.temperaturaActual = 0;
	}
	
	public void revisarTemperatura(int temperaturaAmbiente) {
		
		if (temperaturaAmbiente != this.temperaturaActual) {
			this.temperaturaActual = temperaturaAmbiente;
			super.notificarObservadores();
		}
		
	}
	
	public int getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}
	
}