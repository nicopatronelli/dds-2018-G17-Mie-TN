package simplex;

import java.util.Arrays;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dispositivos.Dispositivo;
import domicilio.DomicilioServicio;
import simplex.facade.SimplexFacade;

import static commons.Matematica.*;

/* Esta es nuestra propia Facade de la biblioteca Simplex. La biblioteca Simplex original provista para el 
 * TP la agregamos como una dependencia Maven utilizando Jitpack.
 */

public class SimplexFacadeSGE {
	
	public static double[] recomendacionConsumo(DomicilioServicio unDomicilio) {
		
		SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true); // FIJO 
		int cantDispositivos = unDomicilio.cantidadTotalDispositivos();
		double coeficientesVariables[] = new double[cantDispositivos]; // 1 por cada dispositivo
		Arrays.fill(coeficientesVariables, 1); 
		simplexFacade.crearFuncionEconomica(coeficientesVariables);
		
		final int MAX_CONSUMO_MENSUAL_PERMITIDO = 612; // 612 kWh * 24 horas * 30 días = 440640 kW
		double coeficientesConsumo[] = new double[cantDispositivos]; // Los coeficientes son el consumo de cada dispositivo
		
		for(int i = 0; i < cantDispositivos; i++){
			coeficientesConsumo[i] = unDomicilio.dispositivos().get(i).consumoKwPorHora();
		}
		
		// 1er reestricción (reestricción principal) 
		simplexFacade.agregarRestriccion(Relationship.LEQ, MAX_CONSUMO_MENSUAL_PERMITIDO, coeficientesConsumo);
		
		// Reestricciones por cada dispositivo 
		for(int i = 0; i < cantDispositivos; i++){
			
			Dispositivo dispositivoActual = unDomicilio.dispositivos().get(i);
			double coeficienteDispositivo[] =  new double[cantDispositivos];
			Arrays.fill(coeficienteDispositivo, 0);
			coeficienteDispositivo[i] = 1;
			simplexFacade.agregarRestriccion(Relationship.GEQ, dispositivoActual.getUsoMensualMinimoEnHoras(), coeficienteDispositivo);
			simplexFacade.agregarRestriccion(Relationship.LEQ, dispositivoActual.getUsoMensualMaximoEnHoras(), coeficienteDispositivo);
		
		}
		
		// Ejecuto el Simplex
		PointValuePair solucionSimplex = simplexFacade.resolver(); // Resuelvo con simplex 
		
		double recomendacionHoras[] = new double[cantDispositivos+1]; // + 1 para guardar el valor Z
		recomendacionHoras[0] = redondear(solucionSimplex.getValue()); // El Z de la función económica (valor óptimo en horas que se le recomienda al usuario consumir)
		
		for(int i = 0; i < cantDispositivos; i++) {
			recomendacionHoras[i+1] = redondear(solucionSimplex.getPoint()[i]);
		}
		
		return recomendacionHoras; // Retorno un array con el valor de Z y las horas recomendadas de uso de cada dispositivo inteligente
		
	}
	
}
