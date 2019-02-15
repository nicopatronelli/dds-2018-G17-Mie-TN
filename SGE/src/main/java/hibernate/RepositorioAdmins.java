package hibernate;

import usuarios.Administrador;

public class RepositorioAdmins {

	PersistEntity <Administrador> pe;
	
	// Constructor
	public RepositorioAdmins() {
		pe = new PersistEntity<Administrador>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(Administrador admin) {
		pe.guardar(admin);
	}
	
	public void borrar(Administrador admin) {
		pe.borrar(admin);
	}
	
	public Administrador recuperarPorId(Long idAdmin) {
		return pe.recuperar(idAdmin, Administrador.class);
	}

}
