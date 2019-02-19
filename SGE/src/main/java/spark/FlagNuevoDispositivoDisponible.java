package spark;

public class FlagNuevoDispositivoDisponible {
	
	public static boolean nuevoDispositivoCargado = false;
	public static String usuarioAdmin;
	
	public static boolean nuevoDispositivoCargado() { return nuevoDispositivoCargado; }
	public static void setFlagTrue() { nuevoDispositivoCargado = true; }
	public static String usuarioAdmin() { return usuarioAdmin;}
	public static void setUsuarioAdmin(String usuario) { usuarioAdmin = usuario;}
	
}
