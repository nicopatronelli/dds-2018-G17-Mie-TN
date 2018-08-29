package domicilio;

import lombok.Data;

@Data
public class Categoria {

	private double cargoFijo;
	private double cargoVariable;
	
	public Categoria(double cargoFijo, double cargoVariable) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}
	
}
