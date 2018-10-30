package hibernate;

import javax.persistence.EntityManager;

public class ActiveRecord<T> {
	
	protected Long id;
	protected EntityManager manager;
	
	protected Long getId() {
		return id;
	}
	
	public void guardar() {
		manager.getTransaction().begin();
		manager.persist(this);
		manager.getTransaction().commit();
	}
	
	public T recuperar() {
		return (T) manager.find(this.getClass(), this.getId());
	}

	public void borrar() {
		manager.remove(this);
	}
	
}
