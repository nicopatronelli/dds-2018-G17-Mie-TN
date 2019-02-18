package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import estadosDispositivoInteligente.EstadoAhorroDeEnergia;
import estadosDispositivoInteligente.EstadoApagado;
import estadosDispositivoInteligente.EstadoDispositivoInteligente;
import estadosDispositivoInteligente.EstadoEncendido;

@Entity
@Table(name = "Dispositivos_Inteligentes")
public class DispositivoInteligente extends Dispositivo {
	
	@Enumerated(EnumType.STRING) @Column(name = "estado_actual")
	private EstadoHistorial estadoActual;
	
	@Column(name = "consumo_generado")
	private double consumoGenerado;
	
	@Transient
	private FabricanteDispositivoInteligente fabricante;
	
	@Transient
	private EstadoDispositivoInteligente estado;
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "id_dispositivo_inteligente")
	private List<EntradaDispositivoInteligente> historial;
	
	public DispositivoInteligente() {
		// Constructor vacío para Hibernate
	}
	
	/*  							
	 * INICIO - Métodos de ESTADO	
	 */	

	public boolean estaEncendido() {
		return estado.estaEncendido(this);
	}
	
	public boolean estaApagado() {
		return estado.estaApagado(this);
	}
	
	public boolean estaEnAhorroDeEnergia() {
		return estado.estaEnModoAhorroDeEnergia(this);
	}
	
	public void apagar() {
		estado.apagar(this);
	}
	
	public void encender() {
		estado.encender(this);
	}
	
	public void activarAhorroDeEnergia() {
		estado.activarAhorroDeEnergia(this);
	}
	
	public void setEstado(EstadoDispositivoInteligente nuevoEstado) {
		estado = nuevoEstado;
	}
	
	/* Para instanciar el estado correspondiente cuando recuperamos un dispositivo inteligente
	de la base de datos */
	@Override
	public void inicializarEstado() {
		
		switch(estadoActual) {
			case ENCENDIDO : 
				estado = new EstadoEncendido();
				break;
			case APAGADO :
				estado = new EstadoApagado();
				break;
			case AHORRO_DE_ENERGIA :
				estado = new EstadoAhorroDeEnergia();
				break;
			default:
				break;
		}
	}
	
	/*
	 *  FIN - Métodos de ESTADO
	 */
	
	@Override
	public boolean esInteligente() {
		return true;
	}
	
	public void actualizarHistorial(EstadoHistorial estadoActual) {
		EntradaDispositivoInteligente nuevaEntrada = new EntradaDispositivoInteligente(LocalDateTime.now(), estadoActual);
		historial.add(nuevaEntrada);
	}
	
	public void mostrarHistorial() {
		System.out.println(historial.toString());
	}
	
	// El consumo instantáneo nos lo provee el fabricante 
	public double consumoInstantaneo() {
		
		switch(estadoActual) {
		case ENCENDIDO : 
			return this.consumoKwPorHora * 10 / 8;
		case APAGADO :
			return 0;
		case AHORRO_DE_ENERGIA :
			return this.consumoKwPorHora * 4 / 8;
		default:
			return 0;
		}
			//return this.fabricante.consumoInstantaneo(fabricante.getIdFabricante());
	}
	
	public String getIdFabricante() {
		return fabricante.getIdFabricante();
	}
	
	public void iniciarEstadoApagado() { 
		// Inicializamos el estado actual en APAGADO
		estado = new EstadoApagado();
		estadoActual = EstadoHistorial.APAGADO; 
		
		// Inicializamos el historial de estados e insertamos la primer entrada como APAGADO
		historial = new ArrayList<EntradaDispositivoInteligente>();
		actualizarHistorial(EstadoHistorial.APAGADO);
	}
	
	@Override
	public boolean estaAdaptado() {
		return false;
	}

	// GETTERS Y SETTERS
	@Override
	public void setFabricante(FabricanteDispositivoInteligente fabricante) { 
		this.fabricante = fabricante; 
	}

	public FabricanteDispositivoInteligente getFabricante() { return fabricante; }
	
	public List<EntradaDispositivoInteligente> getHistorial(){ return historial; }
	
	public List<EntradaDispositivoInteligente> intervalosEncendido(){
		return this.getHistorial().stream().filter(entrada -> entrada.encendidoOApagado()).collect(Collectors.toList());
	}

	@Override
	public EstadoHistorial estado() {
		return estadoActual;
	}
	
	public EstadoHistorial setEstado(EstadoHistorial estado) {
		return estadoActual = estado;
	}

	@Override
	public int horasDeUsoDiarias() {
		return 0;
	}
	
	public double consumoEntre(String fechaInicial, String fechaFinal) {
		
		try {	
			
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
			EntityManager manager = emf.createEntityManager();
			
			StoredProcedureQuery query =  manager()
			.createStoredProcedureQuery("minutos_encendido")
			.registerStoredProcedureParameter("id_dispositivo", Long.class, ParameterMode.IN)
			.registerStoredProcedureParameter("fecha_inicio", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("fecha_final", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("resultado", Double.class, ParameterMode.OUT)
			.setParameter("id_dispositivo", this.id())
			.setParameter("fecha_inicio", fechaInicial)
			.setParameter("fecha_final", fechaFinal);
			
			query.execute(); 
			
			Double minutosEncendido = (Double) query.getOutputParameterValue("resultado");
			Double horasEncendido = minutosEncendido / 60;
			Double consumoEntre = horasEncendido * this.consumoKwPorHora();
		    double scale = Math.pow(10, 4);
		    
		    return Math.round(consumoEntre * scale) / scale;
		}
		finally {
			//manager.close();
			//emf.close();
		}

	}

	@Override
	public double consumoUltimoPeriodo() {
		return consumoEntre("2018-12-01", "2018-12-31");
	}
	
}
