package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.google.gson.annotations.Expose;

public class DomicilioServicio {
	
	private String telefono;
	//@Expose(deserialize = false)
	private DateTime fechaAltaServicio;
	private Categoria categoria;
	private List<DispositivoEstandar> dispositivosEstandares;
	private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<DispositivoInteligente>();

	public DomicilioServicio(String telefono, DateTime fechaAltaServicio, Categoria categoria) {
		this.telefono = telefono;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivosEstandares = new ArrayList<DispositivoEstandar>();
		this.dispositivosInteligentes = new ArrayList<DispositivoInteligente>();
	}
	
	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivoEstandar) {
		this.dispositivosEstandares.add(nuevoDispositivoEstandar);
	}
	
	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivoInteligente) {
		this.dispositivosInteligentes.add(nuevoDispositivoInteligente);
	}
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.cantidadDispositivosEncendidos() > 0;
	}

	public long cantidadDispositivosEncendidos() {
		return this.dispositivosInteligentes.stream().filter(dispositivoInteligente -> dispositivoInteligente.estaEncendido()).count();
	}

	public long cantidadDispositivosApagados() {
		return this.dispositivosInteligentes.stream().filter(dispositivoInteligente -> dispositivoInteligente.estaApagado()).count();
	}

	public long cantidadTotalDispositivos() {
		return this.dispositivosEstandares.stream().filter(dispositivoEstandar -> !dispositivoEstandar.estaAdaptado()).count() + this.dispositivosInteligentes.stream().count();
	}

	public void adaptarDispositivoEstandar(DispositivoEstandar unDispositivoEstandar) {
		this.dispositivosEstandares.get(dispositivosEstandares.indexOf(unDispositivoEstandar)).adaptarDispositivo();
		this.dispositivosInteligentes.add(unDispositivoEstandar.adaptador());
	}
	
	// Getters y Setters
	
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

	public List<DispositivoEstandar> getDispositivosEstandares() {
		return dispositivosEstandares;
	}

	public void setDispositivosEstandars(List<DispositivoEstandar> dispositivosEstandars) {
		this.dispositivosEstandares = dispositivosEstandars;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}

	public void setDispositivosInteligentes(List<DispositivoInteligente> dispositivosInteligentes) {
		this.dispositivosInteligentes = dispositivosInteligentes;
	}

} 
