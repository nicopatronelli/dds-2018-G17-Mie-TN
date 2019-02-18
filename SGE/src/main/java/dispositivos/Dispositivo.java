package dispositivos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;

import hibernate.PersistEntity;
import usuarios.Cliente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "dispositivos")
public abstract class Dispositivo extends PersistEntity<Dispositivo> implements Cloneable  {
	
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
	
	@Column(name = "horas_uso_recomendadas")
	protected double horasDeUsoRecomendadas;

	protected Dispositivo() {
		// Constructor vacío para Hibernate
	}
	
	public void adaptarDispositivo() throws CloneNotSupportedException {
	}
	
	abstract public EstadoHistorial estado();
	
	abstract public boolean esInteligente(); 
	
	abstract public boolean estaEncendido();
	
	abstract public boolean estaApagado();
	
	abstract public boolean estaEnAhorroDeEnergia();
	
	abstract public void apagar();
	
	abstract public void encender();
	
	abstract public void activarAhorroDeEnergia();
	
	abstract public double consumoInstantaneo();
	
	abstract public boolean estaAdaptado();
	
	abstract public int horasDeUsoDiarias();
	
	abstract public double consumoUltimoPeriodo();
	
	// Para prototype
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
/*	// Para Hibernate
	public void iniciarEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		this.manager = emf.createEntityManager();
	}*/

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
	
	public double consumoKwPorHora() {
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
	
	public String nombre() {
		return nombreGenerico;
	}
	
	public void cambiarNombre(String nombre) {
		this.nombreGenerico = nombre;
	}
	
	public Long id() {
		return id;
	}
		
	public double horasDeUsoRecomendadas() {
		return horasDeUsoRecomendadas;
	}

	public void setHorasDeUsoRecomendadas(double horasDeUsoRecomendadas) {
		this.horasDeUsoRecomendadas = horasDeUsoRecomendadas;
	}
	
	abstract public List<EntradaDispositivoInteligente> getHistorial();
	
	public void inicializarEstado(){}
	
	public void setFabricante(FabricanteDispositivoInteligente fabricante) {}
}
