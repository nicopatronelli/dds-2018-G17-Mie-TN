package estadosDispositivoInteligente;

import dispositivos.DispositivoInteligente;
import dispositivos.EstadoHistorial;

public class EstadoEncendido implements EstadoDispositivoInteligente {
	
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
		// Si el dispositivo está encendido y se le envía el mensaje apagar() debe cambiar su estado a apagado
		 dispositivoInteligente.cambiarEstado(new EstadoApagado());
		 dispositivoInteligente.getFabricante().apagar(dispositivoInteligente.getIdFabricante()); // Le digo al fabricante que apague el dispositivo
		 dispositivoInteligente.actualizarHistorial(EstadoHistorial.APAGADO);
	}
	
	public void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está encendido y se le envía el mensaje apagar() debe cambiar su estado a ahorro de energía
		 dispositivoInteligente.cambiarEstado(new EstadoAhorroDeEnergia());
		 dispositivoInteligente.getFabricante().activarAhorroDeEnergia(dispositivoInteligente.getIdFabricante()); // Le digo al fabricante que active el ahorro de energía del dispositivo
		 dispositivoInteligente.actualizarHistorial(EstadoHistorial.AHORRO_DE_ENERGIA);
	}

}
