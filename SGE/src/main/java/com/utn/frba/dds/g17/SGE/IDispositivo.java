package com.utn.frba.dds.g17.SGE;

public interface IDispositivo {

	public boolean estaEncendido();
	
	public long cantidadDispositivosEncendidos();

	public long cantidadDispositivosApagados();

	public long cantidadTotalDispositivos();

}
