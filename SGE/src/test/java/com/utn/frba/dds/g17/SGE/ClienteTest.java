package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	private Dispositivo d1;
	private Dispositivo d2;
	private Dispositivo d3;
	private Dispositivo d4;
	List<Dispositivo> dispositivos;

	@Before
	public void setUp() {

		this.d1 = new Dispositivo("TV Samsung LED 55", 105, true);
		this.d2 = new Dispositivo("Heladera Patric No Frost", 120, true);
		this.d3 = new Dispositivo("Ventilador Liliana 20'", 45, true);
		this.d4 = new Dispositivo("Licuadora Philips", 35, false);

		this.dispositivos = new ArrayList<Dispositivo>();
		this.dispositivos.add(d1);
		this.dispositivos.add(d2);
		this.dispositivos.add(d3);
		this.dispositivos.add(d4);

		this.cliente = new Cliente("Perez", "Francisco", "PerezFrancisco", "Juancho46", "DNI", 22333555, "1589458789",
				"Av Callao 2879", new DateTime("1982-10-09"), new Categoria(), dispositivos);
	
	}
	
	@Test
	public void testTieneDispositivosEncendidos() {
		Assert.assertTrue(this.cliente.tieneDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadDispositivosEncendidos() {
		Assert.assertEquals(3, this.cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados() {
		Assert.assertEquals(1, this.cliente.cantidadDispositivosApagados());
	}
	
	@Test
	public void testCantidadTotalDispositivos() {
		Assert.assertEquals(4, this.cliente.cantidadTotalDispositivos());
	}
	
	@After
	public void limpiarVariables() {
		this.dispositivos.clear();
	}
	
}
