package spark.util;

import spark.Request;

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
    
    public static boolean obtenerEsBajoConsumo(Request request) {
    	String radioButtonValue = request.queryParams("bajoConsumo"); 
    	if (radioButtonValue.equals("esBajoConsumo"))
    		return true;
    	else 
    		return false;
    }
    
    public static boolean obtenerEsInteligente(Request request) {
    	String radioButtonValue = request.queryParams("esIntel"); 
    	if (radioButtonValue.equals("esInteligente"))
    		return true;
    	else 
    		return false;
    }
    
    public static String obtenerFechaDesde(Request request) {
    	return request.queryParams("fechaDesde");
    }
    
    public static String obtenerFechaHasta(Request request) {
    	return request.queryParams("fechaHasta");
    }
    
}
