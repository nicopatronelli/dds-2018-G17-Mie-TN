package dispositivos;

import com.utn.frba.dds.g17.SGE.ClasesMock.FabricanteSGE;

import lombok.Data;

@Data
public class DispositivoEstandar extends Dispositivo {
	
	private int horasDeUsoDiarias; // Lo informa el cliente
	
	private DispositivoInteligente adaptador = null;
	
	public DispositivoEstandar(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {
		
		super(nombreGenerico, consumoKwPorHora, usoMensualMinimoEnHoras, usoMensualMaximoEnHoras, esBajoConsumo);
		this.esInteligente = false; // Todo Dispositivo Estandar inicia como no adaptado
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
	
}
