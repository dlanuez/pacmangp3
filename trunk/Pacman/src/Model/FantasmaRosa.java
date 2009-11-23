package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
	}

}
