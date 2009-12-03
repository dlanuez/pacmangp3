package Model.viviente;

import Model.Punto;
import Model.estrategia.EstrategiaPerseguidora;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstrategia(new EstrategiaPerseguidora());
	}

}
