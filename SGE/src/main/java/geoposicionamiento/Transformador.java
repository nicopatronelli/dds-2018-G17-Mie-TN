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

import com.google.gson.annotations.SerializedName;

import domicilio.DomicilioServicio;
import hibernate.PersistEntity;

@Entity
@Table(name = "Transformadores_activos")
public class Transformador extends PersistEntity<Transformador> {
	
	@Id @Column(name = "id_transformador")
	private int id;
	
	private double latitud;
	
	private double longitud;
	
	@Transient
	private int zonaId; // Zona a la que pertenece 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transformador")  
	List<DomicilioServicio> domicilios; // Lista de domicilios a los que suministra energía 
	
	public Transformador() {
		// Constructor vacío para Hibernate
	}
	
	// Momentaneo
	public Transformador(double latitud, double longitud, int zonaId) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.zonaId = zonaId;
	}
	
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
