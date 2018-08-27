package usuarios;

import java.util.HashMap;
import cargaDatosJson.CargaDatosJson;
import dispositivos.Dispositivo;

// Patrón Prototype 
public class GestorDispositivos {
	
	private HashMap<String, Dispositivo> dispositivos = new HashMap<>();
	
	public GestorDispositivos() {
		
		Dispositivo[] dispositivosInteligentes = CargaDatosJson.cargarDispositivosInteligentes("src\\main\\resources\\DispositivosInteligentes.json");
		
		for( int i = 0; i < dispositivosInteligentes.length; i++ ) {
			dispositivos.put(dispositivosInteligentes[i].getNombreGenerico(), dispositivosInteligentes[i]);
		}
		
		Dispositivo[] dispositivosEstandares = CargaDatosJson.cargarDispositivosEstandares("src\\main\\resources\\DispositivosEstandares.json");
		
		for( int i = 0; i < dispositivosEstandares.length; i++ ) {
			dispositivos.put(dispositivosEstandares[i].getNombreGenerico(), dispositivosEstandares[i]);
		}
		
	} // fin GestorDispositivos()
		
	public Dispositivo obtenerDispositivo(String unDispositivo) throws CloneNotSupportedException{
		return (Dispositivo) dispositivos.get(unDispositivo).clone();
	}
	
}
