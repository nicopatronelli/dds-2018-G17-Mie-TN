package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConversionEstandarInteligenteTest {
	
	Cliente clientes[];
	Cliente unCliente;
	DispositivoEstandar dispositivoEstandarA;
	DispositivoEstandar dispositivoEstandarB;
	DispositivoInteligente dispositivoInteligenteA;
	DispositivoInteligente dispositivoInteligenteB;
	
	@Before
	public void init() {
		//El único cliente del archivo tiene 2 dispositivos inteligentes y 2 dispositivos estandares 
		clientes = CargaDatosJson.cargarClientes("src\\test\\resources\\data\\json\\Clientes.json");
		unCliente = clientes[0];
		dispositivoEstandarA = unCliente.getDomicilios().get(0).getDispositivosEstandares().get(0);
		dispositivoEstandarB = unCliente.getDomicilios().get(0).getDispositivosEstandares().get(1);
		dispositivoInteligenteA = unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(0);
		dispositivoInteligenteA.iniciarEstadoApagado();
		dispositivoInteligenteB = unCliente.getDomicilios().get(0).getDispositivosInteligentes().get(1);
		dispositivoInteligenteB.iniciarEstadoApagado();
		// Adaptamos el dispositivoEstandarA 
		unCliente.adaptarDispositivoEstandar(dispositivoEstandarA, unCliente.getDomicilios().get(0));
	}
	
	@Test
	public void testEstaAdaptado() {
		Assert.assertTrue(dispositivoEstandarA.estaAdaptado());
	}
	
	@Test
	public void testDispositivoAdaptadoEstaEncendido() {
		dispositivoEstandarA.adaptador().encender();
		Assert.assertTrue(dispositivoEstandarA.adaptador().estaEncendido());
	}
	
	@Test
	public void testElClienteTiene10puntosPorAdaptarUnDispositivo() {
		Assert.assertTrue(unCliente.cantidadDePuntos() == 10);
	}
	
	@Test
	public void testCantidadDispositivosEncendidos() {
		//Tengo 3 dispositivos encendidos: 2 inteligentes y uno adaptado
		dispositivoInteligenteA.encender();
		dispositivoInteligenteB.encender(); 
		dispositivoEstandarA.adaptador().encender();
		Assert.assertTrue(unCliente.cantidadDispositivosEncendidos() == 3); 
	}
	
	@Test
	public void testCantidadTotalDispositivosCliente() {
		/* Físicamente tengo 4 dispositivos (dos estandares y dos inteligentes), no debería 
		 * contabilizar 5 al adaptar un estandar (tengo la misma cantidad de dispositivos que antes).
		 */
		Assert.assertTrue(unCliente.cantidadTotalDispositivos() == 4);
	}
	
}
