package casosDePruebaMinimos;

import static utils.ClienteUtil.crearCliente;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domicilio.Posicion;
import usuarios.Cliente;

public class PruebaUsuario {
	
	Posicion nuevaPosicion;
	Cliente cliente;
	
	@Before
	public void initialize() throws CloneNotSupportedException {
		// Creo un nuevo cliente y lo persisto
		cliente = crearCliente();
		cliente.guardar();
		
		// Recupero el cliente recién persistido
		cliente = (Cliente) cliente.recuperar(cliente.getId());
		cliente.mostrarDomicilios();
		
		// Cambio la posicion de un domicilio 
		nuevaPosicion = new Posicion(450, 880);
		cliente.domicilios().get(0).nuevaPosicion(nuevaPosicion);
		
		// Grabo los cambios
		cliente.guardar();
	}
	
	@Test
	public void cambioDeGeoposicionamientoCorrecto() {
		cliente = (Cliente) cliente.recuperar(cliente.getId());
		cliente.mostrarDomicilios();
		Assert.assertTrue(cliente.domicilios().get(0).getPosicion().equals(nuevaPosicion));
	}
	
}
