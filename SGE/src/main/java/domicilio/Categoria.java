package domicilio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
public class Categoria {
	
	@Column(name = "cargo_fijo")
	private double cargoFijo;
	
	@Column(name = "cargo_variable")
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
