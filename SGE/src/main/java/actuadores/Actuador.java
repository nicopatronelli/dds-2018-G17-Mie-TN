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
import dispositivos.DispositivoInteligente;

// Los actuadores son los comandos (patrón command) 
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "actuadores")
public abstract class Actuador {
	
	@Id @GeneratedValue @Column(name = "id_actuador")
	protected Long id;
	
	@OneToOne @JoinColumn(name = "id_dispositivo_inteligente")
	protected Dispositivo dispositivoInteligente;
	
	public Actuador() {
		// Constructor vacío para Hibernate
	}
	
	protected Actuador(Dispositivo dispositivoInteligente){
		this.dispositivoInteligente = dispositivoInteligente;
	}
	
	abstract public void ejecutarActuador();
	
}
