package View;

import java.awt.Color;

import Model.item.Pastilla;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Circulo;

public class VistaPastilla  extends Circulo{

	private Pastilla pastilla;
	
	public VistaPastilla(Pastilla pastilla) {
		super(10);
		this.setColor(Color.white);
		this.pastilla = pastilla;
	}
	
	public void dibujar(SuperficieDeDibujo superfice){
		if (!this.pastilla.getComido())
			super.dibujar(superfice);
	}
}