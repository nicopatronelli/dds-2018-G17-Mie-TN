package dispositivos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import estadosDispositivoInteligente.EstadoApagado;
import estadosDispositivoInteligente.EstadoDispositivoInteligente;

@Entity
@Table(name = "Dispositivos_Inteligentes")
public class DispositivoInteligente extends Dispositivo {
	
	@Column(name = "consumo_generado")
	private double consumoGenerado;
	
	@Transient
	private FabricanteDispositivoInteligente fabricante;
	
	@Transient
	private EstadoDispositivoInteligente estado;
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "dispositivo_inteligente_id")
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
	
	public void cambiarEstado(EstadoDispositivoInteligente nuevoEstado) {
		estado = nuevoEstado;
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
	
	public float energiaConsumidaDuranteUltimasHoras(int horas) {
		//this.historial
		// Lo tenemos que hacer nosotros guardando el estado de los dispositivos 
		return 0;
	}
	
	public double consumoEntre(String fechaInicial, String fechaFinal) {
		
		StoredProcedureQuery query =  manager
		.createStoredProcedureQuery("horas_encendido")
		.registerStoredProcedureParameter("id_disp_intel", Long.class, ParameterMode.IN)
		.registerStoredProcedureParameter("fecha_inicio", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("fecha_final", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("resultado", Double.class, ParameterMode.OUT)
		.setParameter("id_disp_intel", this.id)
		.setParameter("fecha_inicio", fechaInicial)
		.setParameter("fecha_final", fechaFinal);
		
		query.execute(); 
		
		Double horasEncendido = (Double) query.getOutputParameterValue("resultado");

		return horasEncendido * this.consumoKwPorHora;
	}
	
	// El consumo instantáneo nos lo provee el fabricante 
	public double consumoInstantaneo() {
		if (this.estaApagado())
			return 0;
		else
			return this.consumoKwPorHora * 10 / 8;
			//return this.fabricante.consumoInstantaneo(fabricante.getIdFabricante());
	}
	
	public String getIdFabricante() {
		return fabricante.getIdFabricante();
	}
	
	public void iniciarEstadoApagado() { 
		this.estado = new EstadoApagado();
	}
	
	@Override
	public boolean estaAdaptado() {
		return false;
	}

	// GETTERS Y SETTERS

	public void setFabricante(FabricanteDispositivoInteligente fabricante) { 
		this.fabricante = fabricante; 
	}

	public FabricanteDispositivoInteligente getFabricante() { return fabricante; }
	
	public List<EntradaDispositivoInteligente> getHistorial(){ return historial; }
	
	public List<EntradaDispositivoInteligente> intervalosEncendido(){
		return this.getHistorial().stream().filter(entrada -> entrada.encendidoOApagado()).collect(Collectors.toList());
	}
	
}
