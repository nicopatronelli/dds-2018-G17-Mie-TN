package sensor;

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

import regla.ReglaObservador;

@Entity
//@Table(name = "Sensores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SensorObservado {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_sensor")
	private Long id;
	
	@Column(name = "cantidad_mangitud_medida")
	protected int magnitudMedida; // Cada sensor mide una unica magnitud física 
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "sensor_id")
	protected List<ReglaObservador> listaObservadores; // El sensor tiene una lista de reglas
	
	public SensorObservado() {
		// Constructor vacío para Hibernate
	}
	
	protected SensorObservado(int magnitudMedida) {
		this.magnitudMedida = magnitudMedida;
		this.listaObservadores = new ArrayList<ReglaObservador>();
	}
	
	public void agregarObservador(ReglaObservador observador) {
		listaObservadores.add(observador);
	}
	
	public void quitarObservador(ReglaObservador observador) {
		listaObservadores.remove(observador);
	}
	
	public void notificarObservadores() {
		listaObservadores.forEach(observador->observador.revisarRegla(magnitudMedida));
	}
	
}
