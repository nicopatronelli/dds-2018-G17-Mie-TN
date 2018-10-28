package dispositivos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Dispositivo implements Cloneable {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "id_dispositivo")
	protected Long id;
	
	@Column(name = "nombre_generico")
	protected String nombreGenerico;
	
	@Column(name = "consumo_kw_por_hora")
	protected double consumoKwPorHora;
	
	@Column(name = "uso_mensual_minimo_en_horas")
	protected int usoMensualMinimoEnHoras;
	
	@Column(name = "uso_mensual_maximo_en_horas")
	protected int usoMensualMaximoEnHoras;
	
	@Column(name = "es_bajo_consumo")
	protected boolean esBajoConsumo;
	
	@Column(name = "es_inteligente")
	protected boolean esInteligente;
	
	protected Dispositivo() {
		// Constructor vacío para Hibernate
	}
	
/*	public Dispositivo(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		this.nombreGenerico = nombreGenerico;
		this.consumoKwPorHora = consumoKwPorHora;
		this.usoMensualMinimoEnHoras = usoMensualMinimoEnHoras;
		this.usoMensualMaximoEnHoras = usoMensualMaximoEnHoras;
		this.esBajoConsumo = esBajoConsumo;
	}*/
	
	public void adaptarDispositivo() throws CloneNotSupportedException {
	}
	
	abstract public boolean esInteligente(); 
	
	abstract public boolean estaEncendido();
	
	abstract public boolean estaApagado();
	
	abstract public boolean estaEnAhorroDeEnergia();
	
	abstract public void apagar();
	
	abstract public void encender();
	
	abstract public void activarAhorroDeEnergia();
	
	abstract public double consumoInstantaneo();
	
	abstract public boolean estaAdaptado();
	
	// Para prototype
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	// GETTERS Y SETTERS
	
	public int getUsoMensualMinimoEnHoras() {
		return usoMensualMinimoEnHoras;
	}
	
	public void setUsoMensualMinimoEnHoras(int usoMensualMinimoEnHoras) {
		this.usoMensualMinimoEnHoras = usoMensualMinimoEnHoras;
	}
	
	public int getUsoMensualMaximoEnHoras() {
		return usoMensualMaximoEnHoras;
	}
	
	public void setUsoMensualMaximoEnHoras(int usoMensualMaximoEnHoras) {
		this.usoMensualMaximoEnHoras = usoMensualMaximoEnHoras;
	}
	
	public double getConsumoKwPorHora() {
		return consumoKwPorHora;
	}
	
	public void setConsumoKwPorHora(double consumoKwPorHora) {
		this.consumoKwPorHora = consumoKwPorHora;
	}
	
	public boolean esBajoConsumo() {
		return esBajoConsumo;
	}
	
	public void setBajoConsumo(boolean valor) {
		this.esBajoConsumo = valor; 
	}
	
	public String getNombre() {
		return nombreGenerico;
	}
	
	public void cambiarNombre(String nombre) {
		this.nombreGenerico = nombre;
	}
	
	public Long getId() {
		return id;
	}
		
	abstract public List<EntradaDispositivoInteligente> getHistorial();
	
}
