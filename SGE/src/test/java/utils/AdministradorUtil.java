package utils;

import java.time.LocalDate;

import usuarios.Administrador;

public class AdministradorUtil {

	public static Administrador adminDePrueba() throws CloneNotSupportedException {
		
		Administrador admin = new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
		admin.cargarZonas("src/test/resources/data/json/Zonas.json");
		admin.cargarTransformadores("src/test/resources/data/json/Transformadores.json"); 

		return admin;
	}
}
