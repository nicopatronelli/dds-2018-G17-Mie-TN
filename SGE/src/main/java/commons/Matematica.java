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
		double numero = random.nextDouble() * 10;
	    double scale = Math.pow(10, 4);
	    return Math.round(numero * scale) / scale;
	}
}
