package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaSiempreDobla;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaRojo extends Fantasma {

	public FantasmaRojo(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstrategia(new EstrategiaSiempreDobla());
		this.setColor(Color.RED);
	}

}
