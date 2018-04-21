package com.utn.frba.dds.g17.SGE;

public class Dispositivo {

	private String nombreGenerico;
	private int consumoPorHora;
	private boolean encendido;
	
	public Dispositivo() {
		
	}
	
	public Dispositivo(String nombreGenerico,int consumoPorHora,boolean encendido) {
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.encendido = encendido;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public int getConsumoPorHora() {
		return consumoPorHora;
	}

	public void setConsumoPorHora(int consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

}
