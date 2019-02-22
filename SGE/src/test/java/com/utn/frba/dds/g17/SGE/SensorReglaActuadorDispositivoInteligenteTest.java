package com.utn.frba.dds.g17.SGE;

import org.junit.Before;
import org.junit.Test;

import actuadores.ActuadorEncender;
import dispositivos.Dispositivo;
import hibernate.RepositorioAdmins;
import mocks.FabricanteSamsungMock;
import reglas.ReglaTemperaturaMayorA20Grados;
import sensores.SensorObservado;
import usuarios.Administrador;

import org.junit.Assert;

public class SensorReglaActuadorDispositivoInteligenteTest {
	
	Administrador admin;
	Dispositivo aireInteligente;
	SensorObservado sensor;
	ReglaTemperaturaMayorA20Grados regla;
	ActuadorEncender actuador;
	
	@Before
	public void init() throws CloneNotSupportedException {
		
		RepositorioAdmins repoAdmins = new RepositorioAdmins();
		repoAdmins.abrir();
		Administrador admin = repoAdmins.recuperarAdminPrincipal();
		admin.inicializarGestorDispositivos();
		// El aire inicia apagado 
		aireInteligente = admin.obtenerDispositivoInteligente("Aire 2200 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-CP3284X"));
		sensor = new SensorObservado("Temperatura", 15); // Supongamos que la temperatura ambiente actual es de 15°C
		regla = new ReglaTemperaturaMayorA20Grados();
		actuador = new ActuadorEncender(aireInteligente);
		sensor.agregarRegla(regla);
		regla.agregarActuador(actuador);
		
	}
	
	@Test
	public void testEncenderAireSiLaTemperaturaEsMayorA20() {
		sensor.nuevaMedicion(25); // Ahora la temperatura ambiente aumento a 25°C => sensor notifica a sus observadores (regla)
		Assert.assertTrue(aireInteligente.estaEncendido());
	}
	
	@Test 
	public void testElAireSigueApagadoSiLaTemperaturaEsMenorOIgualA20() {
		// Ahora la temperatura ambiente aumento a 18°C => sensor notifica a sus observadores (regla), pero como es menor a 20° no se dispara el actuador
		sensor.nuevaMedicion(18); 
		Assert.assertTrue(aireInteligente.estaApagado());
	}
	
}
