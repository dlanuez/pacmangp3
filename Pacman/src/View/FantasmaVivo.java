package View;

import Model.Punto;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import Model.viviente.FantasmaAzul;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class FantasmaVivo implements ObjetoVivo, Posicionable {
	private Fantasma fantasma;
	private Mesa mesa;
	
	public FantasmaVivo(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException{
		this.fantasma = new FantasmaAzul(posicionInicial, juego);
	}
	@Override
	public void vivir() {
		this.fantasma.vivir();
		
	}

	@Override
	public int getX() {	
		return 32 * this.fantasma.getPosicion().y();
	}

	@Override
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
