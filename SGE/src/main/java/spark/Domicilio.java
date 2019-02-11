package spark;

public class Domicilio {
	
	String nombre;
	String consumo;
	
	public Domicilio(String nombre, String consumo)
	{
		this.nombre = nombre;
		this.consumo = consumo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	
}
