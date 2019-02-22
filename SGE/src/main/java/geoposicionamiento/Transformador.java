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
import static commons.Fecha.*;
import static commons.Matematica.*;

@Entity
@Table(name = "Transformadores_activos")
public class Transformador extends PersistEntity<Transformador> {
	
	@Id @Column(name = "id_transformador")
	private Long id;
	
	private double latitud;
	
	private double longitud;
	
	@Transient
	private int zonaId; // Zona a la que pertenece 
	
	@OneToMany(cascade = CascadeType.ALL) 
    @JoinTable(
            name="transformador_por_domicilio",
            joinColumns = @JoinColumn( name="id_transformador"),
            inverseJoinColumns = @JoinColumn( name="id_domicilio")
    )
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
	
	public double consumoInstantaneo() {
		return domicilios.stream().mapToDouble(domicilio->domicilio.consumoInstantaneo()).sum();
	}
	
	public double consumoEnPeriodo(String fechaDesde, String fechaHasta) {
		return domicilios.stream().mapToDouble(domicilio->domicilio.consumoEnPeriodo(fechaDesde, fechaHasta)).sum();
	}
	
	public double consumoPromedioEntre(String fechaDesde, String fechaHasta) {
		return redondear(consumoEnPeriodo(fechaDesde, fechaHasta) / diasEntreFechas(fechaDesde, fechaHasta));
	}
	
	public int cantidadDomiciliosAsignados() {
		return domicilios.size();
	}
	
	// GETTERS Y SETTERS
	
	public Long id() {
		return id;
	}
	
	public double latitud() {
		return latitud;
	}
	
	public double longitud() {
		return longitud;
	}
	
	public String coordenadas() {
		return "(" + latitud + ", " + longitud + ")";
	}
	
	public int getZonaId() {
		return zonaId;
	}

	public void setDomicilios(List<DomicilioServicio> domicilios) {
		this.domicilios = domicilios;
	}

}
