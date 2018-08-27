package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utn.frba.dds.g17.SGE.ClasesMock.FabricanteSamsungMock;

import dispositivos.DispositivoInteligente;
import usuarios.Administrador;

public class EstadosDispositivoInteligenteTest {
	
	Administrador admin;
	DispositivoInteligente dispositivoInteligente;
	
	@Before
	public void initialize() throws CloneNotSupportedException {
		
		admin = new Administrador("Pepito");
		dispositivoInteligente = (DispositivoInteligente) admin.crearDispositivoInteligente("LED 32 Inteligente", 
				new FabricanteSamsungMock("SAMSUNG-JD256"));
		
	}
	
	@Test
	public void testDispositivoInteligenteIniciaApagado() {
		// Decidimos que todo dispositivo inteligente inicie en estado apagado
		Assert.assertTrue(dispositivoInteligente.estaApagado());
	}
	
	@Test
	public void testEncenderDispositivoApagado() {
		// Si el dispositivo está apagado y le envio el mensaje encender pasa a estado encendido
		dispositivoInteligente.encender();
		Assert.assertTrue(dispositivoInteligente.estaEncendido());
	}
	
	@Test
	public void testApagarDispositivoEncendido() {
		// Encendemos el dispositivo
		dispositivoInteligente.encender();
		// Si el dispositivo está encendido y le envio el mensaje apagar pasa a estado apagado
		dispositivoInteligente.apagar();
		Assert.assertTrue(dispositivoInteligente.estaApagado());
	}

	@Test
	public void testActivarModoAhorroEnergiaSiEstaApagado() {
		// Si el dispositivo está apagado y le envio el mensaje activarAhorroDeEnergia sigue apagado (no pasa nada)
		dispositivoInteligente.activarAhorroDeEnergia();
		Assert.assertTrue(dispositivoInteligente.estaApagado());
	}

	@Test
	public void testEncenderSiEstaEnModoAhorroDeEnergia() {
		// Encendemos el dispositivo
		dispositivoInteligente.encender();
		// Pasamos a modo ahorro de energía 
		dispositivoInteligente.activarAhorroDeEnergia();
		// Si le envio el mensaje encender estando en modo ahorro de energía el dispositivo se enciende
		dispositivoInteligente.encender();
		Assert.assertTrue(dispositivoInteligente.estaEncendido());
	}
	
}
