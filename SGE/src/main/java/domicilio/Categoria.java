package domicilio;

public enum Categoria {
	
	R1(18.76, 0.644), 
	R2(35.32, 0.644),
	R3(60.71, 0.681),
	R4(71.74, 0.738),
	R5(110.38, 0.794),
	R6(220.75, 0.832),
	R7(443.59, 0.851),
	R8(545.96, 0.851),
	R9(887.19, 0.851);
	
	private double cargoFijo;
	
	private double cargoVariable;
	
	// Constructor
	private Categoria(double cargoFijo, double cargoVariable)
	{
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}
	
	// GETTERS Y SETTERS
	public double cargoFijo() {	return this.cargoFijo; }

	public double cargoVariable() {	return this.cargoVariable; }

}
