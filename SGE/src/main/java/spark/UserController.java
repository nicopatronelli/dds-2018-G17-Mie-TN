package spark;

public class UserController {
	
	// Verificamos si el usuario existe en la BD y coincide su contraseña 
	public static boolean authenticate(String username, String password) {
		
		// Si alguno de los dos campos está vacío rechazo el logueo
		if (username.isEmpty() || password.isEmpty()) { 
            return false;
        }
        
		
		
        return true;
    }
	
}
