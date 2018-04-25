package com.utn.frba.dds.g17.SGE;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class Cliente {

	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String password;
	private String tipoDocumento;
	private int nroDocumento;
	private String telefono;
	private String domicilioServicio;
	private DateTime fechaAltaServicio;
	private Categoria categoria;
	List<Dispositivo> dispositivos;

	public Cliente() {
		this.categoria = new Categoria();
		this.dispositivos = new ArrayList<Dispositivo>();
	}
	
	public Cliente(String apellido, String nombre, String nombreUsuario, String password, String tipoDocumento,
			int nroDocumento, String telefono, String domicilioServicio, DateTime fechaAltaServicio,
			Categoria categoria, List<Dispositivo> dispositivos) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.telefono = telefono;
		this.domicilioServicio = domicilioServicio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
	}

	public boolean tieneDispositivosEncendidos() {
		return this.dispositivos.stream().anyMatch(dispositivo -> dispositivo.isEncendido());
	}

	public int cantidadDispositivosEncendidos() {
		return (int) this.dispositivos.stream().filter(dispositivo -> dispositivo.isEncendido()).count();
	}

	public int cantidadDispositivosApagados() {
		return (int) this.dispositivos.stream().filter(dispositivo -> !dispositivo.isEncendido()).count();
	}

	public int cantidadTotalDispositivos() {
		return (int) this.dispositivos.stream().count();
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

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilioServicio() {
		return domicilioServicio;
	}

	public void setDomicilioServicio(String domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
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
