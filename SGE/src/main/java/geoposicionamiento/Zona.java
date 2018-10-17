package geoposicionamiento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ZONAS")
public class Zona {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPK;
	
	private int id; 
	
	private String nombre; // Descripción de la zona 
	
	private double latitud;
	
	private double longitud;
	
	private double radio;
	
	@OneToMany(cascade = {CascadeType.ALL}) @JoinColumn(name = "idPK")
	List<Transformador> transformadores; // Una zona geografica engloba 1 o más transformadores 
	
	public void agregarTransformador(Transformador transformador) {
		transformadores.add(transformador);
	}
	
	public double consumoTotal() {
		return this.transformadores.stream().mapToDouble(transformador -> transformador.energiaConsumida()).sum();
	}
	
	// GETTERS Y SETTERS
	
	public int getId() {
		return id;
	}
	
}
