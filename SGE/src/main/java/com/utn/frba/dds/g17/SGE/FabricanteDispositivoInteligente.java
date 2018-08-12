package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;

public interface FabricanteDispositivoInteligente {
	
	/*
	 *  Interfaz externa para comunicarnos con el fabricante de un dispositivo inteligente y enviarle 
	 *  mensajes. 
	 */
	
	void actuador(String idFabricante, String accion);
	float energiaConsumidaDuranteUltimasHoras(String idFabricante, int horas);
	float consumoTotalEntre(String idFabricante, DateTime fechaInicial, DateTime fechaFinal);

}
