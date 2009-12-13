package View;


import java.awt.Color;

import Controller.JuegoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Circulo;

public class VistaCantidadDeVidas extends Circulo implements Posicionable {
	JuegoVivo juegoVivo;
	int x;

	public VistaCantidadDeVidas(int radio, JuegoVivo juegoVivo) {
		super(radio);
		setColor(Color.YELLOW);
		this.juegoVivo = juegoVivo;		
	}
		

	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		int cantidadDeVidas = this.juegoVivo.getCantidadDeVidas();
		for(int i = 0; i < cantidadDeVidas; i++){
			this.x = 470 - i * 30;
			super.dibujar(superfice);
		}
		
	}

	public Posicionable getPosicionable(){
		return this;
	}


	public int getX() {		
		return x;
	}



	public int getY() {		
		return 520;
	}

}
