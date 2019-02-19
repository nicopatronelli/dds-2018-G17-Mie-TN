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
    
    public static String obtenerNombreDispositivo(Request request) {
    	return request.queryParams("nombre"); 
    }
    
    public static String obtenerConsumoKwh(Request request) {
    	return request.queryParams("kwh"); 
    }
	
    public static String obtenerUsoMinimo(Request request) {
    	return request.queryParams("usomin"); 
    }
    
    public static String obtenerUsoMaximo(Request request) {
    	return request.queryParams("usomax"); 
    }
    
    public static String obtenerEsBajoConsumo(Request request) {
    	return request.queryParams("bajoConsumo"); 
    }
    
    public static String obtenerEsInteligente(Request request) {
    	return request.queryParams("esIntel"); 
    }
    
}
