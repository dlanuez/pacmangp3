package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaPerseguidora;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

//este tipo de fantasma no se puede agarrar

public class FantasmaVerde extends Fantasma {

	public FantasmaVerde(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego, 8, Color.GREEN);
		this.setEstrategia(new EstrategiaPerseguidora());
	}
	
	@Override
	public void cambiarEstado(int tiempoDeEstado){
		//silent return.
	}

}
