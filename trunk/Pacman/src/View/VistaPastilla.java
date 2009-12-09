package View;

import java.awt.Color;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.vista.Circulo;

public class VistaPastilla  extends Circulo{

	private ControladorJuego controlador;
	
	public VistaPastilla(ControladorJuego controlador) {
		super(10);
		this.setColor(Color.white);
		this.controlador = controlador;
	}
	
	public void comido(){
		this.controlador.removerDibujable(this);
	}

}