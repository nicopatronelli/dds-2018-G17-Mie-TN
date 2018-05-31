package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;

public class DispositivoInteligente extends Dispositivo {
	
	private String nombreGenerico;
	private long consumoPorHora;
	private EstadoDispositivoInteligente estado;
	
	public DispositivoInteligente(String nombreGenerico, long consumoPorHora) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora; 
		this.estado = new EstadoApagado(); // Asumo que todo nuevo dispositivo inteligente inicia en estado apagado
	}
	
	public boolean esInteligente() {
		return true;
	}
	
	/* INICIO -- Mètodos de ESTADO (non-Javadoc)
	 * Dispositivo (DispositivoInteligente en este caso) delega todas sus peticiones (responsabilidades) referidas a su estado
	 * a su instancia de EstadoDispositivo.
	 * 
	 */
	
	public boolean estaEncendido() {
		return this.estado.estaEncendido(this);
	}
	
	public boolean estaApagado() {
		return this.estado.estaApagado(this);
	}
	
	public boolean estaEnAhorroDeEnergia() {
		return this.estado.estaEnModoAhorroDeEnergia(this);
	}
	
	public void apagar() {
		this.estado.apagar(this);
	}
	
	public void encender() {
		this.estado.encender(this);
	}
	
	public void activarAhorroDeEnergia() {
		this.estado.activarAhorroDeEnergia(this);
	}
	
	protected void cambiarEstado(EstadoDispositivoInteligente nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	
	// FIN -- Métodos de ESTADO
	
	
	public int energiaConsumidaDuranteUltimasHoras(int horas) {

	}
	
	public int consumoTotalEntre(DateTime fechaInicial, DateTime fechaFinal) {
		
	}
	
}
