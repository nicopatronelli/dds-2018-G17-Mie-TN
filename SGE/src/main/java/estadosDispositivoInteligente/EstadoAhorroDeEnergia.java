package estadosDispositivoInteligente;

import dispositivos.DispositivoInteligente;
import dispositivos.EstadoHistorial;

public class EstadoAhorroDeEnergia implements EstadoDispositivoInteligente {

	/* Métodos que NO implican un cambio de ESTADO: Si bien estos métodos no necesitan recibir el parámetro Dispositivo los incluimos 
	 * aquí para ser congruentes con la interfaz, ya que son métodos que pertenecen al estado del dispositivo.
	 * 
	 */
	public boolean estaEncendido(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	public boolean estaApagado(DispositivoInteligente dispositivoInteligente) {
		return false;
	}
	
	public boolean estaEnModoAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		return true;
	}
	
	// Métodos que PUEDEN implicar un cambio de ESTADO
	
	public void encender(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está en ahorro de energía y se le envía el mensaje encender() debe cambiar su estado a encendido
		 dispositivoInteligente.cambiarEstado(new EstadoEncendido());
		 dispositivoInteligente.getFabricante().encender(dispositivoInteligente.getIdFabricante()); // Le digo al fabricante que encienda el dispositivo
		 dispositivoInteligente.actualizarHistorial(EstadoHistorial.ENCENDIDO); // 
	}
	
	public void apagar(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está en ahorro de energía y se le envía el mensaje apagar() debe cambiar su estado a apagado
		 dispositivoInteligente.cambiarEstado(new EstadoApagado());
		 dispositivoInteligente.getFabricante().apagar(dispositivoInteligente.getIdFabricante()); // Le digo al fabricante que apague el dispositivo
		 dispositivoInteligente.actualizarHistorial(EstadoHistorial.APAGADO);
	}
	
	public void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente) {
		// Si el dispositivo está en ahorro energía y recibe el mensaje activarAhorroDeEnergia no hace nada
	}
	
}
