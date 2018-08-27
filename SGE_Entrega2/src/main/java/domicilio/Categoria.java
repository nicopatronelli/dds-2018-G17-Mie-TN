package domicilio;

public class Categoria {

	private double cargoFijo;
	private double cargoVariable;
	
	public Categoria(double cargoFijo, double cargoVariable) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}
	
	// Getters y Setters
	
	public double getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public double getCargoVariable() {
		return cargoVariable;
	}

	public void setCargoVariable(double cargoVariable) {
		this.cargoVariable = cargoVariable;
	}
	
}
