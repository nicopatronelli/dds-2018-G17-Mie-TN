package com.utn.frba.dds.g17.SGE;

public abstract class Dispositivo {
	
	/* Tanto los dispositivos estándar como los inteligentes tiene un atributo denominado consumoPorHora. Para los estándar 
	 * dicho atributo es una estimación (que según el enunciado carga un usuario administrador), mientras que para los dispositivos
	 * inteligentes es el valor real de consumo por hora (se supone que lo suministra el fabricante). 
	 */
	protected String nombreGenerico;
	
	public abstract boolean esInteligente(); // Método para el filtrado
	public abstract boolean estaEncendido();
	public abstract boolean estaApagado();

}