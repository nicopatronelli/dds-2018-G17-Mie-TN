package usuarios;

import java.util.List;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import domicilio.DomicilioServicio;
import geoposicionamiento.Transformador;
import lombok.Data;
import simplex.SimplexFacadeSGE;

@Data
public class Cliente {
	
	private String apellido;
	private String nombre;
	private String tipoDocumento;
	private int nroDocumento;
	private String nombreUsuario;
	private String password;
	private int cantidadPuntos;
	private boolean ahorroAutomatico;
	private List<DomicilioServicio> domicilios;
	
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
	
} 
