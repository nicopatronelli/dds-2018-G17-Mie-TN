package mocks;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import commons.Matematica;
import dispositivos.FabricanteDispositivoInteligente;


public class FabricanteSamsungMock extends FabricanteDispositivoInteligente {

	public FabricanteSamsungMock() {
		// Constructor vacío para Hibernate
	}
	
	public FabricanteSamsungMock(String idDispositivoFabricante) {
		super(idDispositivoFabricante);
	}	

	@Override
	public double consumoInstantaneo(String idFabricante) {
		return Matematica.numeroAleatorio();
	}

	@Override
	public void apagar(String idFabricante) {	
		System.out.println("Samsung apaga el dispositivo cuya id es " + idFabricante); 
	}

	@Override
	public void encender(String idFabricante) {	
		System.out.println("Samsung enciende el dispositivo cuya id es " + idFabricante); 
	}

	@Override
	public void activarAhorroDeEnergia(String idFabricante) {		
		System.out.println("Samsung activa el ahorro de energía del dispositivo cuya id es " + idFabricante); 
	}
	
}
