package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;

public class DispositivoInteligente {
	
	//private int idSGE; // Número de identificación único del dispositivo en SGE
	private String idFabricante;
	private FabricanteDispositivoInteligente fabricante;
	private EstadoDispositivoInteligente estado;
	
	public DispositivoInteligente(String idFabricante, FabricanteDispositivoInteligente fabricante) {
		this.idFabricante = idFabricante;
		this.fabricante = fabricante;
		this.estado = new EstadoApagado(); // Asumo que todo nuevo dispositivo inteligente inicia en estado apagado
	}
	
	/* INICIO - Métodos de ESTADO 
	 * DispositivoInteligente delega todas las peticiones (responsabilidades) referidas a su estado
	 * a su instancia de EstadoDispositivoInteligente 
	 */
	
	public boolean estaEncendido() {
		return this.estado.estaEncendido(this);
	}
	
	public boolean estaApagado() {
		return this.estado.estaApagado(this);
	}
	
	public boolean estaEnAhorroDeEnergia() {
		return this.estado.estaEnModoAhorroDeEnergia(this);
	}
	
	public void apagar() {
		this.estado.apagar(this);
	}
	
	public void encender() {
		this.estado.encender(this);
	}
	
	public void activarAhorroDeEnergia() {
		this.estado.activarAhorroDeEnergia(this);
	}
	
	protected void cambiarEstado(EstadoDispositivoInteligente nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	// FIN - Métodos de ESTADO
	
	/* A diferencia de los dispositivos estándar, donde calculamos un consumo aproximado nosotros (SGE), 
	 * son los fabricantes de los dispositivos inteligentes los que nos van a proporcionar el consumo 
	 * de los mismos (INTERFAZ EXTERNA con SGE). 
	 */
	
	public float energiaConsumidaDuranteUltimasHoras(int horas) {
		return this.fabricante.energiaConsumidaDuranteUltimasHoras(idFabricante, horas);
	}
	
	public float consumoTotalEntre(DateTime fechaInicial, DateTime fechaFinal) {
		return this.fabricante.consumoTotalEntre(idFabricante, fechaInicial, fechaFinal);
	}

	// Getters y Setters
	
	public String getIdFabricante() {
		return idFabricante;
	}
	
	public void iniciarEstadoApagado() { // Para testing
		this.estado = new EstadoApagado();
	}
	
}
