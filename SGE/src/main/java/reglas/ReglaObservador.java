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
//@Table(name = "Reglas")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ReglaObservador {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "id_regla")
	private Long id;
	
	@OneToMany(cascade = { CascadeType.ALL }) @JoinColumn(name = "regla_id")
	private List<Actuador> actuadores;
	
	protected ReglaObservador(){
		this.actuadores = new ArrayList<Actuador>();
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
