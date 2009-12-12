package Controller;

import Model.juego.Juego;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;

public class JuegoVivo implements ObjetoVivo {
	Juego juego;

	public JuegoVivo(Juego juego){
		this.juego = juego;
	}
	
	public void vivir() {
		
	}

	public String getTexto() {
		String texto = "Puntaje: ";
		texto += (Integer.toString(this.juego.getJugador().getPuntos()));
		return texto;
	}

	public int getCantidadDeVidas() {		
		return this.juego.getJugador().getVidas();
	}
	

}
