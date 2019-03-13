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
@Table(name = "sensores")
public class SensorObservado {
	
	@Id @GeneratedValue @Column(name = "id_sensor")
	private Long id;
	
	private String tipo; // Temperatura, humedad, presencia, etc... 
	
	@Column(name = "valor_mangitud_medida")
	private double valorMagnitudMedida; // Cada sensor mide una unica magnitud física 
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "id_sensor")
	private List<ReglaObservador> reglas; // El sensor tiene una lista de reglas
	
	public SensorObservado() {
		// Constructor vacío para Hibernate
	}
	
	public SensorObservado(String tipo, double magnitudMedida) {
		this.tipo = tipo;
		this.valorMagnitudMedida = magnitudMedida;
		this.reglas = new ArrayList<ReglaObservador>();
	}
	
	public void agregarRegla(ReglaObservador regla) {
		reglas.add(regla);
	}
	
	public void quitarRegla(ReglaObservador regla) {
		reglas.remove(regla);
	}
	
	public void notificarReglas() {
		reglas.forEach(regla->regla.revisarRegla(valorMagnitudMedida));
	}
	
	public void nuevaMedicion(double nuevaMedicion) {
		
		if (nuevaMedicion != this.valorMagnitudMedida) {
			this.valorMagnitudMedida = nuevaMedicion;
			System.out.println("La magnitud medida por el sensor cambio al nuevo valor de : " + nuevaMedicion);
			this.notificarReglas();
		}
		
	} // fin revisarTemperatura()
	
}
