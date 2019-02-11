package mocks;

import sensores.SensorObservado;

public class SensorDeTemperatura extends SensorObservado {
	
	public SensorDeTemperatura(int temperaturaActual) {
		super(temperaturaActual);
	}

	public void revisarTemperatura(int temperaturaAmbiente) {
		
		if (temperaturaAmbiente != this.magnitudMedida) {
			this.magnitudMedida = temperaturaAmbiente;
			System.out.println("La temperatura ambiente cambio a " + temperaturaAmbiente);
			super.notificarReglas();
		}
		
	} // fin revisarTemperatura()
	
	public double getTemperaturaActual() {
		return magnitudMedida;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.magnitudMedida = temperaturaActual;
	}
	
}