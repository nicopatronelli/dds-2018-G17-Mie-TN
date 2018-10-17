package domicilio;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "CATEGORIAS")
@Embeddable
public class Categoria {
	
	//@Id @GeneratedValue
	//private int idCategoria;
	
	private double cargoFijo;
	
	private double cargoVariable;
	
	public Categoria() {
		// Constructor vacío para Hibernate
	}
	
	public Categoria(double cargoFijo, double cargoVariable) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}

	// GETTERS Y SETTERS
	
	public double getCargoFijo() {
		return cargoFijo;
	}
	
	public double getCargoVariable() {
		return cargoVariable;
	}
	
}
