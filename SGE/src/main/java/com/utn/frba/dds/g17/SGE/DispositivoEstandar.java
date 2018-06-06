package com.utn.frba.dds.g17.SGE;

public class DispositivoEstandar extends Dispositivo {
	
	private int horasDeUsoDiarias; // Lo informa el cliente
	
	public DispositivoEstandar(int horasDeUsoDiarias) {
		this.horasDeUsoDiarias = horasDeUsoDiarias;
	}
	
	public boolean esInteligente() {
		return false;
	}

	public boolean estaEncendido() {
		return false;
	}
	
	public boolean estaApagado() {
		return false;
	}
	
}
