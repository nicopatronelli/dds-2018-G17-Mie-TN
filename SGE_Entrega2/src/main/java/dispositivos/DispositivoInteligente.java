package dispositivos;

import org.joda.time.DateTime;

import estadosDispositivoInteligente.EstadoApagado;
import estadosDispositivoInteligente.EstadoDispositivoInteligente;
import lombok.Data;

@Data
public class DispositivoInteligente extends Dispositivo {
	
	private FabricanteDispositivoInteligente fabricante;
	private EstadoDispositivoInteligente estado;
	
	public DispositivoInteligente(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		super(nombreGenerico, consumoKwPorHora, usoMensualMinimoEnHoras, usoMensualMaximoEnHoras, esBajoConsumo);
		this.estado = new EstadoApagado(); // Asumo que todo nuevo dispositivo inteligente inicia en estado apagado
		this.esInteligente = true;
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

	// Getters y Setters
	
	public String getIdFabricante() {
		return fabricante.getIdFabricante();
	}
	
	public void iniciarEstadoApagado() { // Para testing
		this.estado = new EstadoApagado();
	}
	
}
