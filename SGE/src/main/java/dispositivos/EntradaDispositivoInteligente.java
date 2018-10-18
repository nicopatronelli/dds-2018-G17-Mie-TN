package dispositivos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "Estados_Dispositivo_Inteligente")
public class EntradaDispositivoInteligente {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id_estado_di")
	private Long id;
	
	private DateTime fecha;
	
	@Enumerated(EnumType.STRING)
	private EstadoHistorial estado;
	
	public EntradaDispositivoInteligente(DateTime fechaActual, EstadoHistorial estadoActual) {
		this.fecha = fechaActual;
		this.estado = estadoActual;
	}
	
	public EntradaDispositivoInteligente() {
		// Constructor vacío para Hibernate
	}
	
	@Override
	public String toString() {
		return "FECHA:" + fecha + " ESTADO:" + estado;
	}
	
}
