package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdministradorTest {

	private Administrador adm;

	@Before
	public void setUp() {
		this.adm = new Administrador("Rodriguez", "Lucia", "RodriguezLucia", "luci83", "Lavalle 1568",
				new DateTime("2014-06-15"), 25489);
	}

	@Test
	public void testCantidadMesesComoAdmistrador() {
		Assert.assertEquals(46, this.adm.cantidadMesesComoAdmistrador());
	}

}
