package hibernate;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import dispositivos.Dispositivo;
import usuarios.Cliente;

public class RepositorioDispositivos {

	PersistEntity <Dispositivo> pe;
	
	// Constructor 
	public RepositorioDispositivos() {
		pe = new PersistEntity<Dispositivo>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(Dispositivo dispositivo) {
		pe.guardar(dispositivo);
	}
	
	public void borrar(Dispositivo dispositivo) {
		pe.borrar(dispositivo);
	}
	
	public void actualizar(Dispositivo dispositivo) {
		pe.actualizar(dispositivo);
	}
	
	public Dispositivo recuperarPorId(Long idDispositivo) {
		return pe.recuperar(idDispositivo, Dispositivo.class);
	}

	/*public Cliente recuperarPorUsuario(String nombreUsuario) {
		return pe.obtenerEntidadPorAtributo("usuario", nombreUsuario, Cliente.class);
	}*/
}
