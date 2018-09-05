package domicilio;

public class Categoria {

	private double cargoFijo;
	private double cargoVariable;
	
	public Categoria(double cargoFijo, double cargoVariable) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}

	// GETTERS Y SETTERS
	
	public double getCargoFijo() {
		return cargoFijo;
	}
	
}
