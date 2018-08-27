package estadosDispositivoInteligente;

import dispositivos.DispositivoInteligente;

public interface EstadoDispositivoInteligente {
	
	/* Cada método de EstadoDispositivoInteligente recibe por parámetro una instancia de DispositivoInteligente, lo que le permite acceder a 
	 * los atributos del mismo y cambiar su atributo estado.
	 * 
	 */
	
	public boolean estaEncendido(DispositivoInteligente dispositivoInteligente);
	public boolean estaApagado(DispositivoInteligente dispositivoInteligente);
	public boolean estaEnModoAhorroDeEnergia(DispositivoInteligente dispositivoInteligente);
	public void encender(DispositivoInteligente dispositivoInteligente);
	public void apagar(DispositivoInteligente dispositivoInteligente);
	public void activarAhorroDeEnergia(DispositivoInteligente dispositivoInteligente);
	
}
