package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EstadosDispositivoInteligenteTest {
	
	Cliente clientes[];
	Cliente unCliente;
	DispositivoInteligente unDispositivoInteligente;
	
	@Before
	public void initialize() {
		clientes = CargaDatosJson.cargarClientes("src\\test\\resources\\data\\json\\Clientes.json");
		unCliente = clientes[0];
		unDispositivoInteligente = unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(0);
		unDispositivoInteligente.iniciarEstadoApagado();
	}
	
	@Test
	public void testEncenderDispositivoApagado() {
		// Si el dispositivo está apagado y le envio el mensaje encender pasa a estado encendido
		unDispositivoInteligente.encender();
		Assert.assertTrue(unDispositivoInteligente.estaEncendido());
	}
	
	@Test
	public void testApagarDispositivoEncendido() {
		// Encendemos el dispositivo
		unDispositivoInteligente.encender();
		// Si el dispositivo está encendido y le envio el mensaje apagar pasa a estado apagado
		unDispositivoInteligente.apagar();
		Assert.assertTrue(unDispositivoInteligente.estaApagado());
	}
	
	@Test
	public void testActivarModoAhorroEnergiaSiEstaApagado() {
		// Si el dispositivo está apagado y le envio el mensaje activarAhorroDeEnergia sigue apagado (no pasa nada)
		unDispositivoInteligente.activarAhorroDeEnergia();
		Assert.assertTrue(unDispositivoInteligente.estaApagado());
	}
	
	@Test
	public void testEncenderSiEstaEnModoAhorroDeEnergia() {
		// Encendemos el dispositivo
		unDispositivoInteligente.encender();
		// Pasamos a modo ahorro de energía 
		unDispositivoInteligente.activarAhorroDeEnergia();
		// Si le envio el mensaje encender estando en modo ahorro de energía el dispositivo se enciende
		unDispositivoInteligente.encender();
		Assert.assertTrue(unDispositivoInteligente.estaEncendido());
	}
	
	

}
