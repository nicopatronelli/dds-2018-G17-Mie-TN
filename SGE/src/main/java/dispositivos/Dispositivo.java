package dispositivos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Dispositivo implements Cloneable {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;
	
	protected String nombreGenerico;
	
	protected double consumoKwPorHora;
	
	protected int usoMensualMinimoEnHoras;
	
	protected int usoMensualMaximoEnHoras;
	
	protected boolean esBajoConsumo;
	
	protected boolean esInteligente;
	
	public Dispositivo() {
		// Constructor vacío para Hibernate
	}
	
	public Dispositivo(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		this.nombreGenerico = nombreGenerico;
		this.consumoKwPorHora = consumoKwPorHora;
		this.usoMensualMinimoEnHoras = usoMensualMinimoEnHoras;
		this.usoMensualMaximoEnHoras = usoMensualMaximoEnHoras;
		this.esBajoConsumo = esBajoConsumo;
	}
	
	public void adaptarDispositivo() {
	}
	
	public boolean esInteligente() {
		return this.esInteligente;
	}
	
	abstract public boolean estaEncendido();
	
	abstract public boolean estaApagado();
	
	abstract public boolean estaEnAhorroDeEnergia();
	
	abstract public void apagar();
	
	abstract public void encender();
	
	abstract public void activarAhorroDeEnergia();
	
	abstract public double consumoInstantaneo();
	
	// Para prototype
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	// GETTERS Y SETTERS
	
	public double getUsoMensualMinimoEnHoras() {
		return usoMensualMinimoEnHoras;
	}

	public double getUsoMensualMaximoEnHoras() {
		return usoMensualMaximoEnHoras;
	}

	public double getConsumoKwPorHora() {
		return consumoKwPorHora;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}
	
}
