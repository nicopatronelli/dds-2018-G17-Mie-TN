package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String password;
	private String tipoDocumento;
	private int nroDocumento;
	private List<DomicilioServicio> domicilios = new ArrayList<DomicilioServicio>();
	private int cantidadPuntos;
	
	public Cliente() {
		
	}
	
	public Cliente(String apellido, String nombre, String nombreUsuario, String password, String tipoDocumento,
			int nroDocumento, List<DomicilioServicio> domicilios) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.domicilios = domicilios;
		this.cantidadPuntos = 0;
	}
	
	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivoEstandar, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoEstandar);
	}
	
	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivoInteligente, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoInteligente);
		this.cantidadPuntos = this.cantidadPuntos + 15; // Un cliente recibe 15 puntos por cada dispositivo inteligente que registre en SGE
	}
	
	public void adaptarDispositivo(DispositivoEstandar unDispositivoEstandar) {
		// FALTA 
		this.cantidadPuntos = this.cantidadPuntos + 10; // Un cliente recibe 10 puntos por cada dispositivo estandar que adapta a inteligente
	}
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.domicilios.stream().anyMatch(domicilio -> domicilio.tieneAlgunDispositivoEncendido());
	}

	public long cantidadDispositivosEncendidos() {
		return domicilios.stream().mapToLong(domicilio -> domicilio.cantidadDispositivosEncendidos()).sum();
	}

	public long cantidadDispositivosApagados() {
		return domicilios.stream().mapToLong(domicilio -> domicilio.cantidadDispositivosApagados()).sum();
		
/*	long cantidadDispositivosApagados = 0;
		
		for (DomicilioServicio domicilioServicio : domicilios) {
			for (IDispositivo dispositivo : domicilioServicio.getDispositivos()) {
				cantidadDispositivosApagados += dispositivo.cantidadDispositivosApagados();
			}
	}
		return cantidadDispositivosApagados;*/
		
	}

	public long cantidadTotalDispositivos() {
		return domicilios.stream().mapToLong(domicilio -> domicilio.cantidadTotalDispositivos()).sum();
/*		long cantidadTotalDispositivos = 0;

		for (DomicilioServicio domicilioServicio : domicilios) {
			for (IDispositivo dispositivo : domicilioServicio.getDispositivos()) {
				cantidadTotalDispositivos += dispositivo.cantidadTotalDispositivos();
			}
		}

		return cantidadTotalDispositivos;*/
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public List<DomicilioServicio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<DomicilioServicio> domicilios) {
		this.domicilios = domicilios;
	}
	
}
