package View;

import Model.Punto;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;
import Model.viviente.*;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class FantasmaVivo implements ObjetoVivo, Posicionable {
	private Fantasma fantasma;
	private Mesa mesa;
	
	public FantasmaVivo(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException{
		this.fantasma = new FantasmaAzul(posicionInicial, juego);
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
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public Mesa getMesa() {
		return mesa;
	}

}
