package integracion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import integracion.util.ApiMsg;
import integracion.util.ClientMsg;
import integracion.util.JsonTransformer;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class DispositivoFisicoCliente {
	
    public static void main(String[] args) {
        
    	enableDebugScreen();
    	port(4567);
        List<String> msgs = new ArrayList<>();
        
        System.out.println("Este es el ServerREST");
        
        get("/mqtt", (req, res) -> msgs, new JsonTransformer());
        post("/mqtt", (req, res) -> {
            ClientMsg msg = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                msg = mapper.readValue(req.body(), ClientMsg.class);
            } catch (JsonParseException e) {
            	// Si el JSON está mal formado arroja un error
                res.status(400);
                return new ApiMsg("json bad formed", e.getLocalizedMessage());
            }
            msgs.add(msg.getText());
            res.status(201);
            return new ApiMsg("created", "new msg ! ");

        }, new JsonTransformer());

    }
	
}
