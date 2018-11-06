package testsHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;

public class StoreProcedureTest {
	
	@Test
	public void storeProcedure() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		EntityManager manager = emf.createEntityManager();
		
		StoredProcedureQuery query = 
			    manager.createStoredProcedureQuery("minutos_encendido")
			    .registerStoredProcedureParameter(
			        "resultado", Integer.class, ParameterMode.OUT)
			    .setParameter("resultado", 1L);
			 
		query.execute();
			 
		Integer resultado = (Integer) query.getOutputParameterValue("resultado");
		System.out.println("El resultado es: " + resultado);
	}

}
	

