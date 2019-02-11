package utils;

import java.time.LocalDate;

import usuarios.Administrador;

public class AdministradorUtil {

	public static Administrador adminDePrueba() throws CloneNotSupportedException {
		return new Administrador("Jim", "Beach", "Jimmy", "Queen123", "FakeStreet 123", LocalDate.now());
	}
}
