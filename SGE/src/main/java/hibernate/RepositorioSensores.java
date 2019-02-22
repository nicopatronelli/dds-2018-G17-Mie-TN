package hibernate;

import java.util.List;

import sensores.SensorObservado;
import usuarios.Administrador;
import usuarios.Cliente;

public class RepositorioSensores {

	PersistEntity <SensorObservado> pe;
	
	// Constructor
	public RepositorioSensores() {
		pe = new PersistEntity<SensorObservado>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(SensorObservado sensor) {
		pe.guardar(sensor);
	}
	
	public void borrar(SensorObservado sensor) {
		pe.borrar(sensor);
	}
	
	public void actualizar(SensorObservado sensor) {
		pe.actualizar(sensor);
	}
	
	public SensorObservado recuperarPorId(Long idSensor) {
		return pe.recuperar(idSensor, SensorObservado.class);
	}
	
}
