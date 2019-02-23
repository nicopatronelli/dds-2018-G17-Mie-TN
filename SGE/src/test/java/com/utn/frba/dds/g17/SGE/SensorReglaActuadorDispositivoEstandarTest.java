package com.utn.frba.dds.g17.SGE;

import org.junit.Before;
import org.junit.Test;

import actuadores.ActuadorEncender;
import dispositivos.Dispositivo;
import reglas.ReglaTemperaturaMayorA20Grados;
import sensores.SensorObservado;
import usuarios.Administrador;

import org.junit.Assert;

public class SensorReglaActuadorDispositivoEstandarTest {
	
	Administrador admin;
	Dispositivo ventiladorEstandar;
	SensorObservado sensor;
	ReglaTemperaturaMayorA20Grados regla;
	ActuadorEncender actuador;
	
	/* Probamos el mismo caso que en el test SensorReglaActuadorDispositivoInteligenteTest pero 
	 * ahora con un dispositivo estandar que vamos a adaptar => se comporta como un dispositivo 
	 * inteligente 
	 */
	
	@Before
	public void init() throws CloneNotSupportedException {
		
		admin = new Administrador("Pepito");
		// El ventilador inicia apagado 
		ventiladorEstandar = admin.obtenerDispositivoEstandar("Ventilador pie Estandar", 2);
		ventiladorEstandar.adaptarDispositivo(); // Adaptamos el dispositivo 
		sensor = new SensorObservado("Temperatura", 15); // Supongamos que la temperatura ambiente actual es de 15°C
		regla = new ReglaTemperaturaMayorA20Grados();
		actuador = new ActuadorEncender(ventiladorEstandar);
		sensor.agregarRegla(regla);
		regla.agregarActuador(actuador);
		
	}
	
	@Test
	public void testEncenderVentiladorSiLaTemperaturaEsMayorA20() {
		sensor.nuevaMedicion(25); // Ahora la temperatura ambiente aumento a 25°C => sensor notifica a sus observadores (regla)
		Assert.assertTrue(ventiladorEstandar.estaEncendido());
	}
	
	@Test 
	public void testElVentiladorSigueApagadoSiLaTemperaturaEsMenorOIgualA20() {
		// Ahora la temperatura ambiente aumento a 18°C => sensor notifica a sus observadores (regla), pero como es menor a 20° no se dispara el actuador
		sensor.nuevaMedicion(18); 
		Assert.assertTrue(ventiladorEstandar.estaApagado());
	}
	
}
