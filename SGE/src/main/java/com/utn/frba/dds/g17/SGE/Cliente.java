package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements IDispositivo {

	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String password;
	private String tipoDocumento;
	private int nroDocumento;
	private List<DomicilioServicio> domicilios = new ArrayList<DomicilioServicio>();
	
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
	}

	public boolean estaEncendido() {
		return this.domicilios.stream().anyMatch(domicilio -> domicilio.estaEncendido());
	}

	public long cantidadDispositivosEncendidos() {
		
		long cantidadDispositivosEncendidos = 0;
		for (DomicilioServicio domicilioServicio : domicilios) {
			for (IDispositivo dispositivo : domicilioServicio.getDispositivos()) {
				cantidadDispositivosEncendidos += dispositivo.cantidadDispositivosEncendidos();
			}
		}
		
		return cantidadDispositivosEncendidos;

	}

	public long cantidadDispositivosApagados() {
		long cantidadDispositivosApagados = 0;
		for (DomicilioServicio domicilioServicio : domicilios) {
			for (IDispositivo dispositivo : domicilioServicio.getDispositivos()) {
				cantidadDispositivosApagados += dispositivo.cantidadDispositivosApagados();
			}
		}
		
		return cantidadDispositivosApagados;
	}

	public long cantidadTotalDispositivos() {
		long cantidadTotalDispositivos = 0;
		for (DomicilioServicio domicilioServicio : domicilios) {
			for (IDispositivo dispositivo : domicilioServicio.getDispositivos()) {
				cantidadTotalDispositivos += dispositivo.cantidadTotalDispositivos();
			}
		}
		
		return cantidadTotalDispositivos;
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
