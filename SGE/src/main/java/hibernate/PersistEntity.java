package hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

public class PersistEntity<T> {
	
	protected EntityManager manager;
	
	public void guardar() {
		manager.getTransaction().begin();
		manager.persist(this);
		manager.getTransaction().commit();
	}
	
	public T recuperar(Long id) {
		return (T) manager.find(this.getClass(), id);
	}

	public void borrar() {
		manager.remove(this);
	}
	
	public List ejecutarQuery(String query) {
		return manager.createQuery(query).getResultList();
	}
	
	public EntityManager crearEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		return emf.createEntityManager();
	}
	
}
