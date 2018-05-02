package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class DomicilioServicio implements IDispositivo {
	
	private String telefono;
	private DateTime fechaAltaServicio;
	private Categoria categoria;
	private List<IDispositivo> dispositivos = new ArrayList<IDispositivo>();
	
	@Override
	public boolean estaEncendido() {
		return this.dispositivos.stream().anyMatch(dispositivo -> dispositivo.estaEncendido());
	}

	@Override
	public long cantidadDispositivosEncendidos() {
		return (long) this.dispositivos.stream().filter(dispositivo -> dispositivo.estaEncendido()).count();
	}

	@Override
	public long cantidadDispositivosApagados() {
		return (long) this.dispositivos.stream().filter(dispositivo -> !dispositivo.estaEncendido()).count();
	}

	@Override
	public long cantidadTotalDispositivos() {
		return (long) this.dispositivos.stream().count();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DateTime getFechaAltaServicio() {
		return fechaAltaServicio;
	}

	public void setFechaAltaServicio(DateTime fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<IDispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<IDispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

}
