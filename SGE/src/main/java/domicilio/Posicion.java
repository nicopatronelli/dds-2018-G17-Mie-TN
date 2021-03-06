package domicilio;

import javax.persistence.Embeddable;

@Embeddable
public class Posicion {
	
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
