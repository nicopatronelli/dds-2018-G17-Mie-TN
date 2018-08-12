package com.utn.frba.dds.g17.SGE;

public class DispositivoEstandar {
	
	// Estos datos los informa el cliente
	private String nombreGenerico;
	private double consumoKwPorHora; 
	private int horasDeUsoDiarias; 
	
	//private int idSGE; // Número de identificación único del dispositivo en SGE
	private boolean estaAdaptado;
	private DispositivoInteligente adaptador;
	
	public DispositivoEstandar(String nombreGenerico, double consumoKwPorHora, int horasDeUsoDiarias) {
		this.nombreGenerico = nombreGenerico;
		this.consumoKwPorHora = consumoKwPorHora;
		this.horasDeUsoDiarias = horasDeUsoDiarias;
		this.estaAdaptado = false; // Todo dispositivo estándar inicia como no adaptado
	}
	
	public double consumoDiarioEstimado() {
		return this.consumoKwPorHora * this.horasDeUsoDiarias;
	}
	
	public boolean estaAdaptado() {
		return this.estaAdaptado;
	}
	
	public void adaptarDispositivo() {
		this.adaptador = new DispositivoInteligente("0"); 
		this.estaAdaptado = true;
	}
	
	public DispositivoInteligente adaptador() {
		return this.adaptador;
	}
	
	// Getters y Setters
	
	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public double getConsumoKwPorHora() {
		return consumoKwPorHora;
	}

	public void setConsumoKwPorHora(float consumoKwPorHora) {
		this.consumoKwPorHora = consumoKwPorHora;
	}

	public int getHorasDeUsoDiarias() {
		return horasDeUsoDiarias;
	}

	public void setHorasDeUsoDiarias(int horasDeUsoDiarias) {
		this.horasDeUsoDiarias = horasDeUsoDiarias;
	}
	
}
