package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class FantasmaNaranja extends Fantasma {

	public FantasmaNaranja(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
	}

}
