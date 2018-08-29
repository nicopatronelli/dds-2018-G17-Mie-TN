package com.utn.frba.dds.g17.SGE;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utn.frba.dds.g17.SGE.ClasesMock.FabricanteSamsungMock;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import usuarios.Administrador;
import usuarios.Cliente;

public class ZonaTest {
	
	List<Zona> zonas;
	List<Transformador> transformadores;
	Cliente clientes[];
	Cliente cliente;
	DomicilioServicio domicilio;
	DispositivoEstandar dispositivoEstandarA, dispositivoEstandarB;
	DispositivoInteligente dispositivoInteligenteA, dispositivoInteligenteB;
	Transformador transformador;
	Zona zona;
	
	@Before
	public void init() throws CloneNotSupportedException {
		
		Administrador admin = new Administrador("Pepito");
		// Primero se cargan las zonas 
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		// Después cargamos los transformadores activos (que nos envia el ENRE), que son distribuidos a su respectiva zona 
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json");
		clientes = admin.cargarClientes();
		cliente = clientes[0];
		domicilio =  cliente.getDomicilios().get(0);

		dispositivoEstandarA = (DispositivoEstandar)admin.crearDispositivoEstandar("LCD 40 Estandar", 5);
		dispositivoEstandarA.adaptarDispositivo();
		dispositivoEstandarB = (DispositivoEstandar)admin.crearDispositivoEstandar("Ventilador pie Estandar", 1);
		dispositivoEstandarB.adaptarDispositivo();
		dispositivoInteligenteA = (DispositivoInteligente)admin.crearDispositivoInteligente("Aire 2200 Inteligente", new FabricanteSamsungMock("Samsung-IK248"));
		dispositivoInteligenteB = (DispositivoInteligente)admin.crearDispositivoInteligente("Aire 2200 Inteligente", new FabricanteSamsungMock("Samsung-J854"));
		dispositivoEstandarA.encender();
		dispositivoEstandarB.encender();
		dispositivoInteligenteA.encender();
		dispositivoInteligenteB.encender();
		cliente.registrarDispositivoEstandar(dispositivoEstandarA, domicilio);
		cliente.registrarDispositivoEstandar(dispositivoEstandarB, domicilio);
		cliente.registrarDispositivoInteligente(dispositivoInteligenteA, domicilio);
		cliente.registrarDispositivoInteligente(dispositivoInteligenteB, domicilio);
		
		admin.asignarTransformadores(clientes);
		transformador = admin.getTransformadores().get(1);
		zona = admin.getZonas().get(0);
		cliente.recomendacionConsumo(domicilio);
		System.out.println("El resultado del simplex es " + cliente.recomendacionConsumo(domicilio)[1]);
		
	}
	
	@Test
	public void testTransformadorEnergiaConsumida() {
		Assert.assertEquals(transformador.energiaConsumida(), 0.6, 0.0001);
	}
	
	@Test
	public void testZonaConsumoTotal() {
		Assert.assertEquals(zona.consumoTotal(), 0.6, 0.0001);
	}

}
