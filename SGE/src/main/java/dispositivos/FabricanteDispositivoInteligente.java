package dispositivos;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FabricanteDispositivoInteligente {
	
	/*
	 *  Interfaz externa para comunicarnos con el fabricante de un dispositivo inteligente y enviarle 
	 *  mensajes. 
	 */
	
	@Id
	String idFabricante;

	protected FabricanteDispositivoInteligente() {
		// Constructor vacío para Hibernate
	}
	
	protected FabricanteDispositivoInteligente(String idDispositivoFabricante) {
		this.idFabricante = idDispositivoFabricante;
	}
	
	public String getIdFabricante() {
		return idFabricante;
	}
	
	public abstract double consumoInstantaneo(String idFabricante);
	public abstract void apagar(String idFabricante);
	public abstract void encender(String idFabricante);
	public abstract void activarAhorroDeEnergia(String idFabricante);

}
