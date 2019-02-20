package usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.FabricanteDispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import mocks.FabricanteSGE;

@Entity
@Table(name = "Administradores")
@AttributeOverride(name = "id", column = @Column(name = "id_admin"))
public class Administrador extends Usuario {
	
	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
	
	@Transient
	private GestorDispositivos gestorDispositivos;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER) @JoinColumn(name = "administrador_id")
	private List<DispositivoDisponible> dispositivosDisponibles;
	
	@OneToMany(cascade = {CascadeType.ALL}) @JoinColumn(name = "administrador_id")
	private List<Transformador> transformadores;
	
	@OneToMany(cascade = {CascadeType.ALL}) @JoinColumn(name = "administrador_id")
	private List<Zona> zonas;
	
	public Administrador() {
		// Constructor vacío para Hibernate
	}
	
	public Administrador(String nombre) {
		this.nombre = nombre;
		//this.cargarDispositivos();
	}
	
 	public Administrador(String nombre, String apellido, String usuario, String password, String direccion,
			LocalDate fechaAlta, List<DispositivoDisponible> listaDisponibles) {
 		super(nombre, apellido, usuario, password);
		this.fechaAlta = fechaAlta;
		this.dispositivosDisponibles = listaDisponibles;
	}
	
 	public <T> Dispositivo recuperarDispositivoPorId(Class <T> clase, Long dispositivoId) {
 		return (Dispositivo) this.manager.find(clase, dispositivoId);
 	}
 	
	public void cargarZonas(String pathJsonZonas) {
		zonas = new ArrayList<Zona>();
		zonas = Arrays.asList(CargaDatosJson.cargarZonas(pathJsonZonas));
	}
 	
	public void cargarTransformadores(String pathJsonTransformadores) {

		transformadores = new ArrayList<Transformador>();
		transformadores = Arrays.asList(CargaDatosJson.cargarTransformadores(pathJsonTransformadores));
		
		for(Zona zona : this.zonas) {
			for(Transformador transformador : this.transformadores){
				resetearTransformador(transformador);
				if (transformador.getZonaId() == zona.getId()) {
					zona.agregarTransformador(transformador); // Agrego los transformadores a la zona correspondiente
				}
			} // fin for 2
		} // fin for 1
		
	} // FIN cargarTransformadores()
	
	private void resetearTransformador(Transformador transformador) {
		transformador.setDomicilios(new ArrayList<DomicilioServicio>()); // Reseteo los domicilios asignados a cada transformador
	}
	
	public void asignarTransformadores(Cliente[] clientes) {
		for(Cliente cliente : clientes) {
			cliente.asignarTransformadores(transformadores);
		}
	}
	
	public void asignarTransformador(Cliente cliente) {
		cliente.asignarTransformadores(transformadores);
	}
	
	public int cantidadTransformadoresActivos() {
		return transformadores.size();
	}
	
	private DispositivoDisponible crearDispositivo(String keyDispositivo) throws CloneNotSupportedException {
		DispositivoDisponible dispositivo = gestorDispositivos.obtenerDispositivo(keyDispositivo);
		return dispositivo;
	}
	
	public DispositivoEstandar obtenerDispositivoEstandar(String keyDispositivo, int horasDeUsoDiarias) throws CloneNotSupportedException {
		DispositivoDisponible disponible = crearDispositivo(keyDispositivo);
		DispositivoEstandar dispositivoEstandar = new DispositivoEstandar(disponible.nombre(), 
				disponible.consumoKwPorHora(), disponible.usoMensualMinimoEnHoras(), 
				disponible.usoMensualMaximoEnHoras(), disponible.esBajoConsumo(),
				disponible.esInteligente());
		dispositivoEstandar.setHorasDeUsoDiarias(horasDeUsoDiarias);
		return dispositivoEstandar;
	}
	
	public DispositivoInteligente obtenerDispositivoInteligente(String keyDispositivo, FabricanteDispositivoInteligente fabricante) throws CloneNotSupportedException {
		DispositivoDisponible disponible = crearDispositivo(keyDispositivo);
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente(disponible.nombre(), 
				disponible.consumoKwPorHora(), disponible.usoMensualMinimoEnHoras(), 
				disponible.usoMensualMaximoEnHoras(), disponible.esBajoConsumo(),
				disponible.esInteligente());
		dispositivoInteligente.iniciarEstadoApagado();
		dispositivoInteligente.setFabricante(fabricante);
		return dispositivoInteligente;
	}
	
	public DispositivoInteligente obtenerAdaptador(DispositivoEstandar dispositivoEstandar) throws CloneNotSupportedException {
		DispositivoInteligente adaptador = (DispositivoInteligente) obtenerDispositivoInteligente("Adaptador SGE", new FabricanteSGE("01A_SGE"));
		return adaptador;
	}
	
	public void cargarDispositivos() {
		dispositivosDisponibles = gestorDispositivos.dispositivosDisponibles();
	}
	
	public void agregarDispositivoDisponible(DispositivoDisponible disponible) {
		dispositivosDisponibles.add(disponible);
	}
	
	public List<DispositivoDisponible> dispositivosDisponibles(){
		return dispositivosDisponibles;
	}
	
	// Método auxiliar (no de negocio) 
	public Cliente[] cargarClientes() {
		Cliente[] clientes = CargaDatosJson.cargarClientes("src/test/resources/data/json/Clientes.json");
		return clientes;
	}
	
	// GETTERS Y SETTERS 

	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public List<Zona> getZonas() {
		return zonas;
	}
	
	public GestorDispositivos gestor() {
		return gestorDispositivos;
	}
	
	// Tenemos que inicializar el gestor de dispositivos con un mensaje porque Hibernate utiliza el constructor vacío
	public void inicializarGestorDispositivos() {
		this.gestorDispositivos = new GestorDispositivos(this.dispositivosDisponibles);
	}
	
}
