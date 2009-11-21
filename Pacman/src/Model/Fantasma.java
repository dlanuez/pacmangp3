package Model;

import Model.excepciones.PosicionInvalidaException;

public class Fantasma extends Viviente {
	public Fantasma(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		super(posicionInicial, juego);
		
	}
}
