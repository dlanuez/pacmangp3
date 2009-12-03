package View;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;
import Model.excepciones.PosicionInvalidaException;
import Model.juego.Juego;
import Model.viviente.Pacman;
import Model.Punto;
import java.awt.Color;
import ar.uba.fi.algo3.titiritero.vista.Circulo;


public class PacmanVivo implements ObjetoVivo, Posicionable{

	private Pacman pacman;	
	private Mesa mesa;

	public PacmanVivo(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		this.pacman = new Pacman(posicionInicial, juego);
	}
	
	public void vivir() {
		//el controlador hace un pacman.ir_a_donde_se_apreto_la_tecla()!!
		this.pacman.vivir();
	}

	public int getX() {
		return this.pacman.getPosicion().x();
	}

	public int getY() {
		return this.pacman.getPosicion().y();
	}	
	
	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
		
}