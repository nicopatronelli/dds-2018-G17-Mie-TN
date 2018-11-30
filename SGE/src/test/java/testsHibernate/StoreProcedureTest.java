package testsHibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.junit.Ignore;
import org.junit.Test;

public class StoreProcedureTest {
	
	@Ignore
	@Test
	public void procedure_prueba() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		EntityManager manager = emf.createEntityManager();
		
		StoredProcedureQuery query =  manager
				// Tenemos que poner el mismo nombre que tiene el store procedure en la BD
				.createStoredProcedureQuery("procedure_prueba") 
				/*  Los nombres de parámetros no tienen que ser los mismos que en la declaración
				 * del store procedure. Pero si lo llamo "parA", entonces cuando voy a buscar su
				 * resultado con getOutPutParameterValue() también le tengo que pasar "parA"
				 */
				.registerStoredProcedureParameter("resultado", Integer.class, ParameterMode.OUT);
		query.execute();
			 
		Integer resultado = (Integer) query.getOutputParameterValue("resultado");
		System.out.println("El resultado es: " + resultado);
	}
	
	@Ignore
	@Test 
	public void procedure_minutos_entre_fechas() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		EntityManager manager = emf.createEntityManager();

		StoredProcedureQuery query =  manager
				.createStoredProcedureQuery("minutos_entre_fechas")
				.registerStoredProcedureParameter("fecha_inicio", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("fecha_final", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("resultado", Integer.class, ParameterMode.OUT)
				.setParameter("fecha_inicio", "2018-11-01 16:00:00")
				.setParameter("fecha_final", "2018-11-01 18:30:00");
		query.execute();
			 
		Integer resultado = (Integer) query.getOutputParameterValue("resultado");
		System.out.println("El resultado es: " + resultado);
	}
	
	@Test
	public void procedure_minutos_encendido() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SGE");
		EntityManager manager = emf.createEntityManager();
		
		long id = 1;
		
		StoredProcedureQuery query =  manager
		.createStoredProcedureQuery("minutos_encendido")
		.registerStoredProcedureParameter("id_disp_intel", Long.class, ParameterMode.IN)
		.registerStoredProcedureParameter("fecha_inicio", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("fecha_final", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("resultado", Double.class, ParameterMode.OUT)
		.setParameter("id_disp_intel", id)
		.setParameter("fecha_inicio", "2018-10-18")
		.setParameter("fecha_final", "2018-10-26");
		
		query.execute();
		
		Double resultado = (Double) query.getOutputParameterValue("resultado");
		System.out.println("El resultado es: " + resultado);
	}
	
}