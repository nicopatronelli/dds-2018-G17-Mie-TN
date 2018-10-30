package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ActiveRecord<T> {
	
	public EntityManager manager;
	
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
	
	public EntityManager crearEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		return emf.createEntityManager();
	}
	
}
