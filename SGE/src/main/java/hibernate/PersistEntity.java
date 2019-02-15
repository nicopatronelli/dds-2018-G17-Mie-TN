package hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistEntity<T> {
	
	protected static EntityManagerFactory emf;
	protected static EntityManager manager;
	
	private static EntityManager crearEntityManager() {
		emf = Persistence.createEntityManagerFactory("SGE");
		return emf.createEntityManager();
	}
	
	public void inicializarEntityManager() {
		manager = crearEntityManager();
	}
	
	public void cerrarEntityManager() {
		if (manager.isOpen() && emf.isOpen())
		{		
			manager.close();
			emf.close();
		}
	}
	
	public void guardar(T objeto) {
		manager.getTransaction().begin();
		manager.persist(objeto);
		manager.getTransaction().commit();
	}
	
	public void borrar(T objeto) {
		manager.getTransaction().begin();
		manager.remove(objeto);
		manager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public T recuperar(Long id, Class<T> clase) {
		return (T) manager.find(clase, id);
	}

	public List ejecutarQuery(String query) {
		return manager.createQuery(query).getResultList();
	}
	

	public T obtenerEntidadPorAtributo(String atributo, String valorAtributo) {
		String query = "FROM " + this.getClass().getName() +" WHERE " + atributo + " = '" + valorAtributo + "'";
		List<T> temp = manager.createQuery(query).getResultList();
		if ( temp.isEmpty() ) // No se encontro la entidad
			return null;
		else 
			return temp.get(0);
	}
	
} // FIN Clase PersistEntity
