package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	
	Cliente clientes[];
	Cliente unCliente;
	DispositivoInteligente dispositivoInteligenteA;
	DispositivoInteligente dispositivoInteligenteB;
	
	@Before
	public void initialize() {
		//El único cliente del archivo tiene 2 dispositivos inteligentes y 2 dispositivos estandares 
		clientes = CargaDatosJson.cargarClientes("src\\test\\resources\\data\\json\\Clientes.json");
		unCliente = clientes[0];
		dispositivoInteligenteA = unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(0);
		dispositivoInteligenteA.iniciarEstadoApagado();
		// Encendemos uno de los dos dispositivos inteligentes que tiene el cliente 
		unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(0).encender();
		dispositivoInteligenteB = unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(1);
		dispositivoInteligenteB.iniciarEstadoApagado();
	}
	
	@Test
	public void testTieneDispositivosEncendidos() {
		Assert.assertTrue(unCliente.tieneAlgunDispositivoEncendido());
	}
	
	@Test
	public void testCantidadDispositivosEncendidos() {
		Assert.assertTrue(unCliente.cantidadDispositivosEncendidos() == 1);
	}
	
	@Test
	public void testCantidadDispositivosApagados() {
		Assert.assertTrue(unCliente.cantidadDispositivosApagados() == 1);
	}

}
