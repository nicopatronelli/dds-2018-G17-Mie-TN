package geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import domicilio.DomicilioServicio;

public class Transformador {
	
	private int id;
	private double latitud;
	private double longitud;
	private int zonaId; // Zona a la que pertenece 
	List<DomicilioServicio> domicilios; // Lista de domicilios a los que suministra energía 
	
	public void agregarDomicilio(DomicilioServicio domicilio) {
		domicilios.add(domicilio);
	}
	
	public double energiaConsumida() {
		return this.domicilios.stream().mapToDouble(domicilio->domicilio.consumoInteligenteDomicilio()).sum();
	}
	
	// GETTERS Y SETTERS
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

	public int getZonaId() {
		return zonaId;
	}

	public void setDomicilios(List<DomicilioServicio> domicilios) {
		this.domicilios = domicilios;
	}

}
