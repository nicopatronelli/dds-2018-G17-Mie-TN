package spark;

public class RequestUtil {
	
    public static String obtenerParamUsuario(Request request) {
        return request.queryParams("username");
    }

    public static String obtenerParamPassword(Request request) {
        return request.queryParams("pass");
    }
    
    public static String obtenerUsuarioActual(Request request) {
    	return request.session().attribute("currentUser"); 
    }
	
}
