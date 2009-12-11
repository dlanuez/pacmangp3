package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaPerseguidora;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego, 9, Color.PINK); 
		this.setEstrategia(new EstrategiaPerseguidora());
	}

}
