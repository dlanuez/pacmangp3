package View;

import Controller.JuegoVivo;
import ar.uba.fi.algo3.titiritero.vista.Texto;


public class VistaJuego extends Texto {
	String texto;
	JuegoVivo juegoVivo;

	public VistaJuego(JuegoVivo juegoVivo) {
		super();
		this.juegoVivo = juegoVivo;
	}
	
	
	protected String getTexto(){
		return this.juegoVivo.getTexto();
	}
}
