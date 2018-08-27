package geoposicionamiento;

import java.util.List;

import lombok.Data;

@Data
public class Zona {
	
	private int id; 
	private String nombre; // Descripción de la zona 
	private double latitud;
	private double longitud;
	private double radio;
	List<Transformador> transformadores; // Una zona geografica engloba 1 o más transformadores 
	
	public void agregarTransformador(Transformador transformador) {
		transformadores.add(transformador);
	}
	
	public double consumoTotal() {
		return this.transformadores.stream().mapToDouble(transformador -> transformador.energiaConsumida()).sum();
	}
	

	
}
