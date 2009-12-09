package View;

import java.awt.Color;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.vista.Cuadrado;

public class VistaTablero extends Cuadrado implements Posicionable {

	public VistaTablero(int ancho, int alto) {
		super(ancho, alto);
		this.setColor(Color.BLACK);
	}

	public int getX() {		
		return 0;
	}

	public int getY() {
		return 0;
	}	
	
}
