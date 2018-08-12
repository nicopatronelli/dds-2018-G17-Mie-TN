package com.utn.frba.dds.g17.SGE;

import org.joda.time.DateTime;

public interface FabricanteDispositivoInteligente {
	
	void actuador(String idFabricante, String accion);
	float energiaConsumidaDuranteUltimasHoras(String idFabricante, int horas);
	float consumoTotalEntre(String idFabricante, DateTime fechaInicial, DateTime fechaFinal);

}
