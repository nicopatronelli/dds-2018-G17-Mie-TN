package com.utn.frba.dds.g17.SGE;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdministradorTest {

	private Administrador adm;
	private List<Administrador> administradores;

	@Before
	public void setUp() throws IOException {
		this.adm = new Administrador("Rodriguez", "Lucia", "RodriguezLucia", "luci83", "Lavalle 1568",
				new DateTime("2014-06-15"), 25489);
		
		administradores = CargaDatosJson.getAdministradoresJson();
		
	}

	@Test
	public void testCantidadMesesComoAdmistrador() {
		Assert.assertEquals(46, this.adm.cantidadMesesComoAdmistrador());
	}
	
	@Test 
	public void testCantidadAdministradores() {
		Assert.assertEquals(2, this.administradores.size());
	}

}
