package geoposicionamiento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "zonas")
public class Zona {
	
	@Id @Column(name = "id_zona")
	private Long id; 
	
	private String nombre; // Descripción de la zona 
	
	private Double latitud;
	
	private Double longitud;
	
	private Double radio;
	
	@OneToMany(cascade = {CascadeType.ALL}) @JoinColumn(name = "zona_id")
	List<Transformador> transformadores; // Una zona geografica engloba 1 o más transformadores 
	
	public void agregarTransformador(Transformador transformador) {
		transformadores.add(transformador);
	}
	
	public double consumoTotal() {
		return this.transformadores.stream().mapToDouble(transformador -> transformador.energiaConsumida()).sum();
	}
	
	// GETTERS Y SETTERS
	
	public Long getId() {
		return id;
	}
	
}
