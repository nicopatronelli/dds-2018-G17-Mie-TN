package geoposicionamiento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import domicilio.DomicilioServicio;

@Entity
@Table(name = "Transformadores_activos")
public class Transformador {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_transformador")
	private int id;
	
	private double latitud;
	
	private double longitud;
	
	private int zonaId; // Zona a la que pertenece 
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "transformador") 
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
