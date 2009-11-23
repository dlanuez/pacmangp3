package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class Pacman extends Viviente {

	public Pacman(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstado(EstadoViviente.PRESA);
	}

}
