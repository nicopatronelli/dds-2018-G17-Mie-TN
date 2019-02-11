package usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import simplex.SimplexFacadeSGE;

@Entity
@Table(name = "Clientes")
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
public class Cliente extends Usuario {
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	
	@Column(name = "numero_documento")
	private Integer nroDocumento;
	
	@Column(name = "cantidad_puntos")
	private Integer cantidadPuntos;
	
	@Column(name = "ahorro_automatico")
	private Boolean ahorroAutomatico;
	
	private static final int BONUS_INTELIGENTE = 15; 
	
	private static final int BONUS_CONVERSION = 10; 

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER) @JoinColumn(name = "cliente_id")
	private List<DomicilioServicio> domicilios;

	public Cliente() {
		// Constructor vacío para Hibernate (obligatorio para @Entity) 
	}
	
	public Cliente(String nombre, String apellido, String tipoDocumento, int nroDocumento, 
			String usuario, String password) {
		super(nombre, apellido, usuario, password);
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.cantidadPuntos = 0; // Por negocio inicia en cero 
		this.ahorroAutomatico = false; // Por defecto lo inicializamos en false 
		this.domicilios = new ArrayList<DomicilioServicio>();
		super.inicializarEntityManager();
	}
	
	public void agregarDomicilio(DomicilioServicio domicilio) {
		domicilios.add(domicilio);
	}
	
	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivoEstandar, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoEstandar);
	}
	
	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivoInteligente, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoInteligente);
		this.cantidadPuntos = this.cantidadPuntos + BONUS_INTELIGENTE; // Un cliente recibe 15 puntos por cada dispositivo inteligente que registre en SGE
	}
	
	public void adaptarDispositivoEstandar(DispositivoEstandar unDispositivoEstandar, DomicilioServicio unDomicilio) throws CloneNotSupportedException {
		unDomicilio.adaptarDispositivoEstandar(unDispositivoEstandar); // Delego en el domicilio la adaptación del dispositivo
		this.cantidadPuntos = this.cantidadPuntos + BONUS_CONVERSION; // Un cliente recibe 10 puntos por cada dispositivo estandar que adapta a inteligente
	}
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.domicilios.stream().anyMatch(domicilio -> domicilio.tieneAlgunDispositivoEncendido());
	}

	public int cantidadDispositivosEncendidos() {
		return this.domicilios.stream().mapToInt(domicilio -> domicilio.cantidadDispositivosEncendidos()).sum();
	}

	public int cantidadDispositivosApagados() {
		return this.domicilios.stream().mapToInt(domicilio -> domicilio.cantidadDispositivosApagados()).sum();
	}

	public int cantidadTotalDispositivos() {
		return this.domicilios.stream().mapToInt(domicilio -> domicilio.cantidadTotalDispositivos()).sum();
	}
	
	public double[] recomendacionConsumo(DomicilioServicio unDomicilio) {
		return SimplexFacadeSGE.recomendacionConsumo(unDomicilio);
	}
	
	public void asignarTransformadores(List<Transformador> transformadores) {
		for(DomicilioServicio domicilio : domicilios) {
			domicilio.asignarTransformadorMasCercano(transformadores);
		}
	}
	
	public void mostrarDomicilios() {
		domicilios.forEach(domicilio->domicilio.mostrarDomicilio());
	}
	
	public void cambiarDomicilio(DomicilioServicio domicilioActual, Posicion nuevaPosicion) {
		domicilioActual.nuevaPosicion(nuevaPosicion);
	}
	
	/* GETTERS Y SETTERS */
	
	public int cantidadDePuntos() {
		return cantidadPuntos;
	}
	
	public List<DomicilioServicio> domicilios() {
		return domicilios;
	}
	
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento
				+ ", nroDocumento=" + nroDocumento + ", usuario=" + usuario + ", password=" + password
				+ ", cantidadPuntos=" + cantidadPuntos + ", ahorroAutomatico=" + ahorroAutomatico + "]";
	}
	
} 
