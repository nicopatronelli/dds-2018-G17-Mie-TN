package actuadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dispositivos.Dispositivo;

// Los actuadores son los comandos (patrón command) 
@Entity
//@Table(name = "Actuadores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actuador {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "id_actuador")
	protected Long id;
	
	@OneToOne @JoinColumn(name = "dispositivo_inteligente_id")
	protected Dispositivo dispositivoInteligente;
	
	public Actuador() {
		// Constructor vacío para Hibernate
	}
	
	protected Actuador(Dispositivo dispositivoInteligente){
		this.dispositivoInteligente = dispositivoInteligente;
	}
	
	abstract public void ejecutarActuador();
	
}
