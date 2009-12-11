package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaImpredecible;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaAzul extends Fantasma {

	public FantasmaAzul(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego, 10, Color.BLUE);
		this.setEstrategia(new EstrategiaImpredecible());
	}

}
