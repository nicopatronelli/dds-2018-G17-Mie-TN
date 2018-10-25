package dispositivos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import usuarios.Administrador;

@Entity
@Table(name = "Dispositivos_estandares")
public class DispositivoEstandar extends Dispositivo {
	
	@Column(name = "horas_de_uso_diarias")
	private int horasDeUsoDiarias; // Lo informa el cliente
	
	@OneToOne(cascade = { CascadeType.ALL })
	private DispositivoInteligente adaptador = null;
	
	protected DispositivoEstandar() {
		// Constructor vacío para Hibernate
	}
	
/*	private DispositivoEstandar(String nombreGenerico, double consumoKwPorHora, int usoMensualMinimoEnHoras,
			int usoMensualMaximoEnHoras, boolean esBajoConsumo) {

		super(nombreGenerico, consumoKwPorHora, usoMensualMinimoEnHoras, usoMensualMaximoEnHoras, esBajoConsumo);
		this.esInteligente = false; // Todo dispositivo estándar inicia como no adaptado
	}*/

	public double consumoDiarioEstimado() {
		return this.consumoKwPorHora * this.horasDeUsoDiarias;
	}
	
	public boolean estaAdaptado() {
		return this.esInteligente(); // Si el dispositivo estándar es inteligente entonces está adaptado
	}
	
	@Override
	public boolean esInteligente() {
		if (adaptador != null)
			return adaptador.esInteligente();
		else
			return false;
	}
	
	@Override 
	public void adaptarDispositivo() throws CloneNotSupportedException {
		
		Administrador admin = new Administrador("Admin_SGE");
		adaptador = admin.obtenerAdaptador(this);
		
/*		this.adaptador = new DispositivoInteligente(this.nombreGenerico, this.consumoKwPorHora, this.usoMensualMinimoEnHoras, 
				this.usoMensualMaximoEnHoras, this.esBajoConsumo); 
		this.adaptador.setFabricante(new FabricanteSGE("01A_SGE"));
		this.esInteligente = true;*/
	}
	
	/* Una vez que un dispositivo estándar esta adaptado delego todos los mensajes referidos a un dispositivo 
	 * inteligente a su adaptador. 
	 */
	
	public DispositivoInteligente adaptador() {
		return this.adaptador;
	}

	public boolean estaEncendido() {
		return adaptador.estaEncendido();
	}

	public boolean estaApagado() {
		return adaptador.estaApagado();
	}

	public boolean estaEnAhorroDeEnergia() {
		return adaptador.estaEnAhorroDeEnergia();
	}

	public void apagar() {
		adaptador.apagar();
	}

	public void encender() {
		adaptador.encender();
	}

	public void activarAhorroDeEnergia() {
		adaptador.activarAhorroDeEnergia();
	}

	@Override
	public double consumoInstantaneo() {
		return adaptador.consumoInstantaneo();
	}

	// GETTERS Y SETTERS
	
	public void setHorasDeUsoDiarias(int horasDeUsoDiarias) {
		this.horasDeUsoDiarias = horasDeUsoDiarias;
	}

	@Override
	public List<EntradaDispositivoInteligente> getHistorial() {
		return adaptador.getHistorial();
	}

}
