package com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores;

import com.utn.frba.dds.g17.SGE.ActuadorObservador;
import com.utn.frba.dds.g17.SGE.DispositivoInteligente;

public class AireAcondicionadoTemperaturaObservador implements ActuadorObservador {
	
	SensorDeTemperatura sensorDeTemperatura;
	DispositivoInteligente aireAcondicionadoInteligente;
	
	public AireAcondicionadoTemperaturaObservador(SensorDeTemperatura sensorDeTemperatura,
			DispositivoInteligente aireAcondicionadoInteligente) {
		this.sensorDeTemperatura = sensorDeTemperatura;
		this.aireAcondicionadoInteligente = aireAcondicionadoInteligente;
	}

	@Override
	public void ejecutarActuador() {
			
			// Supongamos que enviamos la orden de apagar el aire si la temperatura ambiente es menor a 20
			if (this.sensorDeTemperatura.getTemperaturaActual() < 20) {
				aireAcondicionadoInteligente.apagar();
			}
			// Si la temperatura esta entre 20 y 25 grados ponemos al aire en modo ahorro de energía
			else if(this.sensorDeTemperatura.getTemperaturaActual() > 20 && 
					this.sensorDeTemperatura.getTemperaturaActual() < 25) {
				aireAcondicionadoInteligente.activarAhorroDeEnergia();
			}else {
			// En caso contrario (si la temperatura es mayor a 25), encendemos el aire
				aireAcondicionadoInteligente.encender();
			}
				
	}
	
}
