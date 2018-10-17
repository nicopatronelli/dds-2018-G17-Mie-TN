package domicilio;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
//@Table(name = "POSICIONES")
@Embeddable
public class Posicion {
	
	//@Id @GeneratedValue
	//private int posicionId;
	private double latitud;
	private double longitud;
	
	public Posicion() {
		// Constructor vacío para Hibernate
	}
	
	public Posicion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	// GETTERS Y SETTERS
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
}
