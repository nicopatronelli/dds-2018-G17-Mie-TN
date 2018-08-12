package com.utn.frba.dds.g17.SGE.ClasesAuxiliaresTestSensoresActuadores;

import org.joda.time.DateTime;

import com.utn.frba.dds.g17.SGE.FabricanteDispositivoInteligente;

public class FabricanteSamsungMock implements FabricanteDispositivoInteligente {

	@Override
	public void actuador(String idFabricante, String accion) {
	}

	@Override
	public float energiaConsumidaDuranteUltimasHoras(String idFabricante, int horas) {
		return 25;
	}

	@Override
	public float consumoTotalEntre(String idFabricante, DateTime fechaInicial, DateTime fechaFinal) {
		return 15;
	}
	
}
