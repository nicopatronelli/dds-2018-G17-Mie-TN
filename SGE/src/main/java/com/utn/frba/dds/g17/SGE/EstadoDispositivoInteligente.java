package com.utn.frba.dds.g17.SGE;

public abstract class EstadoDispositivoInteligente {
	
	/* Cada método de EstadoDispositivoInteligente recibe por parámetro una instancia de Dispositivo, lo que le permite acceder a 
	 * los atributos de dicho Dispositivo y cambiar su atributo estado.
	 * 
	 */
	
	public abstract boolean estaEncendido(DispositivoInteligente dispositivoInteligente);
	public abstract boolean estaApagado(DispositivoInteligente dispositivoInteligente);
	public abstract boolean estaEnModoAhorroDeEnergia(DispositivoInteligente dispositivoInteligente);
	public abstract void encender(DispositivoInteligente dispositivoInteligente);
	public abstract void apagar(DispositivoInteligente dispositivoInteligente);
	public abstract void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente);
	
}
