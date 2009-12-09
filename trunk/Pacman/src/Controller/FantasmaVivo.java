package Controller;

import java.awt.Color;

import Model.viviente.*;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class FantasmaVivo implements ObjetoVivo, Posicionable {
	private Fantasma fantasma;

	
	public FantasmaVivo(Fantasma fantasma){
		this.fantasma = fantasma;
	}

	public void vivir() {
		this.fantasma.vivir();
		
	}

	
	public int getX() {	
		return 32 * this.fantasma.getPosicion().y();
	}

	public int getY() {
		return 32 * this.fantasma.getPosicion().x();
	}
	
	public Color getColor(){
		return this.fantasma.getColor();
	}
}
