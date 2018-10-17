package hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Manager {
	
	private EntityManager manager;
	
	public Manager() {
		this.manager = Persistence.createEntityManagerFactory("SGE").createEntityManager();
	}
	
/*	public static EntityManager crearManager() {
		return Persistence.createEntityManagerFactory("SGE").createEntityManager();
	}*/
	
	public void persistir(Object objecto) {
		this.manager.persist(objecto);
	}
	
	public void persistirLista(List<Object> lista) {
		for(Object objeto : lista) {
			persistir(objeto);
		}
	}
	
	public void iniciarTransaccion() {
		this.manager.getTransaction().begin();
	}
	
	public void cerrarTransaccion() {
		this.manager.getTransaction().commit();
	}
	
	public Query ejecutarQuery(String query) {
		return this.manager.createQuery(query);
	}
	
	public <T> T find(Class<T> object, Object pk) {
		return this.manager.find(object, pk);
	}
	
	public void cerrar() {
		this.manager.close();
	}
	
}
