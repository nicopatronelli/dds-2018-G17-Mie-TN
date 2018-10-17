package dispositivos;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import mocks.FabricanteSGE;

@Entity
public class DispositivoEstandar extends Dispositivo {
	
	private int horasDeUsoDiarias; // Lo informa el cliente
	
	@OneToOne
	private DispositivoInteligente adaptador = null;
	
	public DispositivoEstandar() {
		// Constructor vacío para Hibernate
	}
	
	public DispositivoEstandar(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		
		super(nombreGenerico, consumoKwPorHora, usoMensualMinimoEnHoras, usoMensualMaximoEnHoras, esBajoConsumo);
		this.esInteligente = false; // Todo dispositivo estándar inicia como no adaptado
	}

	public double consumoDiarioEstimado() {
		return this.consumoKwPorHora * this.horasDeUsoDiarias;
	}
	
	public boolean estaAdaptado() {
		return this.esInteligente(); // Si el dispositivo estándar es inteligente entonces está adaptado
	}
	
	@Override 
	public void adaptarDispositivo() {
		this.adaptador = new DispositivoInteligente(this.nombreGenerico, this.consumoKwPorHora, this.usoMensualMinimoEnHoras, 
				this.usoMensualMaximoEnHoras, this.esBajoConsumo); 
		this.adaptador.setFabricante(new FabricanteSGE("01A_SGE"));
		this.esInteligente = true;
	}
	
	/* Una vez que un dispositivo estándar esta adaptado delego todos los mensajes referidos a un dispositivo 
	 * inteligente a su adaptador. 
	 */
	
	public DispositivoInteligente adaptador() {
		return this.adaptador;
	}

	public boolean estaEncendido() {
		return adaptador.estaEncendido();
	}

	public boolean estaApagado() {
		return adaptador.estaApagado();
	}

	public boolean estaEnAhorroDeEnergia() {
		return adaptador.estaEnAhorroDeEnergia();
	}

	public void apagar() {
		adaptador.apagar();
	}

	public void encender() {
		adaptador.encender();
	}

	public void activarAhorroDeEnergia() {
		adaptador.activarAhorroDeEnergia();
	}

	@Override
	public double consumoInstantaneo() {
		return adaptador.consumoInstantaneo();
	}

	// GETTERS Y SETTERS
	
	public void setHorasDeUsoDiarias(int horasDeUsoDiarias) {
		this.horasDeUsoDiarias = horasDeUsoDiarias;
	}
	
}
