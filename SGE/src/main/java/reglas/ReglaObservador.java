package reglas;

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

import actuadores.Actuador;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "reglas")
public abstract class ReglaObservador {
	
	@Id @GeneratedValue @Column(name = "id_regla")
	protected Long id;
	
	protected String descripcion;
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "id_regla")
	protected List<Actuador> actuadores = new ArrayList<Actuador>();
	
	protected ReglaObservador() {
		// Constructor vacío para Hibernate
	}
	
	protected ReglaObservador(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void agregarActuador(Actuador actuador) {
		actuadores.add(actuador);
	}
	
	public void eliminarActuador(Actuador actuador) {
		actuadores.remove(actuador);
	}
	
	public void revisarRegla(double magnitudMedida) {
		if ( seCumpleRegla(magnitudMedida) ) // Template Method
			actuadores.forEach(actuador -> actuador.ejecutarActuador());
	} 
	
	abstract protected boolean seCumpleRegla(double magnitudMedida);
	
}
