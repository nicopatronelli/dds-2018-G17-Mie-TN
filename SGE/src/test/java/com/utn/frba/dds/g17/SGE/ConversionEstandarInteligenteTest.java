package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoEstandar;
import usuarios.Administrador;

public class ConversionEstandarInteligenteTest {
	
	Administrador admin;
	DispositivoEstandar dispositivoEstandar;
	
	@Before
	public void init() throws CloneNotSupportedException {
		
		admin = new Administrador("Pepito");
		dispositivoEstandar = (DispositivoEstandar) admin.obtenerDispositivoEstandar("TV 33 Estandar", 4);
		dispositivoEstandar.adaptarDispositivo();

	}
	
	@Test
	public void testEstaAdaptado() {
		Assert.assertTrue(dispositivoEstandar.estaAdaptado());
	}
	
	@Test
	public void testDispositivoAdaptadoEstaEncendido() {
		dispositivoEstandar.encender();
		Assert.assertTrue(dispositivoEstandar.estaEncendido());
	}
	
	@Test
	public void testCambiarEstadoDispositivoAdaptado() {
		dispositivoEstandar.encender();
		dispositivoEstandar.activarAhorroDeEnergia();
		dispositivoEstandar.apagar();
		Assert.assertTrue(dispositivoEstandar.estaApagado());
	}
	
	@Test
	public void testConsumoInstantaneoDispositivoAdaptadoApagado() {
		// Si el dispositivo estandar adaptado está apagado su consumo es, obviamente, 0
		dispositivoEstandar.apagar();
		Assert.assertEquals(dispositivoEstandar.consumoInstantaneo(), 0, 0);
	}
	
	@Test 
	public void testConsumoInstantaneoDispositivoAdaptadoEncendido() {
		// Fijamos como 0.2 el consumo instantáneo para un dispositivo adaptado (ver clase FabricanteSGE)
		dispositivoEstandar.encender();
		Assert.assertEquals(dispositivoEstandar.consumoInstantaneo(), 0.2, 0);
	}

}
