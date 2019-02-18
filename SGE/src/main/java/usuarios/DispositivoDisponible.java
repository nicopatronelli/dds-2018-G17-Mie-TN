package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivos_disponibles")
public class DispositivoDisponible implements Cloneable {
	
	@Id @GeneratedValue 
	protected Long id;
	
	@Column(name = "nombre_generico")
	protected String nombreGenerico;
	
	@Column(name = "consumo_kw_por_hora")
	protected double consumoKwPorHora;
	
	@Column(name = "uso_mensual_minimo_en_horas")
	protected int usoMensualMinimoEnHoras;
	
	@Column(name = "uso_mensual_maximo_en_horas")
	protected int usoMensualMaximoEnHoras;
	
	@Column(name = "es_bajo_consumo")
	protected boolean esBajoConsumo;
	
	@Column(name = "es_inteligente")
	protected boolean esInteligente;
	
	public DispositivoDisponible() {
		// Constructor vacío para Hibernate
	}
	
	public String nombre() {
		return nombreGenerico;
	}

	public double consumoKwPorHora() {
		return consumoKwPorHora;
	}

	public int usoMensualMinimoEnHoras() {
		return usoMensualMinimoEnHoras;
	}

	public int usoMensualMaximoEnHoras() {
		return usoMensualMaximoEnHoras;
	}

	public boolean esBajoConsumo() {
		return esBajoConsumo;
	}

	public boolean esInteligente() {
		return esInteligente;
	}
	
	// Para prototype
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
