package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;
import org.joda.time.Months;

public class Administrador {

	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String contraseña;
	private String domicilio;
	private DateTime fechaAltaUsuarioSistema;
	private int nroIdentificacion;
	
	public Administrador() {
		
	}
	
	public Administrador(String apellido, String nombre, String nombreUsuario, String contraseña, String domicilio,
			DateTime fechaAltaUsuarioSistema, int nroIdentificacion) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.domicilio = domicilio;
		this.fechaAltaUsuarioSistema = fechaAltaUsuarioSistema;
		this.nroIdentificacion = nroIdentificacion;
	}

	public int cantidadMesesComoAdmistrador() {
		return Months.monthsBetween(this.fechaAltaUsuarioSistema, new DateTime()).getMonths();
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public DateTime getFechaAltaUsuarioSistema() {
		return fechaAltaUsuarioSistema;
	}

	public void setFechaAltaUsuarioSistema(DateTime fechaAltaUsuarioSistema) {
		this.fechaAltaUsuarioSistema = fechaAltaUsuarioSistema;
	}

	public int getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(int nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

}
