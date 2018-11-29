package usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.Months;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.FabricanteDispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import hibernate.PersistEntity;
import mocks.FabricanteSGE;

@Entity
@Table(name = "Administradores")
public class Administrador extends PersistEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_admin")
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String usuario;
	
	private String password;
	
	private String direccion;
	
	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
	
	@Transient
	private GestorDispositivos gestorDispositivos = new GestorDispositivos();;
	
	//@Transient
	@OneToMany(cascade = {CascadeType.PERSIST}) @JoinColumn(name = "administrador_id")
	private List<Transformador> transformadores;
	
	@OneToMany(cascade = {CascadeType.PERSIST}) @JoinColumn(name = "administrador_id")
	//@Transient
	private List<Zona> zonas;
	
	public Administrador() {
		// Constructor sin argumentos para Hibernate
	}
	
	public Administrador(String nombre) {
		this.nombre = nombre;
	}
	
 	public Administrador(String nombre, String apellido, String usuario, String password, String direccion,
			LocalDate fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		super.inicializarEntityManager();
	}
	
/* 	public int cantidadMesesComoAdmistrador() {
		return Months.monthsBetween(fechaAlta, new DateTime()).getMonths();
	}*/

/*	public void agregarCliente(Cliente cliente) {
 		clientes.add(cliente);
 	}*/
 	
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
	
	public int cantidadTransformadoresActivos() {
		return transformadores.size();
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
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
}
