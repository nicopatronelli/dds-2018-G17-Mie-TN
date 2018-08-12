package com.utn.frba.dds.g17.SGE;

import org.junit.Before;
import org.junit.Test;

import com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores.AireAcondicionadoTemperaturaObservador;
import com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores.SensorDeTemperatura;

import org.junit.Assert;

public class SensoresActuadoresTest {
	
	DispositivoInteligente aireInteligente;
	SensorDeTemperatura sensorDeTemperatura;
	AireAcondicionadoTemperaturaObservador aireInteligenteObservador;
	
	@Before
	public void init() {
		aireInteligente = new DispositivoInteligente("SAMSUNG CP3284X");
		// Encendemos el aire
		aireInteligente.encender(); 
		sensorDeTemperatura = new SensorDeTemperatura();
		aireInteligenteObservador = new AireAcondicionadoTemperaturaObservador(sensorDeTemperatura, aireInteligente);
		sensorDeTemperatura.agregarObservador(aireInteligenteObservador);
	}
	
	@Test
	public void apagarAireSiLaTemperaturaEsMenorA20() {
		sensorDeTemperatura.revisarTemperatura(18);
		Assert.assertTrue(aireInteligente.estaApagado());
	}
	
	@Test
	public void ponerAireEnAhorroDeEnergiaSiLaTemperaturaEstaEntre20y25() {
		sensorDeTemperatura.revisarTemperatura(23);
		Assert.assertTrue(aireInteligente.estaEnAhorroDeEnergia());
	}
	
	@Test
	public void encenderAireSiLaTemperaturaEsMayorA25() {
		sensorDeTemperatura.revisarTemperatura(35);
		Assert.assertTrue(aireInteligente.estaEncendido());
	}
	
}
