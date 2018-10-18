package usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import domicilio.Posicion;
import geoposicionamiento.Transformador;
import simplex.SimplexFacadeSGE;

@Entity
@Table(name = "Clientes")
public class Cliente {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id_cliente")
	private int id;
	
	private String nombre;
	
	private String apellido;
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	
	@Column(name = "numero_documento")
	private int nroDocumento;
	
	private String usuario;
	
	private String password;
	
	@Column(name = "cantidad_puntos")
	private int cantidadPuntos;
	
	@Column(name = "ahorro_automatico")
	private boolean ahorroAutomatico;

	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "cliente_id")
	private List<DomicilioServicio> domicilios;
	
	public Cliente() {
		// Constructor vacío para Hibernate (obligatorio para @Entity) 
	}
	
	public Cliente(String nombre, String apellido, String tipoDocumento, int nroDocumento, 
			String nombreUsuario, String password) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.usuario = nombreUsuario;
		this.password = password;
		this.cantidadPuntos = 0;
		this.ahorroAutomatico = false;
		this.domicilios = new ArrayList<DomicilioServicio>();
		
	}
	
	public void agregarDomicilio(DomicilioServicio domicilio) {
		domicilios.add(domicilio);
	}
	
	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivoEstandar, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoEstandar);
	}
	
	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivoInteligente, DomicilioServicio unDomicilio) {
		unDomicilio.registrarDispositivo(nuevoDispositivoInteligente);
		this.cantidadPuntos = this.cantidadPuntos + 15; // Un cliente recibe 15 puntos por cada dispositivo inteligente que registre en SGE
	}
	
	public void adaptarDispositivoEstandar(DispositivoEstandar unDispositivoEstandar, DomicilioServicio unDomicilio) {
		unDomicilio.adaptarDispositivoEstandar(unDispositivoEstandar); // Delego en el domicilio la adaptación del dispositivo
		this.cantidadPuntos = this.cantidadPuntos + 10; // Un cliente recibe 10 puntos por cada dispositivo estandar que adapta a inteligente
	}
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.domicilios.stream().anyMatch(domicilio -> domicilio.tieneAlgunDispositivoEncendido());
	}

	public int cantidadDispositivosEncendidos() {
		return domicilios.stream().mapToInt(domicilio -> domicilio.cantidadDispositivosEncendidos()).sum();
	}

	public int cantidadDispositivosApagados() {
		return domicilios.stream().mapToInt(domicilio -> domicilio.cantidadDispositivosApagados()).sum();
	}

	public int cantidadTotalDispositivos() {
		return domicilios.stream().mapToInt(domicilio -> domicilio.cantidadTotalDispositivos()).sum();
	}
	
	public int cantidadDePuntos() {
		return cantidadPuntos;
	}
	
	public double[] recomendacionConsumo(DomicilioServicio unDomicilio) {
		return SimplexFacadeSGE.recomendacionConsumo(unDomicilio);
	}
	
	public void asignarTransformadores(List<Transformador> transformadores) {
		for(DomicilioServicio domicilio : domicilios) {
			domicilio.asignarTransformadorMasCercano(transformadores);
		}
	}
	
	public void cambiarDomicilio(DomicilioServicio domicilioActual, Posicion nuevaPosicion) {
		domicilioActual.nuevaPosicion(nuevaPosicion);
	}
	
	public List<DomicilioServicio> domicilios() {
		return domicilios;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento
				+ ", nroDocumento=" + nroDocumento + ", usuario=" + usuario + ", password=" + password
				+ ", cantidadPuntos=" + cantidadPuntos + ", ahorroAutomatico=" + ahorroAutomatico + "]";
	}
	
} 
