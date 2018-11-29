package spark;

public class RequestUtil {
	
    public static String obtenerParamUsuario(Request request) {
        return request.queryParams("username");
    }

    public static String obtenerParamPassword(Request request) {
        return request.queryParams("pass");
    }
	
}
