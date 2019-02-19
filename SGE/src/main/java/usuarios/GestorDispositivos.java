package usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cargaDatosJson.CargaDatosJson;
import hibernate.RepositorioAdmins;
import spark.FlagNuevoDispositivoDisponible;

import static spark.FlagNuevoDispositivoDisponible.*;

// Patrón Prototype 
public class GestorDispositivos {
	
	private Map<String, DispositivoDisponible> dispositivosDisponibles = new HashMap<>();
	
	/* Versión JSON */
/*	public GestorDispositivos() {
		
		Dispositivo[] dispositivosInteligentes = CargaDatosJson.cargarDispositivosInteligentes("src/main/resources/DispositivosInteligentes.json");
		
		for( int i = 0; i < dispositivosInteligentes.length; i++ ) {
			dispositivosDisponibles.put(dispositivosInteligentes[i].nombre(), dispositivosInteligentes[i]);
		}
		
		Dispositivo[] dispositivosEstandares = CargaDatosJson.cargarDispositivosEstandares("src/main/resources/DispositivosEstandares.json");
		
		for( int i = 0; i < dispositivosEstandares.length; i++ ) {
			dispositivosDisponibles.put(dispositivosEstandares[i].nombre(), dispositivosEstandares[i]);
		}
		
	} // fin GestorDispositivos()
*/	
	/* Versión BD */
	// Paso el array que me devuelve Gson a el HashMap que necesito 
	public GestorDispositivos() {
		
		/*if (FlagNuevoDispositivoDisponible.nuevoDispositivoCargado()) {
			// Si ya se cargó un nuevo dispositivo busco los nuevos dispositivos de la base de datos
			
			RepositorioAdmins repoAdmin = new RepositorioAdmins();
			Administrador admin = repoAdmin.recuperarPorUsuario(FlagNuevoDispositivoDisponible.usuarioAdmin());
			
			for(DispositivoDisponible disponible : admin.dispositivosDisponibles()) {
				this.dispositivosDisponibles.put(disponible.nombre(), disponible);
			}
			
		}else {
			// Si todavía no se cargo ningún nuevo dispositivo busco los dispositivos disponibles de los Json
			DispositivoDisponible[] dispositivosInteligentes = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosInteligentes.json");
			
			for( int i = 0; i < dispositivosInteligentes.length; i++ ) {
				dispositivosDisponibles.put(dispositivosInteligentes[i].nombre(), dispositivosInteligentes[i]);
			}
			
			DispositivoDisponible[] dispositivosEstandares = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosEstandares.json");
			
			for( int i = 0; i < dispositivosEstandares.length; i++ ) {
				dispositivosDisponibles.put(dispositivosEstandares[i].nombre(), dispositivosEstandares[i]);
			}
		}*/
		
			// Si todavía no se cargo ningún nuevo dispositivo busco los dispositivos disponibles de los Json
			DispositivoDisponible[] dispositivosInteligentes = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosInteligentes.json");
			
			for( int i = 0; i < dispositivosInteligentes.length; i++ ) {
				dispositivosDisponibles.put(dispositivosInteligentes[i].nombre(), dispositivosInteligentes[i]);
			}
			
			DispositivoDisponible[] dispositivosEstandares = CargaDatosJson.cargarDispositivosDisponibles("src/main/resources/DispositivosEstandares.json");
			
			for( int i = 0; i < dispositivosEstandares.length; i++ ) {
				dispositivosDisponibles.put(dispositivosEstandares[i].nombre(), dispositivosEstandares[i]);
			}

	} // fin GestorDispositivos()
	
	
	public DispositivoDisponible obtenerDispositivo(String keyDispositivo) throws CloneNotSupportedException{
		return (DispositivoDisponible) dispositivosDisponibles.get(keyDispositivo).clone();
	}
	
	// Retornamos una lista (a partir del map) con los dispositivos disponibles
	public List<DispositivoDisponible> dispositivosDisponibles(){
		return new ArrayList<DispositivoDisponible>(dispositivosDisponibles.values());
	}
	
}
