package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;

public class DispositivoInteligente extends Dispositivo {
	
	private EstadoDispositivoInteligente estado;
	
	public DispositivoInteligente(String nombreGenerico) {
		
		this.nombreGenerico = nombreGenerico;
		this.estado = new EstadoApagado(); // Asumo que todo nuevo dispositivo inteligente inicia en estado apagado
		
	}
	
	public boolean esInteligente() {
		return true;
	}
	
	/* INICIO -- Mètodos de ESTADO 
	 * Dispositivo (DispositivoInteligente en este caso) delega todas sus peticiones (responsabilidades) referidas a su estado
	 * a su instancia de EstadoDispositivo.
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
	
	
	/* Para los siguientes métodos asumo que el dispositivo estuvo en estado encendido durante las últimas N horas y desde la 
	 * fechaInicial hasta la fechaFinal. 
	 */
	
	public int energiaConsumidaDuranteUltimasHoras(int horas) {
		return this.consumoPorHora * horas;
	}
	
	public int consumoTotalEntre(DateTime fechaInicial, DateTime fechaFinal) {
		return 0;
	}
	
}
