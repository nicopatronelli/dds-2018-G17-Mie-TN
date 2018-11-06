package casosDePruebaMinimos;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import usuarios.Cliente;

/*
 * CASO DE PRUEBA 1 
 */
public class PruebaUsuario {
	
	Posicion nuevaPosicion;
	Cliente cliente;
	
	@Before
	public void initialize() {
		// Creo un nuevo cliente y lo persisto
		cliente = crearCliente();
		cliente.guardar();
		
		// Recupero el cliente recién persistido
		cliente = cliente.recuperar(cliente.getId());
		cliente.mostrarDomicilios();
		
		// Cambio la posicion de un domicilio 
		nuevaPosicion = new Posicion(450, 880);
		cliente.domicilios().get(0).nuevaPosicion(nuevaPosicion);
		
		// Grabo los cambios
		cliente.guardar();
	}
	
	@Test
	public void cambioDeGeoposicionamientoCorrecto() {
		cliente = cliente.recuperar(cliente.getId());
		cliente.mostrarDomicilios();
		Assert.assertTrue(cliente.domicilios().get(0).getPosicion().equals(nuevaPosicion));
	}
	
	/*
	 *  Métodos auxiliares
	 */
	
	private Cliente crearCliente() {
		Categoria categoriaR1 = new Categoria(18.7, 0.644);
		Posicion geoposicionamiento = new Posicion(250, 350);
		DomicilioServicio domicilio = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR1, geoposicionamiento);
		Cliente nuevoCliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "queen123");
		nuevoCliente.agregarDomicilio(domicilio);
		Transformador transformador = new Transformador(100, 200, 1);
		domicilio.setTransformador(transformador);
		return nuevoCliente;
	}
	
}
