package View;

import java.awt.Color;

import Model.item.Bolita;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Circulo;

public class VistaBolita extends Circulo{

	private Bolita bolita;

	public VistaBolita(Bolita bolita) {
		super(5);
		this.setColor(Color.white);
		this.bolita = bolita;
	}
	
	public void dibujar(SuperficieDeDibujo superfice){
		if (!this.bolita.getComido())
			super.dibujar(superfice);
	}
}
