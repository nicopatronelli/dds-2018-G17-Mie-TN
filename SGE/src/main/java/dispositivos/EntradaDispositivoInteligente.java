package dispositivos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Estados_dispositivos_inteligentes")
public class EntradaDispositivoInteligente {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id_estado")
	private Long id;
	
	private LocalDateTime fechaHoraActual;
	
	@Enumerated(EnumType.STRING)
	private EstadoHistorial estado;
	
	public EntradaDispositivoInteligente(LocalDateTime fechaHoraActual, EstadoHistorial estadoActual) {
		this.fechaHoraActual = fechaHoraActual;
		this.estado = estadoActual;
	}
	
	public EntradaDispositivoInteligente() {
		// Constructor vacío para Hibernate
	}
	
	/*
	 * GETTERS Y SETTERS
	 */
	
	@Override
	public String toString() {
		return "FECHA:" + fechaHoraActual + " ESTADO:" + estado;
	}
	
	public EstadoHistorial getEstado() {
		return estado;
	}
	
	public boolean encendidoOApagado() {
		return estado.equals(EstadoHistorial.ENCENDIDO) 
				|| estado.equals(EstadoHistorial.APAGADO);
	}
	
}
