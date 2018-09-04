package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Months;

import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.EntradaDispositivoInteligente;
import dispositivos.FabricanteDispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import geoposicionamiento.Zona;
import lombok.Data;

@Data
public class Administrador {

	private int id;
	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String password;
	private String domicilio;
	private DateTime fechaAltaUsuarioSistema;
	private List<Cliente> clientes;
	private List<Transformador> transformadores;
	private List<Zona> zonas;
	private GestorDispositivos gestorDispositivos = new GestorDispositivos();
	
	public Administrador(String nombre) {
		this.nombre = nombre;
	}
	
 	public int cantidadMesesComoAdmistrador() {
		return Months.monthsBetween(this.fechaAltaUsuarioSistema, new DateTime()).getMonths();
	}
	
 	public void agregarCliente(Cliente cliente) {
 		clientes.add(cliente);
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
	
	private Dispositivo crearDispositivo(String keyDispositivo) throws CloneNotSupportedException {
		return gestorDispositivos.obtenerDispositivo(keyDispositivo);
	}
	
	public Dispositivo crearDispositivoEstandar(String keyDispositivo, int horasDeUsoDiarias) throws CloneNotSupportedException {
		DispositivoEstandar dispositivoEstandar = (DispositivoEstandar)crearDispositivo(keyDispositivo);
		dispositivoEstandar.setHorasDeUsoDiarias(horasDeUsoDiarias);
		return dispositivoEstandar;
	}
	
	public Dispositivo crearDispositivoInteligente(String keyDispositivo, FabricanteDispositivoInteligente fabricante) throws CloneNotSupportedException {
		DispositivoInteligente dispositivoInteligente = (DispositivoInteligente)crearDispositivo(keyDispositivo);
		dispositivoInteligente.iniciarEstadoApagado();
		dispositivoInteligente.setFabricante(fabricante);
		//dispositivoInteligente.setHistorial(new ArrayList<EntradaDispositivoInteligente>());
		return dispositivoInteligente;
	}
	
	// Método auxiliar (no de negocio) 
	public Cliente[] cargarClientes() {
		Cliente[] clientes = CargaDatosJson.cargarClientes("src/test/resources/data/json/Clientes.json");
		return clientes;
	}
}
