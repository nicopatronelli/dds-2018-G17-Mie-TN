package hibernate;

import usuarios.Cliente;

public class RepositorioClientes {

	PersistEntity <Cliente> pe;
	
	// Constructor 
	public RepositorioClientes() {
		pe = new PersistEntity<Cliente>();
	}
	
	public void abrir() {
		pe.inicializarEntityManager();
	}
	
	public void cerrar() {
		pe.cerrarEntityManager();
	}
	
	public void guardar(Cliente cliente) {
		pe.guardar(cliente);
	}
	
	public void borrar(Cliente cliente) {
		pe.borrar(cliente);
	}
	
	public void actualizar(Cliente cliente) {
		pe.actualizar(cliente);
	}
	
	public Cliente recuperarPorId(Long idCliente) {
		return pe.recuperar(idCliente, Cliente.class);
	}

	public Cliente recuperarPorUsuario(String nombreUsuario) {
		return pe.obtenerEntidadPorAtributo("usuario", nombreUsuario, Cliente.class);
	}
}
