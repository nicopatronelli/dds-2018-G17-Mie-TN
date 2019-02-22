package domicilio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import geoposicionamiento.Transformador;
import hibernate.PersistEntity;

import static commons.Matematica.*;

@Entity
@Table(name = "Domicilios")
public class DomicilioServicio extends PersistEntity<DomicilioServicio>{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id_domicilio")
	private Long id;
	
	private String telefono;
	
	@Column(name = "fecha_alta_servicio")
	private LocalDate fechaAltaServicio;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Embedded 
	private Posicion posicion;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)  @JoinColumn(name = "id_domicilio")
	private List<Dispositivo> dispositivos;
	
	public DomicilioServicio() {
		// Constructor vacío para Hibernate
	}
	
	public DomicilioServicio(String telefono, LocalDate fechaAltaServicio, Categoria categoria, Posicion posicion) {
		this.telefono = telefono;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = new ArrayList<Dispositivo>();
		this.posicion = posicion;
	}
	
	// Sobrecarga de constructores (Borrar este sino pincha)
	public DomicilioServicio(String telefono, LocalDate fechaAltaServicio, Categoria categoria) {
		this.telefono = telefono;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = new ArrayList<Dispositivo>();
	}
	
	// No devolvemos List<DispositivoInteligente> porque un dispositivo estandar adaptado también es inteligente 
	public List<Dispositivo> dispositivosInteligentes(){		
		return this.dispositivos.stream().filter(dispositivo -> dispositivo.esInteligente()).collect(Collectors.toList()); 
	}
	
	public List<Dispositivo> dispositivosEstandares(){		
		return this.dispositivos.stream().filter(dispositivo -> !(dispositivo.esInteligente())).collect(Collectors.toList()); 
	}
	
	public int cantidadDispositivosInteligentes() {
		return dispositivosInteligentes().size();
	}
	
	public void registrarDispositivo(Dispositivo nuevoDispositivo) {
		this.dispositivos.add(nuevoDispositivo);
	}
	
	public boolean tieneAlgunDispositivoEncendido() {
		return this.cantidadDispositivosEncendidos() > 0;
	}

	public int cantidadDispositivosEncendidos() {
		return (int) this.dispositivos.stream().filter(dispositivo -> dispositivo.esInteligente()).filter(dispositivoInteligente -> dispositivoInteligente.estaEncendido()).count();
	}

	public int cantidadDispositivosApagados() {
		return (int) this.dispositivos.stream().filter(dispositivo -> dispositivo.esInteligente()).filter(dispositivoInteligente -> dispositivoInteligente.estaApagado()).count();
	}

	public int cantidadTotalDispositivos() {
		return (int) this.dispositivos.stream().count();
	}

	public void adaptarDispositivoEstandar(DispositivoEstandar unDispositivoEstandar) throws CloneNotSupportedException {
		this.dispositivos.get(dispositivos.indexOf(unDispositivoEstandar)).adaptarDispositivo();
	}
	
	// Solo considero a los dispositivos inteligentes porque los estándares no pueden darme su consumo instantáneo 
	public double consumoInstantaneo() {
		return this.dispositivosInteligentes().stream().mapToDouble(dispositivo->dispositivo.consumoInstantaneo()).sum();
	}
	
	// Consumo ultimo periodo
	public double consumoUltimoPeriodo() {
		return redondear(this.dispositivos().stream().mapToDouble(dispositivo->dispositivo.consumoUltimoPeriodo()).sum());
	}
	
	// Consumo periodo determinado
	public double consumoEnPeriodo(String fechaInicial, String fechaFinal) {
		return redondear(this.dispositivos().stream().mapToDouble(dispositivo->dispositivo.consumoEntre(fechaInicial, fechaFinal)).sum());
	}
	
	public void asignarTransformadorMasCercano(List<Transformador> transformadores) {
		
		final double MIN_DISTANCIA_POSIBLE = 100000000; 
		double distanciaMinima = MIN_DISTANCIA_POSIBLE;
		Transformador transformadorAsignado = new Transformador();
				
		for(Transformador transformador : transformadores) {
			if ( this.distanciaEntreDomicilioTransformador(transformador) < distanciaMinima ) {
				distanciaMinima = this.distanciaEntreDomicilioTransformador(transformador);
				transformadorAsignado = transformador;
			}
		} // fin for 
		transformadorAsignado.agregarDomicilio(this); // Agrego el domicilio actual a la lista de domicilios del transformador
	}
	
	// Método auxiliar
	private double distanciaEntreDomicilioTransformador(Transformador unTransformador) {
		return Math.sqrt(Math.pow(this.getPosicion().latitud() - unTransformador.latitud(), 2) 
				+ Math.pow(this.getPosicion().longitud() - unTransformador.longitud(), 2));
	}

	// GETTERS Y SETTERS 
	
	public String coordenadas() {
		return "( " + posicion.latitud() + ", " + posicion.longitud() + " )";
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	public void nuevaPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public List<Dispositivo> dispositivos() {
		return dispositivos;
	}

	public Categoria categoria() {
		return categoria;
	}
	
	public void nuevaCategoria(Categoria nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}
	
	public void mostrarDomicilio() {
		System.out.println("El id del domicilio es: " + id);
		System.out.println("El telefono del domicilio es: " + telefono);
		System.out.println("La posicion del domicilio es: ");
		System.out.println("Latitud: " + posicion.latitud() + 
				" Longitud: " + posicion.longitud());
	}
	
	public Long id() {
		return id;
	}

} 
