package Model.viviente;

import java.awt.Color;

import Model.Punto;
import Model.estrategia.EstrategiaSiempreDobla;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class FantasmaNaranja extends Fantasma {

	public FantasmaNaranja(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego, 9, Color.ORANGE);
		this.setEstrategia(new EstrategiaSiempreDobla());
	}

}
