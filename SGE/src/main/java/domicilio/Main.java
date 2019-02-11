package domicilio;

public class Main {
	
	public static void main(String[] args) {
		
		final Categoria categoria;
		categoria = Categoria.R1;
		System.out.println("La categoria es " + categoria
				+ ", cuyo cargo fijo es " + categoria.cargoFijo()
				+ " y su cargo variable es " + categoria.cargoVariable());
	}
	
}
