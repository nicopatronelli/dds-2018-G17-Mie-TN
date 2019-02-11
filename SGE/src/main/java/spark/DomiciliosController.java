package spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomiciliosController {
	
    public static Route serveDomiciliosPage = (Request request, Response response) -> {
        List<Domicilio> domicilios = new ArrayList<Domicilio>();
    	Domicilio domicilioA = new Domicilio("Tucuman 530", "92.5");
    	Domicilio domicilioB = new Domicilio("Medrano 950", "115.1");
    	Domicilio domicilioC = new Domicilio("Alto Peru 542", "125.8");
    	Domicilio domicilioD = new Domicilio("Mozart 2300", "58.3");
    	Domicilio domicilioE = new Domicilio("Av. Corrientes 4200", "92.1");
    	Domicilio domicilioF = new Domicilio("French 542", "98.2");
    	domicilios.add(domicilioA);
    	domicilios.add(domicilioB);
    	domicilios.add(domicilioC);
    	domicilios.add(domicilioD);
    	domicilios.add(domicilioE);
    	domicilios.add(domicilioF);
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("domicilios", domicilios);
    	
        return ViewUtil.render(request, model, "/velocity/consumo_domicilios.html");
    };
    
    public static Route serveAltaDispositivoPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/alta_dispositivo.html");
    };
    
    public static Route serveEstadoDomicilioPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/estado_domicilio.html");
    };
    
    
    public static Route serveCargaArchivoDispositivosPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/carga_archivo_dispositivos.html");
    };
    
    public static Route serveSimplexPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/simplex.html");
    };
    
    public static Route serveConsumoPeriodoPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/consumo_periodo.html");
    };
    
    public static Route serveMapaPage = (Request request, Response response) -> {
    	Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/mapa.html");
    };
    
}
