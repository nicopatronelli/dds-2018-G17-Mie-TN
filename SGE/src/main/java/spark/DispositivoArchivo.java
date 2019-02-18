package spark;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DispositivoArchivo {
	
	private String nombreGenerico;
	
	private double consumoKwPorHora;
	
	private int usoMensualMinimoEnHoras;
	
	private int usoMensualMaximoEnHoras;
	
	private boolean esBajoConsumo;
	
	private boolean esInteligente;
	
	private int horasUsoDiarias;

	public DispositivoArchivo(Long id, String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo, boolean esInteligente, int horasUsoDiarias) {
		this.nombreGenerico = nombreGenerico;
		this.consumoKwPorHora = consumoKwPorHora;
		this.usoMensualMinimoEnHoras = usoMensualMinimoEnHoras;
		this.usoMensualMaximoEnHoras = usoMensualMaximoEnHoras;
		this.esBajoConsumo = esBajoConsumo;
		this.esInteligente = esInteligente;
		this.horasUsoDiarias = horasUsoDiarias;
	}

	public String nombreGenerico() {
		return nombreGenerico;
	}

	public boolean esInteligente() {
		return esInteligente;
	}
	
	public int horasDeUsoDiarias() {
		return horasUsoDiarias;
	}
	
}
