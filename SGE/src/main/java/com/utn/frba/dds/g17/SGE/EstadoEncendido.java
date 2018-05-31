package com.utn.frba.dds.g17.SGE;

public class EstadoEncendido extends EstadoDispositivoInteligente {
	
	/* Métodos que NO implican un cambio de ESTADO: Si bien estos métodos no necesitan recibir el parámetro Dispositivo los incluimos 
	 * aquí para ser congruentes con la interfaz, ya que son métodos que pertenecen al estado del dispositivo.
	 * 
	 */
	public boolean estaEncendido(DispositivoInteligente dispositivoInteligente) {
		return true;
	}
	
	public boolean estaApagado(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	public boolean estaEnModoAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	// Métodos que PUEDEN implicar un cambio de ESTADO
	public void encender(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está encendido y recibe el mensaje encender() no hace nada. 
	}
	
	public void apagar(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está encendido y se le envía el mensaje apagar() debe CAMBIAR SU ESTADO A APAGADO
		 dispositivoInteligente.cambiarEstado(new EstadoApagado());
		
	}
	
	public void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está encendido y se le envía el mensaje apagar() debe CAMBIAR SU ESTADO A AHORRO DE ENERGIA
		 dispositivoInteligente.cambiarEstado(new EstadoAhorroDeEnergia());
	}

}
