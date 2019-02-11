package usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	private String direccion; // ¿Para qué se usa?
	
	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
	
	@Transient
	private GestorDispositivos gestorDispositivos = new GestorDispositivos();;
	
	@OneToMany(cascade = {CascadeType.PERSIST}) @JoinColumn(name = "administrador_id")
	private List<Transformador> transformadores;
	
	@OneToMany(cascade = {CascadeType.PERSIST}) @JoinColumn(name = "administrador_id")
	private List<Zona> zonas;
	
	public Administrador() {
		// Constructor sin argumentos para Hibernate
	}
	
	public Administrador(String nombre) {
		this.nombre = nombre;
	}
	
 	public Administrador(String nombre, String apellido, String usuario, String password, String direccion,
			LocalDate fechaAlta) {
 		super(nombre, apellido, usuario, password);
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		super.inicializarEntityManager();
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
	
	public int cantidadTransformadoresActivos() {
		return transformadores.size();
	}
	
	private Dispositivo crearDispositivo(String keyDispositivo) throws CloneNotSupportedException {
		Dispositivo dispositivo = gestorDispositivos.obtenerDispositivo(keyDispositivo);
		dispositivo.iniciarEntityManager();
		return dispositivo;
	}
	
	public Dispositivo obtenerDispositivoEstandar(String keyDispositivo, int horasDeUsoDiarias) throws CloneNotSupportedException {
		DispositivoEstandar dispositivoEstandar = (DispositivoEstandar)crearDispositivo(keyDispositivo);
		dispositivoEstandar.setHorasDeUsoDiarias(horasDeUsoDiarias);
		return dispositivoEstandar;
	}
	
	public DispositivoInteligente obtenerDispositivoInteligente(String keyDispositivo, FabricanteDispositivoInteligente fabricante) throws CloneNotSupportedException {
		DispositivoInteligente dispositivoInteligente = (DispositivoInteligente)crearDispositivo(keyDispositivo);
		dispositivoInteligente.iniciarEstadoApagado();
		dispositivoInteligente.setFabricante(fabricante);
		return dispositivoInteligente;
	}
	
	public DispositivoInteligente obtenerAdaptador(DispositivoEstandar dispositivoEstandar) throws CloneNotSupportedException {
		DispositivoInteligente adaptador = (DispositivoInteligente) obtenerDispositivoInteligente("Adaptador SGE", new FabricanteSGE("01A_SGE"));
		return adaptador;
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
	
}
