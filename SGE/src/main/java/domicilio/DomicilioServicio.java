package domicilio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import dispositivos.Dispositivo;
import dispositivos.DispositivoEstandar;
import geoposicionamiento.Transformador;
import lombok.Data;

@Data
public class DomicilioServicio {
	
	private String telefono;
	private DateTime fechaAltaServicio;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;
	private Posicion posicion;
	private Transformador transformador;
	
	public DomicilioServicio(String telefono, DateTime fechaAltaServicio, Categoria categoria) {
		this.telefono = telefono;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = new ArrayList<Dispositivo>();
	}
	
	// No devolvemos List<DispositivoInteligente> porque un dispositivo estandar adaptado también es inteligente 
	public List<Dispositivo> dispositivosInteligentes(){		
		return this.dispositivos.stream().filter(dispositivo -> dispositivo.esInteligente()).collect(Collectors.toList()); 
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

	public void adaptarDispositivoEstandar(DispositivoEstandar unDispositivoEstandar) {
		this.dispositivos.get(dispositivos.indexOf(unDispositivoEstandar)).adaptarDispositivo();
	}
	
	// Solo considero a los dispositivos inteligentes porque los estándares no pueden darme su consumo instantáneo 
	public double consumoInteligenteDomicilio() {
		return this.dispositivosInteligentes().stream().mapToDouble(dispositivo->dispositivo.consumoInstantaneo()).sum();
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
		
		this.transformador = transformadorAsignado; // Asigno el transformador más cercano al domicilio 
		transformadorAsignado.agregarDomicilio(this); // Agrego el domicilio actual a la lista de domicilios del transformador
	}
	
	// Método auxiliar
	private double distanciaEntreDomicilioTransformador(Transformador unTransformador) {
		return Math.sqrt(Math.pow(this.getPosicion().getLatitud() - unTransformador.getLatitud(), 2) 
				+ Math.pow(this.getPosicion().getLongitud() - unTransformador.getLongitud(), 2));
	}
	
} 
