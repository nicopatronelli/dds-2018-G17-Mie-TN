package mocks;

import dispositivos.FabricanteDispositivoInteligente;

public class FabricanteSGE extends FabricanteDispositivoInteligente {

	public FabricanteSGE(String idDispositivoFabricante) {
		super(idDispositivoFabricante);
	}

	public void actuador(String idFabricante, String accion) {

	}

	public double consumoInstantaneo(String idFabricante) {
		return 0.2;
	}

	public void apagar(String idFabricante) {
		System.out.println("SGE apaga el dispositivo cuya id es " + idFabricante); 
	}

	public void encender(String idFabricante) {
		System.out.println("SGE enciende el dispositivo cuya id es " + idFabricante); 
	}

	public void activarAhorroDeEnergia(String idFabricante) {
		System.out.println("SGE activa el ahorro de energía del dispositivo cuya id es " + idFabricante); 
	}

}
