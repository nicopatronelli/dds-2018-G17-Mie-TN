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
	
	public Cliente recuperarPorId(Long idCliente) {
		return pe.recuperar(idCliente, Cliente.class);
	}
}
