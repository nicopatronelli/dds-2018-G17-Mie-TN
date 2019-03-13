package commons;

import java.text.DecimalFormat;
import java.util.Random;

//import java.util.Random;

public class Matematica {
	
	public static double redondear(double numero) {
	    double scale = Math.pow(10, 4);
	    return Math.round(numero * scale) / scale;
	}
	
	public static double numeroAleatorio()
	{
		Random random = new Random();
		double numero = random.nextDouble() * 10 + 8;
	    double scale = Math.pow(10, 2);
	    return Math.round(numero * scale) / scale;
	}
	
	public static void main(String[] args) {
		System.out.println("Un numero aleatorio es: " + numeroAleatorio());
		System.out.println("Otro numero aleatorio es: " + numeroAleatorio());
		System.out.println("Y otro aleatorio más es: " + numeroAleatorio());
	}
}
