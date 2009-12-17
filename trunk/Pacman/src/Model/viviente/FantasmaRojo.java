package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaImpredecible;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaRojo extends Fantasma {

	public FantasmaRojo(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego, 9, Color.RED);
		this.setEstrategia(new EstrategiaImpredecible());
	}

}
