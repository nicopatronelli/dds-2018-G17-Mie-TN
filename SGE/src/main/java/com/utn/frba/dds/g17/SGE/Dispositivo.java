package com.utn.frba.dds.g17.SGE;

public abstract class Dispositivo {
	
	// Método para el filtrado
	public abstract boolean esInteligente();
	public abstract boolean estaEncendido();
	public abstract boolean estaApagado();

}



/*
public class Dispositivo implements IDispositivo {

	private String nombreGenerico;
	private long consumoPorHora;
	private boolean encendido;
	
	public Dispositivo() {
		
	}

	public Dispositivo(String nombreGenerico, int consumoPorHora, boolean encendido) {
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.encendido = encendido;
	}

	@Override
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	@Override
	public long cantidadDispositivosEncendidos() {
		return this.estaEncendido() ? 1 : 0;
	}

	@Override
	public long cantidadDispositivosApagados() {
		return this.estaEncendido() ? 0 : 1;
	}

	@Override
	public long cantidadTotalDispositivos() {
		return this.getClass() != null ? 1 : 0;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public long getConsumoPorHora() {
		return consumoPorHora;
	}

	public void setConsumoPorHora(long consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

}
*/