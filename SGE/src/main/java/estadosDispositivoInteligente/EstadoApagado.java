package estadosDispositivoInteligente;

import dispositivos.DispositivoInteligente;
import dispositivos.EstadoHistorial;

public class EstadoApagado implements EstadoDispositivoInteligente {
	
	/* Métodos que NO implican un cambio de ESTADO: Si bien estos métodos no necesitan recibir el parámetro Dispositivo los incluimos 
	 * aquí para ser congruentes con la interfaz, ya que son métodos que pertenecen al estado del dispositivo.
	 * 
	 */
	public boolean estaEncendido(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	public boolean estaApagado(DispositivoInteligente dispositivoInteligente) {
		return true;
	}
	
	public boolean estaEnModoAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	// Métodos que PUEDEN implicar un cambio de ESTADO 

	public void encender(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está apagado y se le envía el mensaje encender() debe cambiar su estado a encendido
		 dispositivoInteligente.cambiarEstado(new EstadoEncendido());
		 dispositivoInteligente.getFabricante().encender(dispositivoInteligente.getIdFabricante()); // Le digo al fabricante que encienda el dispositivo
		 dispositivoInteligente.actualizarHistorial(EstadoHistorial.ENCENDIDO);
		 dispositivoInteligente.setEstado(EstadoHistorial.ENCENDIDO);
	}
	
	public void apagar(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está apagado y recibe el mensaje apagar() no hace nada (sigue apagado).
	}
	
	public void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está apagado y se le envía el mensaje activarAhorroDeEnergia() no hace nada (sigue apagado).
	}
	
}
