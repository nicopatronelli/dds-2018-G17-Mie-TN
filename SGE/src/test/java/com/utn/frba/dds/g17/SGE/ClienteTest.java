package com.utn.frba.dds.g17.SGE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utn.frba.dds.g17.SGE.ClasesMock.FabricanteSamsungMock;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import usuarios.Administrador;
import usuarios.Cliente;

public class ClienteTest {
	
	Administrador admin;
	Cliente clientes[];
	Cliente cliente;
	DomicilioServicio domicilio;
	DispositivoEstandar dispositivoEstandarA, dispositivoEstandarB;
	DispositivoInteligente dispositivoInteligenteA;

	@Before
	public void initialize() throws CloneNotSupportedException {
		
		admin = new Administrador("Pepito");
		clientes = admin.cargarClientes();
		cliente = clientes[0];
		domicilio =  cliente.getDomicilios().get(0);
		dispositivoEstandarA = (DispositivoEstandar)admin.crearDispositivoEstandar("LCD 40 Estandar", 5);
		dispositivoEstandarB = (DispositivoEstandar)admin.crearDispositivoEstandar("Ventilador pie Estandar", 1);
		dispositivoInteligenteA = (DispositivoInteligente)admin.crearDispositivoInteligente("Aire 2200 Inteligente", new FabricanteSamsungMock("Samsung-IK248"));
		cliente.registrarDispositivoEstandar(dispositivoEstandarA, domicilio);
		cliente.registrarDispositivoEstandar(dispositivoEstandarB, domicilio);
		cliente.registrarDispositivoInteligente(dispositivoInteligenteA, domicilio);
		
	}
	
	// El cliente tiene 3 dispositivos: 2 estandares y 1 inteligente 
	
	@Test
	public void testElClienteGana15PuntosPorRegistrarUnDispositivoInteligente() {
		Assert.assertTrue(cliente.cantidadDePuntos() == 15);
	}

	@Test
	public void testCantidadDispositivosApagados() {
		// El dispositivoInteligenteA inicia apagado => tengo un solo dispositivo apagado (los estandar no cuentan)
		Assert.assertTrue(cliente.cantidadDispositivosApagados() == 1);
	}
	
	@Test
	public void testTieneDispositivosEncendidos() {
		dispositivoInteligenteA.encender();
		Assert.assertTrue(cliente.tieneAlgunDispositivoEncendido());
	}
	
	@Test
	public void testCantidadDispositivosEncendidos() {
		// Adapto el dispositivoEstandarA
		cliente.adaptarDispositivoEstandar(dispositivoEstandarA, domicilio);
		// Enciendo el único dispositivo inteligente y el dispositivo recién adaptado 
		dispositivoInteligenteA.encender();
		dispositivoEstandarA.encender();
		Assert.assertTrue(cliente.cantidadDispositivosEncendidos() == 2);
	}

	@Test 
	public void testElClienteRecibe10PuntosAlAdaptarDispositivo() {
		cliente.adaptarDispositivoEstandar(dispositivoEstandarA, domicilio);
		// Sumo 15 por registrar un dispositivo inteligente + 10 por adaptar uno estandar
		Assert.assertTrue(cliente.cantidadDePuntos() == 25);
	}
	
}
