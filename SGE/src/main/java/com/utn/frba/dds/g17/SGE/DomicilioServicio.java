package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class DomicilioServicio {
	
	private String telefono;
	private DateTime fechaAltaServicio;
	private Categoria categoria;
	private List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.cantidadDispositivosEncendidos() > 0;
	}

	public long cantidadDispositivosEncendidos() {
		return this.dispositivos.stream().filter(dispositivo -> dispositivo.esInteligente()).filter(dispositivoInteligente -> dispositivoInteligente.estaEncendido()).count();
	}

	public long cantidadDispositivosApagados() {
		return this.cantidadTotalDispositivos() - this.cantidadDispositivosEncendidos();
	}

	public long cantidadTotalDispositivos() {
		return this.dispositivos.stream().count();
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

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

}
