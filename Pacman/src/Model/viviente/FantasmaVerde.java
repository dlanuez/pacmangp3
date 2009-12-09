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
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstrategia(new EstrategiaPerseguidora());
		this.setColor(Color.GREEN);
	}
	
	@Override
	public void cambiarEstado(int tiempoDeEstado){
		//silent return.
	}

}
