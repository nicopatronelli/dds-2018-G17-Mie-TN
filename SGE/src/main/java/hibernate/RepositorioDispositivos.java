package hibernate;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import dispositivos.Dispositivo;
import mocks.FabricanteSamsungMock;
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
	
	private Dispositivo recuperarPorIdBase(Long idDispositivo) {
		Dispositivo dispositivo = pe.recuperar(idDispositivo, Dispositivo.class);
		dispositivo.inicializarEstado();
		dispositivo.setFabricante(new FabricanteSamsungMock("SAMSUNG-JD256"));
		return dispositivo;
	}
	
	public Dispositivo recuperarPorId(Long idDispositivo) {
		return recuperarPorIdBase(idDispositivo);
	}
	
	// Sobrecarga del método para recibir el id del dispositivo como String 
	public Dispositivo recuperarPorId(String idDispositivo) {
		return recuperarPorIdBase(Long.parseLong(idDispositivo));
	}
	

	
	/*public Cliente recuperarPorUsuario(String nombreUsuario) {
		return pe.obtenerEntidadPorAtributo("usuario", nombreUsuario, Cliente.class);
	}*/
}
