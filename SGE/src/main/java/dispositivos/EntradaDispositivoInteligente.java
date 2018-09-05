package dispositivos;

import org.joda.time.DateTime;

public class EntradaDispositivoInteligente {
	
	private DateTime fecha;
	private EstadoHistorial estado;
	
	public EntradaDispositivoInteligente(DateTime fechaActual, EstadoHistorial estadoActual) {
		this.fecha = fechaActual;
		this.estado = estadoActual;
	}
	
	@Override
	public String toString() {
		return "FECHA:" + fecha + " ESTADO:" + estado;
	}
	
}
