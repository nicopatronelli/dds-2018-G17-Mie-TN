package sensores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import reglas.ReglaObservador;

@Entity
//@Table(name = "Sensores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SensorObservado {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_sensor")
	private Long id;
	
	@Column(name = "cantidad_mangitud_medida")
	protected double magnitudMedida; // Cada sensor mide una unica magnitud física 
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "sensor_id")
	protected List<ReglaObservador> reglas; // El sensor tiene una lista de reglas
	
	public SensorObservado() {
		// Constructor vacío para Hibernate
	}
	
	protected SensorObservado(double magnitudMedida) {
		this.magnitudMedida = magnitudMedida;
		this.reglas = new ArrayList<ReglaObservador>();
	}
	
	public void agregarRegla(ReglaObservador regla) {
		reglas.add(regla);
	}
	
	public void quitarRegla(ReglaObservador regla) {
		reglas.remove(regla);
	}
	
	public void notificarReglas() {
		reglas.forEach(regla->regla.revisarRegla(magnitudMedida));
	}
	
}
