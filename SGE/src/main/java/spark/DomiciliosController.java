package spark;

import java.util.HashMap;
import java.util.Map;

public class DomiciliosController {
	
    public static Route serveDomiciliosPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, "/velocity/domicilios.vm");
    };
}
