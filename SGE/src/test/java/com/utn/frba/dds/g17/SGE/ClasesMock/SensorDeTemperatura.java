package com.utn.frba.dds.g17.SGE.ClasesMock;

import sensor.SensorObservado;

public class SensorDeTemperatura extends SensorObservado {
	
	public SensorDeTemperatura(int temperaturaActual) {
		super(temperaturaActual);
	}

	public void revisarTemperatura(int temperaturaAmbiente) {
		
		if (temperaturaAmbiente != this.magnitudMedida) {
			this.magnitudMedida = temperaturaAmbiente;
			System.out.println("La temperatura ambiente cambio a " + temperaturaAmbiente);
			super.notificarObservadores();
		}
		
	} // fin revisarTemperatura()
	
	public int getTemperaturaActual() {
		return magnitudMedida;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.magnitudMedida = temperaturaActual;
	}
	
}