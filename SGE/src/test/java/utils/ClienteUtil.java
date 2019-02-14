package utils;

import java.time.LocalDate;

import dispositivos.DispositivoInteligente;
import domicilio.Categoria;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import mocks.FabricanteSamsungMock;
import usuarios.Administrador;
import usuarios.Cliente;

public class ClienteUtil {
	
	public static Cliente crearCliente() throws CloneNotSupportedException {
		Categoria categoriaR2 = Categoria.R2;
		Posicion geoposicionamiento = new Posicion(250, 350);
		DomicilioServicio domicilio = new DomicilioServicio("4232-4817", LocalDate.of(2018, 06, 12), categoriaR2, geoposicionamiento);
		Cliente nuevoCliente = new Cliente("John", "Deacon", "DNI", 10512789, "Johnny", "queen123");
		nuevoCliente.agregarDomicilio(domicilio);
		Transformador transformador = new Transformador(100, 200, 1);
		domicilio.setTransformador(transformador);
		return nuevoCliente;
	}
	
}
