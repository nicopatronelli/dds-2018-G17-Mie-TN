package com.utn.frba.dds.g17.SGE;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private List<Cliente> clientes;
	private Cliente unCliente;
	private Cliente otroCliente;
	
	@Before
	public void setUp() throws IOException {
		this.clientes = CargaDatosJson.getClientesJson();
		this.unCliente = this.clientes.get(0);
		this.otroCliente = this.clientes.get(1);
	}

	@Test
	public void testTieneDispositivosEncendidos() {
		Assert.assertTrue(unCliente.estaEncendido());
		Assert.assertTrue(otroCliente.estaEncendido());
	}

	@Test
	public void testCantidadDispositivosEncendidos(){
		Assert.assertEquals(1, this.unCliente.cantidadDispositivosEncendidos());
		Assert.assertEquals(2, this.otroCliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados(){
		Assert.assertEquals(2, this.unCliente.cantidadDispositivosApagados());
		Assert.assertEquals(2, this.otroCliente.cantidadDispositivosApagados());
	}

	@Test
	public void testCantidadTotalDispositivos() {
		Assert.assertEquals(3, this.unCliente.cantidadTotalDispositivos());
		Assert.assertEquals(4, this.otroCliente.cantidadTotalDispositivos());
	}

	@After
	public void limpiarVariables() {
		this.clientes.clear();
	}

}
