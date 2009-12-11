package View;

import java.awt.Color;
import java.awt.Graphics;

import Controller.FantasmaVivo;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Cuadrado;

public class VistaFantasma extends Cuadrado {

	private FantasmaVivo fantasmaVivo;
	
	public VistaFantasma(FantasmaVivo fantasma, int ancho, int alto) {
		super(ancho, alto);
		this.setColor(Color.blue);
		this.fantasmaVivo = fantasma;
		this.setColor(fantasma.getColor());
		this.setPosicionable(fantasma);
	}
	
	public FantasmaVivo getFantasma(){
		return this.fantasmaVivo;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		this.setColor(fantasmaVivo.getColor());
		super.dibujar(superfice);
	}

}
