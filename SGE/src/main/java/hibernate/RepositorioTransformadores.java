package hibernate;

import java.util.List;

import geoposicionamiento.Transformador;
import usuarios.Administrador;
import usuarios.Cliente;

public class RepositorioTransformadores {

	PersistEntity <Transformador> pe;
	
	// Constructor
	public RepositorioTransformadores() {
		pe = new PersistEntity<Transformador>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(Transformador admin) {
		pe.guardar(admin);
	}
	
	public void borrar(Transformador admin) {
		pe.borrar(admin);
	}
	
	public void actualizar(Transformador admin) {
		pe.actualizar(admin);
	}
	
	public List<Transformador> recuperarTransformadores() {
		String query = "FROM Transformador"; // Me traigo todos los transformadores de la BD
		List<Transformador> transformadores = pe.ejecutarQuery(query);
		return transformadores;
	}
	
}
