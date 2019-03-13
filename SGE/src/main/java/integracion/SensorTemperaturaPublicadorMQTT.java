package integracion;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import commons.Matematica;

import static commons.Matematica.*;

public class SensorTemperaturaPublicadorMQTT {
	
    public static void main(String[] args) throws MqttException, InterruptedException {
        
        System.out.println("***** Inicio mediciones sensor temperatura SGE (Publisher) *****");
    	
    	// Nos conectamos al Broker
        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();
        double temperaturaActual;
        double incrementoTemperatura = 0.25;
        
        while (true) {
                MqttMessage message = new MqttMessage();
                temperaturaActual = temperaturaActual() + incrementoTemperatura;
                System.out.println("La temperatura actual en el hogar SGE es: " + temperaturaActual );
                message.setPayload(String.valueOf(temperaturaActual).getBytes());
                client.publish("iot_data", message);
                Thread.sleep(5000);
                
                // Subimos la temperatura para la próxima medición
                incrementoTemperatura = incrementoTemperatura + 1.25;
                
        } // FIN while(true)

    }
    
    public static double temperaturaActual() {
    	return Matematica.numeroAleatorio();
    }
	
}
