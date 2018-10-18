package dispositivos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import estadosDispositivoInteligente.EstadoApagado;
import estadosDispositivoInteligente.EstadoDispositivoInteligente;

@Entity
@Table(name = "Dispositivos_Inteligentes")
public class DispositivoInteligente extends Dispositivo {
	
	@Column(name = "consumo_generado")
	private double consumoGenerado;
	
	@OneToOne(cascade = { CascadeType.ALL })
	private FabricanteDispositivoInteligente fabricante;
	
	@Transient
	private EstadoDispositivoInteligente estado;
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "dispositivo_inteligente_id")
	private List<EntradaDispositivoInteligente> historial;
	
	public DispositivoInteligente() {
		// Constructor vacío para Hibernate
	}
	
	public DispositivoInteligente(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		
		super(nombreGenerico, consumoKwPorHora, usoMensualMinimoEnHoras, usoMensualMaximoEnHoras, esBajoConsumo);
		this.estado = new EstadoApagado(); // Asumo que todo nuevo dispositivo inteligente inicia en estado apagado
		this.esInteligente = true;
		this.historial = new ArrayList<EntradaDispositivoInteligente>();
	}
	
	/*  							
	 * INICIO - Métodos de ESTADO	
	 */	

	public boolean estaEncendido() {
		return estado.estaEncendido(this);
	}
	
	public boolean estaApagado() {
		return estado.estaApagado(this);
	}
	
	public boolean estaEnAhorroDeEnergia() {
		return estado.estaEnModoAhorroDeEnergia(this);
	}
	
	public void apagar() {
		estado.apagar(this);
	}
	
	public void encender() {
		estado.encender(this);
	}
	
	public void activarAhorroDeEnergia() {
		estado.activarAhorroDeEnergia(this);
	}
	
	public void cambiarEstado(EstadoDispositivoInteligente nuevoEstado) {
		estado = nuevoEstado;
	}
	
	/*
	 *  FIN - Métodos de ESTADO
	 */
	
	/* A diferencia de los dispositivos estándar, donde calculamos un consumo aproximado nosotros (SGE), 
	 * son los fabricantes de los dispositivos inteligentes los que nos van a proporcionar el consumo 
	 * de los mismos (INTERFAZ EXTERNA con SGE). 
	 */
	
	public void actualizarHistorial(EstadoHistorial estadoActual) {
		EntradaDispositivoInteligente nuevaEntrada = new EntradaDispositivoInteligente(new DateTime(), estadoActual);
		historial.add(nuevaEntrada);
	}
	
	public void mostrarHistorial() {
		System.out.println(historial.toString());
	}
	
	public float energiaConsumidaDuranteUltimasHoras(int horas) {
		// Lo tenemos que hacer nosotros guardando el estado de los dispositivos 
		return 0;
	}
	
	public float consumoTotalEntre(DateTime fechaInicial, DateTime fechaFinal) {
		// Lo tenemos que calcular nosotros guardando el estado de los dispositivos
		return 0;
	}
	
	public double consumoInstantaneo() {
		if (this.estaApagado())
			return 0;
		else 
			return this.fabricante.consumoInstantaneo(fabricante.getIdFabricante());
	}
	
	public String getIdFabricante() {
		return fabricante.getIdFabricante();
	}
	
	public void iniciarEstadoApagado() { 
		this.estado = new EstadoApagado();
	}

	// GETTERS Y SETTERS

	public void setFabricante(FabricanteDispositivoInteligente fabricante) {
		this.fabricante = fabricante;
	}

	public FabricanteDispositivoInteligente getFabricante() {
		return fabricante;
	}

	
}
