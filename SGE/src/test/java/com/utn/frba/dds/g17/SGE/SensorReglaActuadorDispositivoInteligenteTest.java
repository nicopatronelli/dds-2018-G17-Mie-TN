package com.utn.frba.dds.g17.SGE;

import org.junit.Before;
import org.junit.Test;

import actuadores.ActuadorEncender;
import dispositivos.Dispositivo;
import mocks.FabricanteSamsungMock;
import mocks.ReglaTemperaturaMayor20Grados;
import mocks.SensorDeTemperatura;
import usuarios.Administrador;

import org.junit.Assert;

public class SensorReglaActuadorDispositivoInteligenteTest {
	
	Administrador admin;
	Dispositivo aireInteligente;
	SensorDeTemperatura sensor;
	ReglaTemperaturaMayor20Grados regla;
	ActuadorEncender actuador;
	
	@Before
	public void init() throws CloneNotSupportedException {
		
		admin = new Administrador("Pepito");
		// El aire inicia apagado 
		aireInteligente = admin.obtenerDispositivoInteligente("Aire 2200 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-CP3284X"));
		sensor = new SensorDeTemperatura(15); // Supongamos que la temperatura ambiente actual es de 15°C
		regla = new ReglaTemperaturaMayor20Grados();
		actuador = new ActuadorEncender(aireInteligente);
		sensor.agregarObservador(regla);
		regla.agregarActuador(actuador);
		
	}
	
	@Test
	public void testEncenderAireSiLaTemperaturaEsMayorA20() {
		sensor.revisarTemperatura(25); // Ahora la temperatura ambiente aumento a 25°C => sensor notifica a sus observadores (regla)
		Assert.assertTrue(aireInteligente.estaEncendido());
	}
	
	@Test 
	public void testElAireSigueApagadoSiLaTemperaturaEsMenorOIgualA20() {
		// Ahora la temperatura ambiente aumento a 18°C => sensor notifica a sus observadores (regla), pero como es menor a 20° no se dispara el actuador
		sensor.revisarTemperatura(18); 
		Assert.assertTrue(aireInteligente.estaApagado());
	}
	
}
