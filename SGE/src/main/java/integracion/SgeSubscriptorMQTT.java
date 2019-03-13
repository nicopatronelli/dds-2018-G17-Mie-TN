package integracion;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import actuadores.ActuadorEncender;
import dispositivos.Dispositivo;
import dispositivos.DispositivoInteligente;
import dispositivos.EstadoHistorial;
import hibernate.RepositorioClientes;
import hibernate.RepositorioDispositivos;
import reglas.ReglaTemperaturaMayorA20Grados;
import sensores.SensorObservado;
import usuarios.Administrador;
import usuarios.Cliente;

public class SgeSubscriptorMQTT {
	
    public static void main(String[] args) throws MqttException, InterruptedException {
    	
    	// Configuración previa
    	RepositorioDispositivos repoDispositivos = new RepositorioDispositivos();
    	repoDispositivos.abrir();
    	Dispositivo aireInteligente = repoDispositivos.recuperarPorId(2L);
    	//repoDispositivos.actualizar(aireInteligente);	
    	
    	// Suponemos que la temperatura inicial del ambiente es de 15C
    	SensorObservado sensor = new SensorObservado("Temperatura", 15);
    	ReglaTemperaturaMayorA20Grados regla = new ReglaTemperaturaMayorA20Grados();
		sensor.agregarRegla(regla);
    	ActuadorEncender actuador = new ActuadorEncender(aireInteligente);
		regla.agregarActuador(actuador);
		
		System.out.println("El dispositivo es: " + aireInteligente.nombre());
		System.out.println("El estado inicial del dispositivo es: " + aireInteligente.estado());
		
        System.out.println("***** Inicio lectura mediciones sensor temperatura SGE (Subscriber) *****");

        MqttClient client = new MqttClient("tcp://localhost:1883", "serverClientId");
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable throwable) {
                System.out.println("El broker MQTT se desconecto.");
            }
            
            // Llego el mensaje correctamente
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
            	String temperaturaActual = new String(mqttMessage.getPayload());
                
                // Acá va la lógica de que hacer con ese mensaje
            	System.out.println("El valor de temperatura recibido es:\t" + temperaturaActual);
                sensor.nuevaMedicion(Double.parseDouble(temperaturaActual)); 
                EstadoHistorial estadoActual = aireInteligente.estado();
                System.out.println("El estado actual del dispositivo es " + estadoActual);
            }

            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });
        client.connect();

        client.subscribe("iot_data");

    }

}
